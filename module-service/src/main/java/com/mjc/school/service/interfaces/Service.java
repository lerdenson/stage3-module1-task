package com.mjc.school.service.interfaces;

import com.mjc.school.repository.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;

import java.util.List;

public interface Service<I, O> {
    List<O> findAll();

    O findById(long id) throws NotFoundException;

    O create(I in) throws ValidationException;

    O update(I in) throws ValidationException;

    boolean deleteById(long id);

}
