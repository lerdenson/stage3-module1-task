package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.exceptions.AuthorNotFoundException;
import com.mjc.school.repository.exceptions.NewsNotFoundException;

import java.util.List;

public interface Repository<T> {
    List<T> readAll();

    T create(T t) throws AuthorNotFoundException;

    T update(T t) throws AuthorNotFoundException, NewsNotFoundException;

    Boolean deleteById(Long id);

    T readById(Long id) throws NewsNotFoundException;
}
