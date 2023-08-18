package com.mjc.school.controller.impl;

import com.mjc.school.controller.interfaces.Controller;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.impl.NewsService;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;
import com.mjc.school.service.interfaces.Service;

import java.util.List;

public class NewsController implements Controller<NewsRequestDto, NewsResponseDto> {
    private final Service<NewsRequestDto, NewsResponseDto> NewsService;

    public NewsController() {
        NewsService = new NewsService();
    }

    public List<NewsResponseDto> readAll() {
        return NewsService.readAll();
    }

    public NewsResponseDto readById(Long id) throws NotFoundException {
        return NewsService.readById(id);
    }

    public NewsResponseDto create(NewsRequestDto newsDTO) throws ValidationException, NotFoundException {
        return NewsService.create(newsDTO);
    }

    public NewsResponseDto update(NewsRequestDto newsDTO) throws ValidationException, NotFoundException {
        return NewsService.update(newsDTO);
    }

    public Boolean delete(Long id) {
        return NewsService.deleteById(id);
    }

}
