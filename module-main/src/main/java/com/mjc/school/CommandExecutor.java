package com.mjc.school;

import com.mjc.school.controller.interfaces.Controller;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;

import java.util.List;
import java.util.Scanner;

public class CommandExecutor {
    private final String WRONG_ID = "id must be number";
    private final Controller<NewsRequestDTO, NewsResponseDTO> newsController;

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
        List<NewsResponseDTO> newsList = newsController.readAll();
        for (NewsResponseDTO news : newsList) {
            System.out.println(news);
        }
    }

    private void findById(Scanner scanner) {
        try {
            long id = Long.parseLong(readString(scanner, "id"));
            NewsResponseDTO news = newsController.readById(id);
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
            NewsRequestDTO newNews = new NewsRequestDTO(title, content, authorId);
            NewsResponseDTO returnedNews = newsController.create(newNews);
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
            NewsRequestDTO newsForUpdate = new NewsRequestDTO(id, title, content, authorId);
            NewsResponseDTO returnedNews = newsController.update(newsForUpdate);
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
