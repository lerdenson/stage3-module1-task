package com.mjc.school.controller.interfaces;

import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;

import java.util.List;

public interface Controller<I, O> {
    List<O> readAll();

    O readById(Long id) throws NotFoundException;

    O create(I in) throws ValidationException, NotFoundException;

    O update(I in) throws ValidationException, NotFoundException;

    Boolean delete(Long id);
}
