package com.mjc.school.repository.dataWorker;

import com.mjc.school.repository.dataTypes.Author;
import com.mjc.school.repository.dataTypes.News;
import com.mjc.school.repository.dataWorker.reader.AuthorsReader;
import com.mjc.school.repository.dataWorker.reader.NewsReader;

import java.util.List;

public class DataSource {
    private List<Author> authors;
    private List<News> news;

    private DataSource() {
        authors = new AuthorsReader().read();
        news = new NewsReader().read();
    }

    private static DataSource instance = new DataSource();



    public static DataSource getInstance() {
        return instance;
    }

    public List<News> getNews() {
        return news;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
