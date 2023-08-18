import com.mjc.school.service.impl.NewsService;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;
import com.mjc.school.service.interfaces.Service;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NewsServiceTest {

    long getRandomNewsId(Service<NewsRequestDto, NewsResponseDto> service) {
        List<NewsResponseDto> newsList = service.readAll();
        return newsList.get((int) (Math.random() * newsList.size())).getId();
    }

    @Test
    void findAllReturnsNonEmptyListTest() {
        Service<NewsRequestDto, NewsResponseDto> service = new NewsService();
        List<NewsResponseDto> responseList = service.readAll();

        assertFalse(responseList.isEmpty());
    }

    @Test
    void findByIdReturnsCorrectNewsTest() {
        Service<NewsRequestDto, NewsResponseDto> service = new NewsService();
        List<NewsResponseDto> responseList = service.readAll();

        NewsResponseDto expectedNews = responseList.get((int) (Math.random() * responseList.size()));
        try {
            NewsResponseDto responseNews = service.readById(expectedNews.getId());

            assertEquals(expectedNews.getTitle(), responseNews.getTitle());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void findIdWithWrongIdThrowsNotFoundException() {
        Service<NewsRequestDto, NewsResponseDto> service = new NewsService();
        Exception exception = assertThrows(NotFoundException.class, () -> service.readById(207L));

        String expectedErrorCode = "ERROR CODE: 01";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void createReturnsCorrectNewsTest() {
        Service<NewsRequestDto, NewsResponseDto> service = new NewsService();
        NewsRequestDto newsDto = new NewsRequestDto("MY TITLE", "MY CONTENT", 7);
        try {
            NewsResponseDto response = service.create(newsDto);

            assertEquals(newsDto.getTitle(), response.getTitle());
        } catch (ValidationException | NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void createAddNewElementTest() {
        Service<NewsRequestDto, NewsResponseDto> service = new NewsService();
        NewsRequestDto newsDto = new NewsRequestDto("MY TITLE!", "MY CONTENT!", 11);
        try {
            service.create(newsDto);
            List<NewsResponseDto> responseList = service.readAll();

            assertTrue(responseList.stream()
                    .map(NewsResponseDto::getTitle)
                    .anyMatch(a -> a.equals(newsDto.getTitle())));
        } catch (ValidationException | NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void createWithWrongDataThrowsValidationExceptionTest() {
        Service<NewsRequestDto, NewsResponseDto> service = new NewsService();
        NewsRequestDto badNews = new NewsRequestDto("A", "MY CONTENT!", 11);

        Exception exception = assertThrows(ValidationException.class, () -> service.create(badNews));

        String expectedErrorCode = "ERROR CODE: 03";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void createWithWrongAuthorIdThrowsValidationExceptionTest() {
        Service<NewsRequestDto, NewsResponseDto> service = new NewsService();
        NewsRequestDto badNews = new NewsRequestDto("CLIMBING", "MY CONTENT!", 117);

        Exception exception = assertThrows(NotFoundException.class, () -> service.create(badNews));

        String expectedErrorCode = "ERROR CODE: 02";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void updateReturnsCorrectValueTest() {
        Service<NewsRequestDto, NewsResponseDto> service = new NewsService();
        long id = getRandomNewsId(service);
        NewsRequestDto newsDto = new NewsRequestDto(id, "MY TITLE!", "MY CONTENT!", 11);

        try {
            NewsResponseDto response = service.update(newsDto);

            assertEquals(newsDto.getTitle(), response.getTitle());
        } catch (ValidationException | NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void updateChangeElementTest() {
        Service<NewsRequestDto, NewsResponseDto> service = new NewsService();
        long id = getRandomNewsId(service);
        NewsRequestDto newsDto = new NewsRequestDto(id, "MY TITLE!!!!", "MY CONTENT!!!!", 11);
        try {
            NewsResponseDto response = service.update(newsDto);
            NewsResponseDto updatedNews = service.readById(newsDto.getId());

            assertEquals(response, updatedNews);
        } catch (ValidationException | NotFoundException e) {
            e.printStackTrace();
        }


    }

    @Test
    void updateWithWrongDataThrowsValidationExceptionTest() {
        Service<NewsRequestDto, NewsResponseDto> service = new NewsService();
        long id = getRandomNewsId(service);

        NewsRequestDto badNews = new NewsRequestDto(id, "LALALA", "ab", 11);

        Exception exception = assertThrows(ValidationException.class, () -> service.create(badNews));

        String expectedErrorCode = "ERROR CODE: 04";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void updateWithIncorrectAuthorIdThrowsValidationException() {
        Service<NewsRequestDto, NewsResponseDto> service = new NewsService();
        long id = getRandomNewsId(service);

        NewsRequestDto badNews = new NewsRequestDto(id, "LALALA", "SASASASASAS", 209);

        Exception exception = assertThrows(NotFoundException.class, () -> service.create(badNews));

        String expectedErrorCode = "ERROR CODE: 02";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void updateWithIncorrectIdThrowsValidationException() {
        Service<NewsRequestDto, NewsResponseDto> service = new NewsService();

        NewsRequestDto badNews = new NewsRequestDto(254, "LALALA", "SASASASASAS", 6);

        Exception exception = assertThrows(NotFoundException.class, () -> service.update(badNews));

        String expectedErrorCode = "ERROR CODE: 01";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }


    @Test
    void deleteRemovesElementTest() {
        Service<NewsRequestDto, NewsResponseDto> service = new NewsService();
        long id = getRandomNewsId(service);
        boolean isRemoved = service.deleteById(id);
        List<NewsResponseDto> responseDTOList = service.readAll();

        assertTrue(isRemoved);
        assertFalse(responseDTOList.stream().map(NewsResponseDto::getId).anyMatch(a -> a == id));
    }

    @Test
    void deleteReturnsFalseWithIncorrectIdTest() {
        Service<NewsRequestDto, NewsResponseDto> service = new NewsService();
        int sizeBeforeDelete = service.readAll().size();
        boolean isRemoved = service.deleteById(123L);
        int sizeAfterDelete = service.readAll().size();

        assertFalse(isRemoved);
        assertEquals(sizeBeforeDelete, sizeAfterDelete);
    }
}
