package com.mjc.school.service.mapper;

import com.mjc.school.repository.models.NewsModel;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsResponseDTO convert(NewsModel news);

    List<NewsResponseDTO> convert(List<NewsModel> newsList);

    default NewsModel convertRequest(NewsRequestDTO newsRequest) {
        return NewsModel.builder()
                .id(newsRequest.getId())
                .title(newsRequest.getTitle())
                .content(newsRequest.getContent())
                .authorId(newsRequest.getAuthorId())
                .build();
    }


}
