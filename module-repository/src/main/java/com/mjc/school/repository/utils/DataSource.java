package com.mjc.school.repository.utils;

import com.mjc.school.repository.models.AuthorModel;
import com.mjc.school.repository.models.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static DataSource instance;
    private List<AuthorModel> authors;
    private List<NewsModel> news;

    private DataSource() {
        getCollections();
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public List<NewsModel> getNews() {
        return news;
    }

    public List<AuthorModel> getAuthors() {
        return authors;
    }

    private void getCollections() {
        this.authors = new ArrayList<>();
        this.news = new ArrayList<>();
        DataReader reader = new DataReader();
        List<String> authorNames = reader.readLinesFromFile("author.txt");
        for (String name : authorNames) {
            this.authors.add(new AuthorModel(name));
        }

        List<String> newsTitles = reader.readLinesFromFile("news.txt");
        List<String> newsContent = reader.readLinesFromFile("content.txt");

        for (int i = 0; i < 20; i++) {
            this.news.add(new NewsModel(
                    getRandomString(newsTitles),
                    getRandomString(newsContent),
                    getRandomAuthorId()
            ));
        }


    }

    private String getRandomString(List<String> list) {
        return list.get((int) (Math.random() * list.size()));
    }

    private long getRandomAuthorId() {
        return this.authors.get((int) (Math.random() * authors.size())).getId();
    }
}
