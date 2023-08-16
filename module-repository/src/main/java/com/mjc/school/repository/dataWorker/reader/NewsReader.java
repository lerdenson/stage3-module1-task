package com.mjc.school.repository.dataWorker.reader;

import com.mjc.school.repository.dataTypes.News;
import com.mjc.school.repository.interfaces.DataReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsReader implements DataReader<News> {
    @Override
    public List<News> read() {
        List<News> newsList = new ArrayList<>();

        try {
            List<String> content = getData("content.txt");
            List<String> news = getData("news.txt");
            for (int i = 0; i < 20; i++) {
                newsList.add(
                        new News(news.get(i), content.get(i), i+1));
            }

        } catch (IOException e) {
            System.err.println("IO exception in readNews()");
        }


        return newsList;
    }


    private List<String> getData(String filepath) throws IOException{
        List<String> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                data.add(nextLine);
            }
        }
        return data;
    }
}
