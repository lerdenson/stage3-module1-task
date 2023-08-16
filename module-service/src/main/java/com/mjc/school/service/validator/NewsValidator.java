package com.mjc.school.service.validator;

import com.mjc.school.service.dto.NewsRequestDTO;

public class NewsValidator {
    public boolean validateNews(NewsRequestDTO newsDTO) {
        return (validateTitle(newsDTO.getTitle()) && validateContent(newsDTO.getContent()));
    }

    private boolean validateTitle(String title) {
        return (title.length() >= 5 && title.length() <= 30);
    }

    private boolean validateContent(String content) {
        return (content.length() >= 5 && content.length() <= 255);
    }
}
