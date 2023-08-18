package com.mjc.school.service.validator;

import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.exceptions.ErrorCodeMessage;
import com.mjc.school.service.exceptions.ValidationException;

public class NewsValidator {
    public void validateNews(NewsRequestDto newsDTO) throws ValidationException{
        validateTitle(newsDTO.getTitle());
        validateContent(newsDTO.getContent());
        validateAuthorId(newsDTO.getId());
    }

    private void validateTitle(String title) throws ValidationException{
        if(!(title.length() >= 5 && title.length() <= 30)) {
            throw new ValidationException(String.format(ErrorCodeMessage.INCORRECT_NEWS_TITLE.toString(), title.length()));
        }
    }

    private void validateContent(String content) throws ValidationException{
        if(!(content.length() >= 5 && content.length() <= 255)) {
            throw new ValidationException(String.format(ErrorCodeMessage.INCORRECT_NEWS_CONTENT.toString(), content.length()));
        }
    }

    private void validateAuthorId(Long id) throws ValidationException{
        if (id == null) {
            throw new ValidationException(ErrorCodeMessage.NULL_AUTHOR_ID.toString());
        }
    }
}
