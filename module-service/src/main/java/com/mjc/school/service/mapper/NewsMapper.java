package com.mjc.school.service.mapper;

import com.mjc.school.repository.dataTypes.News;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsResponseDTO convert(News news);

    List<NewsResponseDTO> convert(List<News> newsList);

    default News convertRequest(NewsRequestDTO newsRequest) {
        return News.builder()
                .id(newsRequest.getId())
                .title(newsRequest.getTitle())
                .content(newsRequest.getContent())
                .build();
    }


}
