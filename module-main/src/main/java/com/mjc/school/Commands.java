package com.mjc.school;

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

    public static Commands getCommand(int commandNumber) {
        Optional<Commands> optional = Arrays.stream(Commands.values()).filter(a -> a.commandNumber == commandNumber).findFirst();
        return optional.orElse(null);
    }

    @Override
    public String toString() {
        return String.format("%d - %s", commandNumber, description);
    }
}
