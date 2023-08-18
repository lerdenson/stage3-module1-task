package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.models.NewsModel;
import com.mjc.school.repository.exceptions.AuthorNotFoundException;
import com.mjc.school.repository.exceptions.NewsNotFoundException;
import com.mjc.school.repository.interfaces.Repository;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import com.mjc.school.service.exceptions.ErrorCodeMessage;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;
import com.mjc.school.service.interfaces.Service;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validator.NewsValidator;

import java.util.List;

public class NewsService implements Service<NewsRequestDTO, NewsResponseDTO> {

    private final Repository<NewsModel> NewsRepository;
    private final NewsValidator newsValidator = new NewsValidator();

    public NewsService() {
        NewsRepository = new NewsRepository();
    }

    @Override
    public List<NewsResponseDTO> readAll() {
        return NewsMapper.INSTANCE.convert(NewsRepository.readAll());
    }

    @Override
    public NewsResponseDTO readById(Long id) throws NotFoundException {
        try {
            return NewsMapper
                    .INSTANCE
                    .convert(NewsRepository.readById(id));
        } catch (NewsNotFoundException e) {
            throw new NotFoundException(String.format(ErrorCodeMessage.NEWS_NOT_FOUND.toString(), id));
        }


    }

    @Override
    public NewsResponseDTO create(NewsRequestDTO in) throws ValidationException, NotFoundException {
        try {
            newsValidator.validateNews(in);
            return NewsMapper.INSTANCE.convert(
                    NewsRepository.create(NewsMapper.INSTANCE.convertRequest(in))
            );
        } catch (AuthorNotFoundException e) {
            throw new NotFoundException(String.format(ErrorCodeMessage.AUTHOR_NOT_FOUND.toString(), in.getAuthorId()));
        }
    }

    @Override
    public NewsResponseDTO update(NewsRequestDTO in) throws ValidationException, NotFoundException {
        try {
            newsValidator.validateNews(in);
            return NewsMapper.INSTANCE.convert(
                    NewsRepository.update(NewsMapper.INSTANCE.convertRequest(in))
            );
        } catch (AuthorNotFoundException e) {
            throw new NotFoundException(String.format(ErrorCodeMessage.AUTHOR_NOT_FOUND.toString(), in.getAuthorId()));
        } catch (NewsNotFoundException e) {
            throw new NotFoundException(String.format(ErrorCodeMessage.NEWS_NOT_FOUND.toString(), in.getId()));
        }

    }

    @Override
    public Boolean deleteById(Long id) {
        return NewsRepository.deleteById(id);
    }
}
