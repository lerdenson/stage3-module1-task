package com.mjc.school.service;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.dataTypes.News;
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

    Repository<News> repository;
    NewsValidator validator;

    public NewsService() {
        repository = new NewsRepository();
        validator = new NewsValidator();
    }

    @Override
    public List<NewsResponseDTO> findAll() {
        return NewsMapper.INSTANCE.convert(repository.findAll());
    }

    @Override
    public NewsResponseDTO findById(long id) throws NotFoundException {
        try {
            return NewsMapper
                    .INSTANCE
                    .convert(repository.findById(id));
        } catch (NewsNotFoundException e) {
            throw new NotFoundException(String.format(ErrorCodeMessage.NEWS_NOT_FOUND.toString(), id));
        }


    }

    @Override
    public NewsResponseDTO create(NewsRequestDTO in) throws ValidationException, NotFoundException {
        try {
            validator.validateNews(in);
            return NewsMapper.INSTANCE.convert(
                    repository.add(NewsMapper.INSTANCE.convertRequest(in))
            );
        } catch (AuthorNotFoundException e) {
            throw new NotFoundException(String.format(ErrorCodeMessage.AUTHOR_NOT_FOUND.toString(), in.getAuthorId()));
        }
    }

    @Override
    public NewsResponseDTO update(NewsRequestDTO in) throws ValidationException, NotFoundException {
        try {
            validator.validateNews(in);
            return NewsMapper.INSTANCE.convert(
                    repository.update(NewsMapper.INSTANCE.convertRequest(in))
            );
        } catch (AuthorNotFoundException e) {
            throw new NotFoundException(String.format(ErrorCodeMessage.AUTHOR_NOT_FOUND.toString(), in.getAuthorId()));
        } catch (NewsNotFoundException e) {
            throw new NotFoundException(String.format(ErrorCodeMessage.NEWS_NOT_FOUND.toString(), in.getId()));
        }

    }

    @Override
    public boolean deleteById(long id) {
        return repository.deleteById(id);
    }
}
