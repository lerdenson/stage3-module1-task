package com.mjc.school.repository.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public List<String> readLinesFromFile(String filename) {
        List<String> content = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(filename)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
