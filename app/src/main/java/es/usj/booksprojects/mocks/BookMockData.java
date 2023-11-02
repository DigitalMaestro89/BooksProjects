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

        Book book4 = new Book(4, "Le Petit Prince");
        book4.setExternalReference("978-4-4444-4444-4");
        book4.setAuthor(4);
        book4.setGenres(Arrays.asList(1, 8, 9));

        Book book5 = new Book(5, "Don Quichotte");
        book5.setExternalReference("978-5-5555-5555-5");
        book5.setAuthor(5);
        book5.setGenres(Arrays.asList(1, 10, 11));

        Book book6 = new Book(6, "Les Misérables");
        book6.setExternalReference("978-6-6666-6666-6");
        book6.setAuthor(6);
        book6.setGenres(Arrays.asList(1, 12, 13));

        Book book7 = new Book(7, "Crime et Châtiment");
        book7.setExternalReference("978-7-7777-7777-7");
        book7.setAuthor(7);
        book7.setGenres(Arrays.asList(1, 14, 15));

        Book book8 = new Book(8, "Guerre et Paix");
        book8.setExternalReference("978-8-8888-8888-8");
        book8.setAuthor(8);
        book8.setGenres(Arrays.asList(1, 16, 17));

        Book book9 = new Book(9, "Moby Dick");
        book9.setExternalReference("978-9-9999-9999-9");
        book9.setAuthor(9);
        book9.setGenres(Arrays.asList(1, 18, 19));

        Book book10 = new Book(10, "L'Odyssée");
        book10.setExternalReference("978-10-1010-1010-0");
        book10.setAuthor(10);
        book10.setGenres(Arrays.asList(1, 20, 21));

        Book book11 = new Book(11, "Hamlet");
        book11.setExternalReference("978-11-1111-1111-1");
        book11.setAuthor(11);
        book11.setGenres(Arrays.asList(1, 22, 23));

        Book book12 = new Book(12, "Les Trois Mousquetaires");
        book12.setExternalReference("978-12-1212-1212-1");
        book12.setAuthor(12);
        book12.setGenres(Arrays.asList(1, 24, 25));

        Book book13 = new Book(13, "Le Comte de Monte-Cristo");
        book13.setExternalReference("978-13-1313-1313-1");
        book13.setAuthor(13);
        book13.setGenres(Arrays.asList(1, 26, 27));

        Book book14 = new Book(14, "Anna Karénine");
        book14.setExternalReference("978-14-1414-1414-1");
        book14.setAuthor(14);
        book14.setGenres(Arrays.asList(1, 28, 29));

        Book book15 = new Book(15, "Le Rouge et le Noir");
        book15.setExternalReference("978-15-1515-1515-1");
        book15.setAuthor(15);
        book15.setGenres(Arrays.asList(1, 30, 31));

        Book book16 = new Book(16, "La Divine Comédie");
        book16.setExternalReference("978-16-1616-1616-1");
        book16.setAuthor(16);
        book16.setGenres(Arrays.asList(1, 32, 33));

        Book book17 = new Book(17, "Le Portrait de Dorian Gray");
        book17.setExternalReference("978-17-1717-1717-1");
        book17.setAuthor(17);
        book17.setGenres(Arrays.asList(1, 34, 35));

        Book book18 = new Book(18, "Les Hauts de Hurle-Vent");
        book18.setExternalReference("978-18-1818-1818-1");
        book18.setAuthor(18);
        book18.setGenres(Arrays.asList(1, 36, 37));

        Book book19 = new Book(19, "Dracula");
        book19.setExternalReference("978-19-1919-1919-1");
        book19.setAuthor(19);
        book19.setGenres(Arrays.asList(1, 38, 39));

        Book book20 = new Book(20, "Frankenstein");
        book20.setExternalReference("978-20-2020-2020-1");
        book20.setAuthor(20);
        book20.setGenres(Arrays.asList(1, 40, 41));

        Book book21 = new Book(21, "Le Maître et Marguerite");
        book21.setExternalReference("978-21-2121-2121-1");
        book21.setAuthor(21);
        book21.setGenres(Arrays.asList(1, 42, 43));

        Book book22 = new Book(22, "L'Étranger");
        book22.setExternalReference("978-22-2222-2222-1");
        book22.setAuthor(22);
        book22.setGenres(Arrays.asList(1, 44, 45));

        Book book23 = new Book(23, "La Guerre des mondes");
        book23.setExternalReference("978-23-2323-2323-1");
        book23.setAuthor(23);
        book23.setGenres(Arrays.asList(1, 46, 47));

        Book book24 = new Book(24, "Voyage au centre de la Terre");
        book24.setExternalReference("978-24-2424-2424-1");
        book24.setAuthor(24);
        book24.setGenres(Arrays.asList(1, 48, 49));

        Book book25 = new Book(25, "Le Tour du monde en 80 jours");
        book25.setExternalReference("978-25-2525-2525-1");
        book25.setAuthor(25);
        book25.setGenres(Arrays.asList(1, 50, 51));

        return Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9, book10,
                book11, book12, book13, book14, book15, book16, book17, book18, book19, book20,
                book21, book22, book23, book24, book25);
    }
}
