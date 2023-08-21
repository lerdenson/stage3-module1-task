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
    private final Service<NewsDtoRequest, NewsDtoResponse> newsService;

    public NewsController() {
        newsService = new NewsService();
    }

    public List<NewsDtoResponse> readAll() {
        return newsService.readAll();
    }

    public NewsDtoResponse readById(Long id) throws NotFoundException {
        return newsService.readById(id);
    }

    public NewsDtoResponse create(NewsDtoRequest newsDto) throws ValidationException, NotFoundException {
        return newsService.create(newsDto);
    }

    public NewsDtoResponse update(NewsDtoRequest newsDto) throws ValidationException, NotFoundException {
        return newsService.update(newsDto);
    }

    public Boolean delete(Long id) {
        return newsService.deleteById(id);
    }

}
