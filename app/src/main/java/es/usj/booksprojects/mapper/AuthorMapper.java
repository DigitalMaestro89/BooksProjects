package es.usj.booksprojects.mapper;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.usj.booksprojects.model.Author;
import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.valueApi.AuthorValueApi;
import es.usj.booksprojects.serverOperations.valueApi.BookValueApi;

public class AuthorMapper {
    public static Author toDomainAuthor(AuthorValueApi valueApi){
        Author author = new Author();
        List<String> authorsList = valueApi.getAuthors();

        //author.setFirstName(valueApi.getAuthors());
        //book.setTitle(valueApi.getTitle());
        //book.setIsbnList(valueApi.getIsbnList());
        Log.e("mapper",valueApi.toString());
        //...
        return author;
    }
}
