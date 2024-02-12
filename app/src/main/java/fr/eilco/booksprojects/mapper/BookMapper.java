package fr.eilco.booksprojects.mapper;

import android.util.Log;

import fr.eilco.booksprojects.model.Book;
import fr.eilco.booksprojects.serverOperations.valueApi.BookValueApi;

public class BookMapper {

    public static Book toDomainBook(BookValueApi valueApi){
        Book book = new Book();
        book.setTitle(valueApi.getTitle());
        book.setIsbnList(valueApi.getIsbn());
        book.setAuthorName(valueApi.getAuthor_name() != null && !valueApi.getAuthor_name().isEmpty() ? valueApi.getAuthor_name().get(0) : "");
        book.setAuthorKey(valueApi.getAuthor_key() != null && !valueApi.getAuthor_key().isEmpty() ? valueApi.getAuthor_key().get(0) : "");
        book.setFirstPublishYear(valueApi.getFirst_publish_year());
        book.setPageNumber(valueApi.getNumber_of_pages_median());

        String link;
        if(valueApi.getSeed() != null){
            link = "https://openlibrary.org".concat(valueApi.getSeed().get(0));
        }else {
            link = "https://openlibrary.org";
        }

        book.setLink(link);

        String key = valueApi.getKey();
        int index = key.indexOf("/works/") + "/works/".length();
        String effectiveKey = key.substring(index);

        book.setKey(effectiveKey);
        return book;
    }
}
