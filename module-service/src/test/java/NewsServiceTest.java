import com.mjc.school.service.impl.NewsService;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;
import com.mjc.school.service.interfaces.Service;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NewsServiceTest {

    long getRandomNewsId(Service<NewsRequestDTO, NewsResponseDTO> service) {
        List<NewsResponseDTO> newsList = service.readAll();
        return newsList.get((int) (Math.random() * newsList.size())).getId();
    }

    @Test
    void findAllReturnsNonEmptyListTest() {
        Service<NewsRequestDTO, NewsResponseDTO> service = new NewsService();
        List<NewsResponseDTO> responseList = service.readAll();

        assertFalse(responseList.isEmpty());
    }

    @Test
    void findByIdReturnsCorrectNewsTest() {
        Service<NewsRequestDTO, NewsResponseDTO> service = new NewsService();
        List<NewsResponseDTO> responseList = service.readAll();

        NewsResponseDTO expectedNews = responseList.get((int) (Math.random() * responseList.size()));
        try {
            NewsResponseDTO responseNews = service.readById(expectedNews.getId());

            assertEquals(expectedNews.getTitle(), responseNews.getTitle());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void findIdWithWrongIdThrowsNotFoundException() {
        Service<NewsRequestDTO, NewsResponseDTO> service = new NewsService();
        Exception exception = assertThrows(NotFoundException.class, () -> service.readById(207L));

        String expectedErrorCode = "ERROR CODE: 01";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void createReturnsCorrectNewsTest() {
        Service<NewsRequestDTO, NewsResponseDTO> service = new NewsService();
        NewsRequestDTO newsDto = new NewsRequestDTO("MY TITLE", "MY CONTENT", 7);
        try {
            NewsResponseDTO response = service.create(newsDto);

            assertEquals(newsDto.getTitle(), response.getTitle());
        } catch (ValidationException | NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void createAddNewElementTest() {
        Service<NewsRequestDTO, NewsResponseDTO> service = new NewsService();
        NewsRequestDTO newsDto = new NewsRequestDTO("MY TITLE!", "MY CONTENT!", 11);
        try {
            service.create(newsDto);
            List<NewsResponseDTO> responseList = service.readAll();

            assertTrue(responseList.stream()
                    .map(NewsResponseDTO::getTitle)
                    .anyMatch(a -> a.equals(newsDto.getTitle())));
        } catch (ValidationException | NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void createWithWrongDataThrowsValidationExceptionTest() {
        Service<NewsRequestDTO, NewsResponseDTO> service = new NewsService();
        NewsRequestDTO badNews = new NewsRequestDTO("A", "MY CONTENT!", 11);

        Exception exception = assertThrows(ValidationException.class, () -> service.create(badNews));

        String expectedErrorCode = "ERROR CODE: 03";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void createWithWrongAuthorIdThrowsValidationExceptionTest() {
        Service<NewsRequestDTO, NewsResponseDTO> service = new NewsService();
        NewsRequestDTO badNews = new NewsRequestDTO("CLIMBING", "MY CONTENT!", 117);

        Exception exception = assertThrows(NotFoundException.class, () -> service.create(badNews));

        String expectedErrorCode = "ERROR CODE: 02";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void updateReturnsCorrectValueTest() {
        Service<NewsRequestDTO, NewsResponseDTO> service = new NewsService();
        long id = getRandomNewsId(service);
        NewsRequestDTO newsDto = new NewsRequestDTO(id, "MY TITLE!", "MY CONTENT!", 11);

        try {
            NewsResponseDTO response = service.update(newsDto);

            assertEquals(newsDto.getTitle(), response.getTitle());
        } catch (ValidationException | NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void updateChangeElementTest() {
        Service<NewsRequestDTO, NewsResponseDTO> service = new NewsService();
        long id = getRandomNewsId(service);
        NewsRequestDTO newsDto = new NewsRequestDTO(id, "MY TITLE!!!!", "MY CONTENT!!!!", 11);
        try {
            NewsResponseDTO response = service.update(newsDto);
            NewsResponseDTO updatedNews = service.readById(newsDto.getId());

            assertEquals(response, updatedNews);
        } catch (ValidationException | NotFoundException e) {
            e.printStackTrace();
        }


    }

    @Test
    void updateWithWrongDataThrowsValidationExceptionTest() {
        Service<NewsRequestDTO, NewsResponseDTO> service = new NewsService();
        long id = getRandomNewsId(service);

        NewsRequestDTO badNews = new NewsRequestDTO(id, "LALALA", "ab", 11);

        Exception exception = assertThrows(ValidationException.class, () -> service.create(badNews));

        String expectedErrorCode = "ERROR CODE: 04";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void updateWithIncorrectAuthorIdThrowsValidationException() {
        Service<NewsRequestDTO, NewsResponseDTO> service = new NewsService();
        long id = getRandomNewsId(service);

        NewsRequestDTO badNews = new NewsRequestDTO(id, "LALALA", "SASASASASAS", 209);

        Exception exception = assertThrows(NotFoundException.class, () -> service.create(badNews));

        String expectedErrorCode = "ERROR CODE: 02";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void updateWithIncorrectIdThrowsValidationException() {
        Service<NewsRequestDTO, NewsResponseDTO> service = new NewsService();

        NewsRequestDTO badNews = new NewsRequestDTO(254, "LALALA", "SASASASASAS", 6);

        Exception exception = assertThrows(NotFoundException.class, () -> service.update(badNews));

        String expectedErrorCode = "ERROR CODE: 01";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }


    @Test
    void deleteRemovesElementTest() {
        Service<NewsRequestDTO, NewsResponseDTO> service = new NewsService();
        long id = getRandomNewsId(service);
        boolean isRemoved = service.deleteById(id);
        List<NewsResponseDTO> responseDTOList = service.readAll();

        assertTrue(isRemoved);
        assertFalse(responseDTOList.stream().map(NewsResponseDTO::getId).anyMatch(a -> a == id));
    }

    @Test
    void deleteReturnsFalseWithIncorrectIdTest() {
        Service<NewsRequestDTO, NewsResponseDTO> service = new NewsService();
        int sizeBeforeDelete = service.readAll().size();
        boolean isRemoved = service.deleteById(123L);
        int sizeAfterDelete = service.readAll().size();

        assertFalse(isRemoved);
        assertEquals(sizeBeforeDelete, sizeAfterDelete);
    }
}
