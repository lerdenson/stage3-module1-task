package com.mjc.school.repository.dataTypes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {
    private long id;
    private String name;


    private static volatile long currentNextId = 1;

    private synchronized long generateId() {
        return currentNextId++;
    }

    public Author(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(String name) {
        this.id = generateId();
        this.name = name;
    }
}
