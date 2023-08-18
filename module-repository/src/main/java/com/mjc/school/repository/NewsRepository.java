package com.mjc.school.repository;

import com.mjc.school.repository.dataTypes.Author;
import com.mjc.school.repository.dataTypes.News;
import com.mjc.school.repository.dataWorker.DataSource;
import com.mjc.school.repository.exceptions.NewsNotFoundException;
import com.mjc.school.repository.exceptions.AuthorNotFoundException;
import com.mjc.school.repository.interfaces.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class NewsRepository implements Repository<News> {
    DataSource dataSource;
    public NewsRepository() {
        dataSource = DataSource.getInstance();
    }

    private boolean isAuthorExists(long authorId) {
        return dataSource.getAuthors().stream().map(Author::getId).anyMatch(a -> a==authorId);
    }


    @Override
    public List<News> findAll() {
        return dataSource.getNews();
    }

    @Override
    public News findById(long id) throws NewsNotFoundException {
        Optional<News> newsOptional = this.dataSource.getNews().stream().filter(a -> a.getId() == id).findFirst();
        if (newsOptional.isPresent()) {
            return newsOptional.get();
        } else throw new NewsNotFoundException();
    }

    @Override
    public News add(News news) throws AuthorNotFoundException {
        News createdNews;
        if (isAuthorExists(news.getAuthorId())) {
            createdNews = new News(news.getTitle(), news.getContent(), news.getAuthorId());
            this.dataSource.getNews().add(createdNews);
            return createdNews;
        } else {
            throw new AuthorNotFoundException();
        }
    }

    @Override
    public News update(News news) throws AuthorNotFoundException, NewsNotFoundException {

        if (isAuthorExists(news.getAuthorId())) {
            Optional<News> optionalNews = this.dataSource.getNews().stream()
                    .filter(a -> a.getId() == news.getId())
                    .findFirst();

            if (optionalNews.isPresent()) {
                News updatedNews = optionalNews.get();
                updatedNews.setAuthorId(news.getAuthorId());
                updatedNews.setTitle(news.getTitle());
                updatedNews.setContent(news.getContent());
                updatedNews.setLastUpdateDate(LocalDateTime.now());
                return updatedNews;

            } else throw new NewsNotFoundException();

        } else throw new AuthorNotFoundException();
    }

    @Override
    public boolean deleteById(long id) {
        Optional<News> newsOptional = this.dataSource.getNews().stream().filter(a -> a.getId() == id).findFirst();
        if (newsOptional.isPresent()) {
            News newsToDelete = newsOptional.get();
            return this.dataSource.getNews().remove(newsToDelete);
        } else return false;
    }


}
