package com.mjc.school.controller;

import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;

import java.util.List;

public interface Controller<I, O> {
    List<O> findAll();

    O findById(long id) throws NotFoundException;

    O create(I in) throws ValidationException, NotFoundException;

    O update(I in) throws ValidationException, NotFoundException;

    boolean delete(long id);
}
