package com.mjc.school.repository.interfaces;

import java.util.List;

public interface DataReader<T> {
    List<T> read();
}
