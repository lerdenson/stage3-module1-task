package com.mjc.school.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsRequestDTO {
    private long id;
    private String title;
    private String content;
    private long authorId;
}
