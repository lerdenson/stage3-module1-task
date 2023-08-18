package com.mjc.school.service.interfaces;

import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;

import java.util.List;

public interface Service<I, O> {
    List<O> readAll();

    O readById(long id) throws NotFoundException;

    O create(I in) throws ValidationException, NotFoundException;

    O update(I in) throws ValidationException, NotFoundException;

    Boolean deleteById(Long id);

}
