package com.mjc.school.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewsDtoRequest {
    private long id;
    private String title;
    private String content;
    private long authorId;

    public NewsDtoRequest(String title, String content, long authorId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }
}
