package com.mjc.school;

import com.mjc.school.controller.interfaces.Controller;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;

import java.util.List;
import java.util.Scanner;

public class CommandExecutor {
    private final String WRONG_ID = "id must be number";
    private final Controller<NewsRequestDto, NewsResponseDto> newsController;

    public CommandExecutor() {
        newsController = new NewsController();
    }

    public void printMenu() {
        System.out.println("Enter the number of operation:");
        for (Commands command : Commands.values()) {
            System.out.println(command);
        }
    }

    public void executeCommand(Commands command, Scanner scanner) {
        switch (command) {
            case GET_ALL_NEWS -> findAll();
            case GET_NEWS_BY_ID -> findById(scanner);
            case CREATE_NEWS -> create(scanner);
            case UPDATE_NEWS -> update(scanner);
            case REMOVE_NEWS_BY_ID -> delete(scanner);
            case EXIT -> System.exit(0);
            default -> System.out.println("There is no such command");

        }
    }

    private void findAll() {
        List<NewsResponseDto> newsList = newsController.readAll();
        for (NewsResponseDto news : newsList) {
            System.out.println(news);
        }
    }

    private void findById(Scanner scanner) {
        try {
            long id = Long.parseLong(readString(scanner, "id"));
            NewsResponseDto news = newsController.readById(id);
            System.out.println(news);
        } catch (NumberFormatException e) {
            System.out.println(WRONG_ID);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void create(Scanner scanner) {
        try {
            String title = readString(scanner, "title");
            String content = readString(scanner, "content");
            long authorId = Long.parseLong(readString(scanner, "author id"));
            NewsRequestDto newNews = new NewsRequestDto(title, content, authorId);
            NewsResponseDto returnedNews = newsController.create(newNews);
            System.out.println(returnedNews);
        } catch (NumberFormatException e) {
            System.out.println(WRONG_ID);
        } catch (NotFoundException | ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    private void update(Scanner scanner) {
        try {
            long id = Long.parseLong(readString(scanner, "id"));
            String title = readString(scanner, "title");
            String content = readString(scanner, "content");
            long authorId = Long.parseLong(readString(scanner, "author id"));
            NewsRequestDto newsForUpdate = new NewsRequestDto(id, title, content, authorId);
            NewsResponseDto returnedNews = newsController.update(newsForUpdate);
            System.out.println(returnedNews);

        } catch (NumberFormatException e) {
            System.out.println(WRONG_ID);
        } catch (NotFoundException | ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    private void delete(Scanner scanner) {
        try {
            long id = Long.parseLong(readString(scanner, "id"));
            boolean isDeleted = newsController.delete(id);
            System.out.println(isDeleted);
        } catch (NumberFormatException e) {
            System.out.println(WRONG_ID);
        }
    }

    private String readString(Scanner scanner, String paramName) {
        System.out.format("Enter news %s:", paramName);
        String input;
        do {
            input = scanner.nextLine().trim();
        } while (input.isEmpty());

        return input;
    }


}
