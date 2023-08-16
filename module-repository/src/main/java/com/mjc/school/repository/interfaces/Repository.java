package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.exceptions.NotFoundException;
import com.mjc.school.repository.exceptions.WrongCredentialsException;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();
    T add(T t) throws WrongCredentialsException;
    T update(T t) throws WrongCredentialsException;
    boolean deleteById(long id);
    T findById(long id) throws NotFoundException;
}
