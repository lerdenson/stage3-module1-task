package com.mjc.school.repository.dataWorker;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();
    void add(T t) throws WrongCredentialsException;
    T update(T t) throws WrongCredentialsException;
    void delete(T t);
}
