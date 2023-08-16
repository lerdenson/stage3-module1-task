package com.mjc.school.controller;

import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import com.mjc.school.service.interfaces.Service;

import java.util.List;

public class NewsController {
    private final Service<NewsRequestDTO, NewsResponseDTO> service = new NewsService();

    public List<NewsResponseDTO> findAll() {
        return service.findAll();
    }

    public NewsResponseDTO findById(long id) {
        return service.findById(id);
    }

    public NewsResponseDTO create(NewsRequestDTO newsDTO) {
        return service.create(newsDTO);
    }

    public NewsResponseDTO update(NewsRequestDTO newsDTO) {
        return service.update(newsDTO);
    }

    public boolean delete(long id) {
        return service.deleteById(id);
    }

}
