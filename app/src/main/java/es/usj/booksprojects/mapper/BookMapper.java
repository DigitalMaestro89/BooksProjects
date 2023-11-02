package es.usj.booksprojects.mapper;

import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.valueApi.BookValueApi;

public class BookMapper {

    public static Book toDomainBook(BookValueApi valueApi){
        Book book = new Book();
        book.setTitle(valueApi.getTitle());
        //...
        return book;
    }
}
