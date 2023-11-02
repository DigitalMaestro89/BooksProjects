package es.usj.booksprojects;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.GetRequest;

public class GetRequestTest {
    @Test
    public void testRetrBooks() {
        GetRequest getRequest = new GetRequest();
        String searchName = "velo";
        List<Book> booksList = getRequest.retrBooks(searchName);

        assert !booksList.isEmpty() : "La liste de livres ne doit pas être vide";
        assertEquals(100, booksList.size());

    }
}
