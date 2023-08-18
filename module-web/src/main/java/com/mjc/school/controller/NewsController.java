package com.mjc.school.controller;

import com.mjc.school.service.impl.NewsService;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;
import com.mjc.school.service.interfaces.Service;

import java.util.List;

public class NewsController implements Controller<NewsRequestDTO, NewsResponseDTO> {
    private final Service<NewsRequestDTO, NewsResponseDTO> service;

    public NewsController() {
        service = new NewsService();
    }

    public List<NewsResponseDTO> findAll() {
        return service.readAll();
    }

    public NewsResponseDTO findById(long id) throws NotFoundException {
        return service.readById(id);
    }

    public NewsResponseDTO create(NewsRequestDTO newsDTO) throws ValidationException, NotFoundException {
        return service.create(newsDTO);
    }

    public NewsResponseDTO update(NewsRequestDTO newsDTO) throws ValidationException, NotFoundException {
        return service.update(newsDTO);
    }

    public boolean delete(long id) {
        return service.deleteById(id);
    }

}
