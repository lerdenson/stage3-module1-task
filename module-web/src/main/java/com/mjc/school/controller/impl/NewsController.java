package com.mjc.school.controller.impl;

import com.mjc.school.controller.interfaces.Controller;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.impl.NewsService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;
import com.mjc.school.service.interfaces.Service;

import java.util.List;

public class NewsController implements Controller<NewsDtoRequest, NewsDtoResponse> {
    private final Service<NewsDtoRequest, NewsDtoResponse> NewsService;

    public NewsController() {
        NewsService = new NewsService();
    }

    public List<NewsDtoResponse> readAll() {
        return NewsService.readAll();
    }

    public NewsDtoResponse readById(Long id) throws NotFoundException {
        return NewsService.readById(id);
    }

    public NewsDtoResponse create(NewsDtoRequest newsDTO) throws ValidationException, NotFoundException {
        return NewsService.create(newsDTO);
    }

    public NewsDtoResponse update(NewsDtoRequest newsDTO) throws ValidationException, NotFoundException {
        return NewsService.update(newsDTO);
    }

    public Boolean delete(Long id) {
        return NewsService.deleteById(id);
    }

}
