package com.mjc.school.repository.impl;

import com.mjc.school.repository.exceptions.AuthorNotFoundException;
import com.mjc.school.repository.exceptions.NewsNotFoundException;
import com.mjc.school.repository.interfaces.Repository;
import com.mjc.school.repository.models.AuthorModel;
import com.mjc.school.repository.models.NewsModel;
import com.mjc.school.repository.utils.DataSource;

import java.util.List;
import java.util.Optional;

public class NewsRepository implements Repository<NewsModel> {
    private final DataSource dataSource;

    public NewsRepository() {
        dataSource = DataSource.getInstance();
    }

    private boolean isAuthorExists(long authorId) {
        return dataSource.getAuthors().stream().map(AuthorModel::getId).anyMatch(a -> a == authorId);
    }


    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNews();
    }

    @Override
    public NewsModel readById(Long id) throws NewsNotFoundException {
        Optional<NewsModel> newsOptional = this.dataSource.getNews().stream().filter(a -> a.getId() == id).findFirst();
        if (newsOptional.isPresent()) {
            return newsOptional.get();
        } else throw new NewsNotFoundException();
    }

    @Override
    public NewsModel create(NewsModel news) throws AuthorNotFoundException {
        NewsModel createdNews;
        if (isAuthorExists(news.getAuthorId())) {
            createdNews = dataSource.getNewsFactory().createNews(news.getTitle(), news.getContent(), news.getAuthorId());
            this.dataSource.getNews().add(createdNews);
            return createdNews;
        } else {
            throw new AuthorNotFoundException();
        }
    }

    @Override
    public NewsModel update(NewsModel news) throws AuthorNotFoundException, NewsNotFoundException {

        if (!isAuthorExists(news.getAuthorId())) throw new AuthorNotFoundException();

        Optional<NewsModel> optionalNews = this.dataSource.getNews().stream()
                .filter(a -> a.getId() == news.getId())
                .findFirst();

        if (optionalNews.isPresent()) {
            return dataSource.getNewsFactory().updateNews(optionalNews.get(), news);
        } else throw new NewsNotFoundException();

    }

    @Override
    public Boolean deleteById(Long id) {
        Optional<NewsModel> newsOptional = this.dataSource.getNews().stream().filter(a -> a.getId() == id).findFirst();
        if (newsOptional.isPresent()) {
            NewsModel newsToDelete = newsOptional.get();
            return this.dataSource.getNews().remove(newsToDelete);
        } else return false;
    }


}
