package com.mjc.school.service.mapper;

import com.mjc.school.repository.models.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDtoResponse convert(NewsModel news);

    List<NewsDtoResponse> convert(List<NewsModel> newsList);

    default NewsModel convertRequest(NewsDtoRequest newsRequest) {
        return NewsModel.builder()
                .id(newsRequest.getId())
                .title(newsRequest.getTitle())
                .content(newsRequest.getContent())
                .authorId(newsRequest.getAuthorId())
                .build();
    }


}
