package com.mjc.school.repository.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorModel {
    private long id;
    private String name;


    private static volatile long currentNextId = 1;

    private synchronized long generateId() {
        return currentNextId++;
    }

    public AuthorModel(String name) {
        this.id = generateId();
        this.name = name;
    }
}
