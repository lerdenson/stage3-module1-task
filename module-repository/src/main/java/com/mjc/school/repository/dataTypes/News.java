package com.mjc.school.repository.dataTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
@Builder
public class News {
    private long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private long authorId;

    private static volatile long currentNextId = 1;

    private synchronized long generateId() {
        return currentNextId++;
    }

    public News(String title, String content, long authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;

        this.id = generateId();
        this.createDate = LocalDateTime.now();
        this.lastUpdateDate = LocalDateTime.now();
    }
}
