package es.usj.booksprojects.data;

import java.util.ArrayList;
import java.util.List;

import es.usj.booksprojects.model.Book;


// we need just 1 instance of this class  --> singleton
// cf https://developpement-informatique.com/article/254/les-singletons-en-java

public final class BookData {
    private static BookData instance;
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    private BookData(){
        books = new ArrayList<Book>();
    }

    public static BookData getInstance() {
        if (instance == null) {
            instance = new BookData();
        }
        return instance;
    }
    // to create or retrieve this instance in exeternal class :
    // BookData booksSingleton = BookData.getInstance();

}
