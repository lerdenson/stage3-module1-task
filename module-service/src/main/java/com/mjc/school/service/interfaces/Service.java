package com.mjc.school.service.interfaces;

import java.util.List;

public interface Service<I, O> {
    List<O> findAll();
    O findById(long id);
    O create(I in);
    O update(I in);
    boolean deleteById(long id);

}
