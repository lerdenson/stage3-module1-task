package com.mjc.school.repository.dataTypes;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
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
        this.createDate = LocalDateTime.now();
        this.lastUpdateDate = LocalDateTime.now();

        this.id = generateId();
    }
}
