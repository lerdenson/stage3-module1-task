package com.mjc.school.service.mapper;

import com.mjc.school.repository.models.NewsModel;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsResponseDto convert(NewsModel news);

    List<NewsResponseDto> convert(List<NewsModel> newsList);

    default NewsModel convertRequest(NewsRequestDto newsRequest) {
        return NewsModel.builder()
                .id(newsRequest.getId())
                .title(newsRequest.getTitle())
                .content(newsRequest.getContent())
                .authorId(newsRequest.getAuthorId())
                .build();
    }


}
