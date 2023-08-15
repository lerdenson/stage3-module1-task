package com.mjc.school.repository.dataWorker;

import com.mjc.school.repository.dataTypes.Author;
import com.mjc.school.repository.dataTypes.News;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class NewsRepository implements Repository<News> {
    private static List<News> news;
    private static List<Author> authors;
    private static NewsRepository instance;

    private NewsRepository() {
        DataReader reader = new DataReader();
        authors = reader.readAuthors();
        news = reader.readNews();
    }

    public static NewsRepository getInstance() {
        if (instance == null) {
            instance = new NewsRepository();
        }
        return instance;
    }

    private boolean isAuthorExists(News news) {
        return authors.stream().map(Author::getId).anyMatch(a -> a == news.getAuthorId());
    }


    @Override
    public List<News> findAll() {
        return news;
    }

    @Override
    public void add(News news) throws WrongCredentialsException {
        if (isAuthorExists(news)) {
            NewsRepository.news.add(news);
        } else {
            throw new WrongCredentialsException("Wrong author ID. there is no author with ID=" + news.getId());
        }
    }

    @Override
    public News update(News news) throws WrongCredentialsException {

        if (isAuthorExists(news)) {
            News n1;
            Optional<News> optional = NewsRepository.news.stream()
                    .filter(a -> a.getId() == news.getId())
                    .findFirst();

            if (optional.isPresent()) {
                n1 = optional.get();
                n1.setAuthorId(news.getAuthorId());
                n1.setTitle(news.getTitle());
                n1.setContent(news.getContent());
                n1.setLastUpdateDate(LocalDateTime.now());
                return n1;

            } else {
                throw new WrongCredentialsException("Wrong news ID. there is no news with ID=" + news.getId());
            }

        } else {
            throw new WrongCredentialsException("Wrong author ID. there is no author with ID=" + news.getId());
        }
    }

    @Override
    public void delete(News news) {
        NewsRepository.news.remove(news);
    }
}
