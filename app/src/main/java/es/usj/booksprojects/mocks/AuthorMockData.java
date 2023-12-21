package es.usj.booksprojects.mocks;

import java.util.Arrays;
import java.util.List;

import es.usj.booksprojects.model.Author;

public class AuthorMockData {

    public static List<Author> getMockAuthors() {
        Author author1 = new Author();
        author1.setKeys(Arrays.asList(1));
        author1.setNames(Arrays.asList("J.K. Rowling"));

        Author author2 = new Author();
        author2.setKeys(Arrays.asList(2));
        author2.setNames(Arrays.asList("George Orwell"));

        Author author3 = new Author();
        author3.setKeys(Arrays.asList(3));
        author3.setNames(Arrays.asList("J.R.R. Tolkien"));

        return Arrays.asList(author1, author2, author3);
    }
}
