package es.usj.booksprojects.mocks;

import java.util.Arrays;
import java.util.List;

import es.usj.booksprojects.model.Author;

public class AuthorMockData {

    public static List<Author> getMockAuthors() {
        Author author1 = new Author();
        author1.setId(1);
        author1.setFirstName("J.K.");
        author1.setLastName("Rowling");

        Author author2 = new Author();
        author2.setId(2);
        author2.setFirstName("George");
        author2.setLastName("Orwell");

        Author author3 = new Author();
        author3.setId(3);
        author3.setFirstName("J.R.R.");
        author3.setLastName("Tolkien");

        return Arrays.asList(author1, author2, author3);
    }
}
