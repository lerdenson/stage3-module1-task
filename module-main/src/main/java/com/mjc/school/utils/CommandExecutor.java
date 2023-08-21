package com.mjc.school.utils;

import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.controller.interfaces.Controller;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;

import java.util.List;
import java.util.Scanner;

public class CommandExecutor {
    private final static String wrongIdMessage = "id must be number";
    private final Controller<NewsDtoRequest, NewsDtoResponse> newsController;

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
            default -> System.out.println("Command not found.");

        }
    }

    private void findAll() {
        List<NewsDtoResponse> newsList = newsController.readAll();
        for (NewsDtoResponse news : newsList) {
            System.out.println(news);
        }
    }

    private void findById(Scanner scanner) {
        try {
            long id = Long.parseLong(readString(scanner, "id"));
            NewsDtoResponse news = newsController.readById(id);
            System.out.println(news);
        } catch (NumberFormatException e) {
            System.out.println(wrongIdMessage);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void create(Scanner scanner) {
        try {
            String title = readString(scanner, "title");
            String content = readString(scanner, "content");
            long authorId = Long.parseLong(readString(scanner, "author id"));
            NewsDtoRequest newNews = new NewsDtoRequest(title, content, authorId);
            NewsDtoResponse returnedNews = newsController.create(newNews);
            System.out.println(returnedNews);
        } catch (NumberFormatException e) {
            System.out.println(wrongIdMessage);
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
            NewsDtoRequest newsForUpdate = new NewsDtoRequest(id, title, content, authorId);
            NewsDtoResponse returnedNews = newsController.update(newsForUpdate);
            System.out.println(returnedNews);

        } catch (NumberFormatException e) {
            System.out.println(wrongIdMessage);
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
            System.out.println(wrongIdMessage);
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
