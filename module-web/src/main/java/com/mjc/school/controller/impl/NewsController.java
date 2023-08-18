package com.mjc.school.controller.impl;

import com.mjc.school.controller.interfaces.Controller;
import com.mjc.school.service.impl.NewsService;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;
import com.mjc.school.service.interfaces.Service;

import java.util.List;

public class NewsController implements Controller<NewsRequestDTO, NewsResponseDTO> {
    private final Service<NewsRequestDTO, NewsResponseDTO> NewsService;

    public NewsController() {
        NewsService = new NewsService();
    }

    public List<NewsResponseDTO> readAll() {
        return NewsService.readAll();
    }

    public NewsResponseDTO readById(Long id) throws NotFoundException {
        return NewsService.readById(id);
    }

    public NewsResponseDTO create(NewsRequestDTO newsDTO) throws ValidationException, NotFoundException {
        return NewsService.create(newsDTO);
    }

    public NewsResponseDTO update(NewsRequestDTO newsDTO) throws ValidationException, NotFoundException {
        return NewsService.update(newsDTO);
    }

    public Boolean delete(Long id) {
        return NewsService.deleteById(id);
    }

}
