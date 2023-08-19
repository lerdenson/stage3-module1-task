package com.mjc.school.repository.utils.factories;

import com.mjc.school.repository.models.NewsModel;

import java.time.LocalDateTime;

public class NewsFactory {
    private long nextId = 1;

    private long generateId() {
        return nextId++;
    }

    public NewsModel createNews(String title, String content, Long authorId) {
        return new NewsModel(
                generateId(),
                title,
                content,
                LocalDateTime.now(),
                LocalDateTime.now(),
                authorId
        );
    }

    public NewsModel updateNews(NewsModel originalNews, NewsModel newData) {
        originalNews.setAuthorId(newData.getAuthorId());
        originalNews.setTitle(newData.getTitle());
        originalNews.setContent(newData.getContent());
        originalNews.setLastUpdateDate(LocalDateTime.now());
        return originalNews;
    }
}
