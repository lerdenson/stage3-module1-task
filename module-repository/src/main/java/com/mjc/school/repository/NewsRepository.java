package com.mjc.school.repository;

import com.mjc.school.repository.dataTypes.Author;
import com.mjc.school.repository.dataTypes.News;
import com.mjc.school.repository.dataWorker.DataSource;
import com.mjc.school.repository.exceptions.NotFoundException;
import com.mjc.school.repository.exceptions.WrongCredentialsException;
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
        return dataSource.getAuthors().stream().map(Author::getId).anyMatch(a -> a == authorId);
    }


    @Override
    public List<News> findAll() {
        return dataSource.getNews();
    }

    @Override
    public News add(News news) throws WrongCredentialsException {
        if (isAuthorExists(news.getAuthorId())) {
            News createdNews = new News(news.getTitle(), news.getContent(), news.getAuthorId());
            this.dataSource.getNews().add(news);
            return createdNews;
        } else {
            throw new WrongCredentialsException("Wrong author ID. there is no author with ID=" + news.getId());
        }
    }

    @Override
    public News update(News news) throws WrongCredentialsException {

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

            } else {
                throw new WrongCredentialsException("Wrong news ID. there is no news with ID=" + news.getId());
            }

        } else {
            throw new WrongCredentialsException("Wrong author ID. there is no author with ID=" + news.getId());
        }
    }

    @Override
    public boolean deleteById(long id) {
        Optional<News> newsOptional = this.dataSource.getNews().stream().filter(a -> a.getId() == id).findFirst();
        if (newsOptional.isPresent()) {
            News newsToDelete = newsOptional.get();
            return this.dataSource.getNews().remove(newsToDelete);
        } else return false;
    }

    @Override
    public News findById(long id) throws NotFoundException {
        Optional<News> newsOptional = this.dataSource.getNews().stream().filter(a -> a.getId() == id).findFirst();
        if (newsOptional.isPresent()) {
            return newsOptional.get();
        } else throw new NotFoundException("Wrong news ID. there is no news with ID=" + id);
    }
}
