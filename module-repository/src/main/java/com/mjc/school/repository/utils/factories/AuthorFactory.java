package com.mjc.school.repository.utils.factories;

import com.mjc.school.repository.models.AuthorModel;

public class AuthorFactory {
    private long nextId = 1;

    private long generateId() {
        return nextId++;
    }

    public AuthorModel createAuthor(String name) {
        return new AuthorModel(generateId(), name);
    }
}
