package com.mjc.school.utils;

import com.mjc.school.service.exceptions.ValidationException;

import java.util.Arrays;
import java.util.Optional;

public enum Commands {
    GET_ALL_NEWS(1, "Get all news."),
    GET_NEWS_BY_ID(2, "Get news by id."),
    CREATE_NEWS(3, "Create news."),
    UPDATE_NEWS(4, "Update news."),
    REMOVE_NEWS_BY_ID(5, "Remove news by id."),
    EXIT(0, "Exit.");

    private final int commandNumber;
    private final String description;

    Commands(int command, String description) {
        this.commandNumber = command;
        this.description = description;
    }

    public static Commands getCommand(int commandNumber) throws ValidationException {
        Optional<Commands> optional = Arrays.stream(Commands.values()).filter(a -> a.commandNumber == commandNumber).findFirst();
        if (optional.isPresent()) return optional.get();
        else throw new ValidationException("Command not found");
    }

    public int getCommandNumber() {
        return commandNumber;
    }

    @Override
    public String toString() {
        return String.format("%d - %s", commandNumber, description);
    }
}
