package com.mjc.school.repository.dataWorker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mjc.school.repository.dataTypes.Author;
import com.mjc.school.repository.dataTypes.News;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public List<Author> readAuthors() {
        List<Author> authors = new ArrayList<>();
        try (FileReader reader = new FileReader("author.json")) {
            authors = new Gson().fromJson(reader, new TypeToken<ArrayList<Author>>(){}.getType());

        } catch (IOException e) {
            System.err.println("IO exception in readAuthors");
        }


        return authors;
    }

    public List<News> readNews() {
        List<News> news = new ArrayList<>();
        try (FileReader reader = new FileReader("news.json")) {
            news = new Gson().fromJson(reader, new TypeToken<ArrayList<Author>>(){}.getType());

        } catch (IOException e) {
            System.err.println("IO exception in readNews()");
        }


        return news;
    }
}
