package es.usj.booksprojects.mapper;

import android.util.Log;

import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.valueApi.BookValueApi;

public class BookMapper {

    public static Book toDomainBook(BookValueApi valueApi){
        Book book = new Book();
        book.setTitle(valueApi.getTitle());
        book.setIsbnList(valueApi.getIsbn());
        book.setAuthorName(valueApi.getAuthor_name().get(0));
        book.setAuthorKey(valueApi.getAuthor_key().get(0));

        String key = valueApi.getKey();
        int index = key.indexOf("/works/") + "/works/".length();
        String effectiveKey = key.substring(index);

        book.setKey(effectiveKey);

        Log.d("DEBUG","MAPPER : " + valueApi.toString());
        //...
        return book;
    }
}
