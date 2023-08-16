package com.mjc.school.repository.dataWorker.reader;

import com.mjc.school.repository.dataTypes.Author;
import com.mjc.school.repository.interfaces.DataReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorsReader implements DataReader<Author> {

    @Override
    public List<Author> read() {
        List<Author> authors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("author.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                authors.add(new Author(line));
            }

        } catch (IOException e) {
            System.err.println("IO exception in readAuthors()");
        }

        return authors;
    }
}
