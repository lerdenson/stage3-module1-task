package com.mjc.school.service.impl;

import com.mjc.school.repository.exceptions.AuthorNotFoundException;
import com.mjc.school.repository.exceptions.NewsNotFoundException;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.interfaces.Repository;
import com.mjc.school.repository.models.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.ErrorCodeMessage;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;
import com.mjc.school.service.interfaces.Service;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validator.NewsValidator;

import java.util.List;

public class NewsService implements Service<NewsDtoRequest, NewsDtoResponse> {

    private final Repository<NewsModel> newsRepository;
    private final NewsValidator newsValidator = new NewsValidator();

    public NewsService() {
        newsRepository = new NewsRepository();
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return NewsMapper.INSTANCE.convert(newsRepository.readAll());
    }

    @Override
    public NewsDtoResponse readById(Long id) throws NotFoundException {
        try {
            return NewsMapper
                    .INSTANCE
                    .convert(newsRepository.readById(id));
        } catch (NewsNotFoundException e) {
            throw new NotFoundException(String.format(ErrorCodeMessage.NEWS_NOT_FOUND.toString(), id));
        }


    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest in) throws ValidationException, NotFoundException {
        try {
            newsValidator.validateNews(in);
            return NewsMapper.INSTANCE.convert(
                    newsRepository.create(NewsMapper.INSTANCE.convertRequest(in))
            );
        } catch (AuthorNotFoundException e) {
            throw new NotFoundException(String.format(ErrorCodeMessage.AUTHOR_NOT_FOUND.toString(), in.getAuthorId()));
        }
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest in) throws ValidationException, NotFoundException {
        try {
            newsValidator.validateNews(in);
            return NewsMapper.INSTANCE.convert(
                    newsRepository.update(NewsMapper.INSTANCE.convertRequest(in))
            );
        } catch (AuthorNotFoundException e) {
            throw new NotFoundException(String.format(ErrorCodeMessage.AUTHOR_NOT_FOUND.toString(), in.getAuthorId()));
        } catch (NewsNotFoundException e) {
            throw new NotFoundException(String.format(ErrorCodeMessage.NEWS_NOT_FOUND.toString(), in.getId()));
        }

    }

    @Override
    public Boolean deleteById(Long id) {
        return newsRepository.deleteById(id);
    }
}
