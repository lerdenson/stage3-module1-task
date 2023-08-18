package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.exceptions.NewsNotFoundException;
import com.mjc.school.repository.exceptions.AuthorNotFoundException;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();
    T add(T t) throws AuthorNotFoundException;
    T update(T t) throws AuthorNotFoundException, NewsNotFoundException;
    boolean deleteById(long id);
    T findById(long id) throws NewsNotFoundException;
}
