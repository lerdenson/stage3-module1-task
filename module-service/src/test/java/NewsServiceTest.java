import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.impl.NewsService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidationException;
import com.mjc.school.service.interfaces.Service;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsServiceTest {

    long getRandomNewsId(Service<NewsDtoRequest, NewsDtoResponse> service) {
        List<NewsDtoResponse> newsList = service.readAll();
        return newsList.get((int) (Math.random() * newsList.size())).getId();
    }

    @Test
    void findAllReturnsNonEmptyListTest() {
        Service<NewsDtoRequest, NewsDtoResponse> service = new NewsService();
        List<NewsDtoResponse> responseList = service.readAll();

        assertFalse(responseList.isEmpty());
    }

    @Test
    void findByIdReturnsCorrectNewsTest() {
        Service<NewsDtoRequest, NewsDtoResponse> service = new NewsService();
        List<NewsDtoResponse> responseList = service.readAll();

        NewsDtoResponse expectedNews = responseList.get((int) (Math.random() * responseList.size()));
        try {
            NewsDtoResponse responseNews = service.readById(expectedNews.getId());

            assertEquals(expectedNews.getTitle(), responseNews.getTitle());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void findIdWithWrongIdThrowsNotFoundException() {
        Service<NewsDtoRequest, NewsDtoResponse> service = new NewsService();
        Exception exception = assertThrows(NotFoundException.class, () -> service.readById(207L));

        String expectedErrorCode = "ERROR CODE: 01";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void createReturnsCorrectNewsTest() {
        Service<NewsDtoRequest, NewsDtoResponse> service = new NewsService();
        NewsDtoRequest newsDto = new NewsDtoRequest("MY TITLE", "MY CONTENT", 7);
        try {
            NewsDtoResponse response = service.create(newsDto);

            assertEquals(newsDto.getTitle(), response.getTitle());
        } catch (ValidationException | NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void createAddNewElementTest() {
        Service<NewsDtoRequest, NewsDtoResponse> service = new NewsService();
        NewsDtoRequest newsDto = new NewsDtoRequest("MY TITLE!", "MY CONTENT!", 11);
        try {
            service.create(newsDto);
            List<NewsDtoResponse> responseList = service.readAll();

            assertTrue(responseList.stream()
                    .map(NewsDtoResponse::getTitle)
                    .anyMatch(a -> a.equals(newsDto.getTitle())));
        } catch (ValidationException | NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void createWithWrongDataThrowsValidationExceptionTest() {
        Service<NewsDtoRequest, NewsDtoResponse> service = new NewsService();
        NewsDtoRequest badNews = new NewsDtoRequest("A", "MY CONTENT!", 11);

        Exception exception = assertThrows(ValidationException.class, () -> service.create(badNews));

        String expectedErrorCode = "ERROR CODE: 03";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void createWithWrongAuthorIdThrowsValidationExceptionTest() {
        Service<NewsDtoRequest, NewsDtoResponse> service = new NewsService();
        NewsDtoRequest badNews = new NewsDtoRequest("CLIMBING", "MY CONTENT!", 117);

        Exception exception = assertThrows(NotFoundException.class, () -> service.create(badNews));

        String expectedErrorCode = "ERROR CODE: 02";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void updateReturnsCorrectValueTest() {
        Service<NewsDtoRequest, NewsDtoResponse> service = new NewsService();
        long id = getRandomNewsId(service);
        NewsDtoRequest newsDto = new NewsDtoRequest(id, "MY TITLE!", "MY CONTENT!", 11);

        try {
            NewsDtoResponse response = service.update(newsDto);

            assertEquals(newsDto.getTitle(), response.getTitle());
        } catch (ValidationException | NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    void updateChangeElementTest() {
        Service<NewsDtoRequest, NewsDtoResponse> service = new NewsService();
        long id = getRandomNewsId(service);
        NewsDtoRequest newsDto = new NewsDtoRequest(id, "MY TITLE!!!!", "MY CONTENT!!!!", 11);
        try {
            NewsDtoResponse response = service.update(newsDto);
            NewsDtoResponse updatedNews = service.readById(newsDto.getId());

            assertEquals(response, updatedNews);
        } catch (ValidationException | NotFoundException e) {
            e.printStackTrace();
        }


    }

    @Test
    void updateWithWrongDataThrowsValidationExceptionTest() {
        Service<NewsDtoRequest, NewsDtoResponse> service = new NewsService();
        long id = getRandomNewsId(service);

        NewsDtoRequest badNews = new NewsDtoRequest(id, "LALALA", "ab", 11);

        Exception exception = assertThrows(ValidationException.class, () -> service.create(badNews));

        String expectedErrorCode = "ERROR CODE: 04";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void updateWithIncorrectAuthorIdThrowsValidationException() {
        Service<NewsDtoRequest, NewsDtoResponse> service = new NewsService();
        long id = getRandomNewsId(service);

        NewsDtoRequest badNews = new NewsDtoRequest(id, "LALALA", "SASASASASAS", 209);

        Exception exception = assertThrows(NotFoundException.class, () -> service.create(badNews));

        String expectedErrorCode = "ERROR CODE: 02";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }

    @Test
    void updateWithIncorrectIdThrowsValidationException() {
        Service<NewsDtoRequest, NewsDtoResponse> service = new NewsService();

        NewsDtoRequest badNews = new NewsDtoRequest(254, "LALALA", "SASASASASAS", 6);

        Exception exception = assertThrows(NotFoundException.class, () -> service.update(badNews));

        String expectedErrorCode = "ERROR CODE: 01";
        assertTrue(exception.getMessage().contains(expectedErrorCode));
    }


    @Test
    void deleteRemovesElementTest() {
        Service<NewsDtoRequest, NewsDtoResponse> service = new NewsService();
        long id = getRandomNewsId(service);
        boolean isRemoved = service.deleteById(id);
        List<NewsDtoResponse> responseDTOList = service.readAll();

        assertTrue(isRemoved);
        assertFalse(responseDTOList.stream().map(NewsDtoResponse::getId).anyMatch(a -> a == id));
    }

    @Test
    void deleteReturnsFalseWithIncorrectIdTest() {
        Service<NewsDtoRequest, NewsDtoResponse> service = new NewsService();
        int sizeBeforeDelete = service.readAll().size();
        boolean isRemoved = service.deleteById(123L);
        int sizeAfterDelete = service.readAll().size();

        assertFalse(isRemoved);
        assertEquals(sizeBeforeDelete, sizeAfterDelete);
    }
}
