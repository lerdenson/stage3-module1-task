package com.mjc.school.repository.dataWorker;

import com.mjc.school.repository.dataTypes.Author;
import com.mjc.school.repository.dataTypes.News;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static DataSource instance;
    private List<Author> authors;
    private List<News> news;

    private DataSource() {
        getCollections();
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public List<News> getNews() {
        return news;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    private void getCollections() {
        this.authors = new ArrayList<>();
        this.news = new ArrayList<>();
        DataReader reader = new DataReader();
        List<String> authorNames = reader.readLinesFromFile("author.txt");
        for (String name : authorNames) {
            this.authors.add(new Author(name));
        }

        List<String> newsTitles = reader.readLinesFromFile("news.txt");
        List<String> newsContent = reader.readLinesFromFile("content.txt");

        for (int i = 0; i < 20; i++) {
            this.news.add(new News(
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
