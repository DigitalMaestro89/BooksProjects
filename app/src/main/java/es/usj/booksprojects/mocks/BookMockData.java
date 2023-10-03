package es.usj.booksprojects.mocks;

import java.util.Arrays;
import java.util.List;

import es.usj.booksprojects.model.Book;

public class BookMockData {

    public static List<Book> getMockBooks() {
        Book book1 = new Book(1, "Le Seigneur des Anneaux");
        book1.setExternalReference("978-2-1111-1111-1");
        book1.setAuthor(1);
        book1.setGenres(Arrays.asList(1, 3, 5));

        Book book2 = new Book(2, "Harry Potter à l'école des sorciers");
        book2.setExternalReference("978-2-2222-2222-2");
        book2.setAuthor(2);
        book2.setGenres(Arrays.asList(2, 4, 6));

        Book book3 = new Book(3, "1984");
        book3.setExternalReference("978-3-3333-3333-3");
        book3.setAuthor(3);
        book3.setGenres(Arrays.asList(1, 5, 7));

        return Arrays.asList(book1, book2, book3);
    }
}
