package com.mjc.school.service.exceptions;

public enum ErrorCodeMessage {
    NEWS_NOT_FOUND("01", "NewsModel with id: %d not found"),
    AUTHOR_NOT_FOUND("02", "AuthorModel with id: %d not found"),
    INCORRECT_NEWS_TITLE("03", "Title should`t be shorter than 5 characters and longer than 30. Your title length is: %d"),
    INCORRECT_NEWS_CONTENT("04", "Content should`t be shorter than 5 characters and longer than 255. Your content length is: %d"),
    NULL_AUTHOR_ID("05", "AuthorModel id can`t be null");

    private final String code;
    private final String message;

    ErrorCodeMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ERROR CODE: " + code + " , MESSAGE: " + message;
    }
}
