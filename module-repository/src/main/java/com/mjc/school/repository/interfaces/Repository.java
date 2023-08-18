package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.exceptions.NewsNotFoundException;
import com.mjc.school.repository.exceptions.AuthorNotFoundException;

import java.util.List;

public interface Repository<T> {
    List<T> readAll();
    T create(T t) throws AuthorNotFoundException;
    T update(T t) throws AuthorNotFoundException, NewsNotFoundException;
    Boolean deleteById(long id);
    T readById(long id) throws NewsNotFoundException;
}
