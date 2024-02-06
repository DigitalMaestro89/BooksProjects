package fr.eilco.booksprojects.mapper;

import android.util.Log;

import fr.eilco.booksprojects.model.Author;
import fr.eilco.booksprojects.serverOperations.valueApi.AuthorValueApi;


public class AuthorMapper {
    public static Author toDomainAuthor(AuthorValueApi valueApi){
        Author author = new Author();
        author.setName(valueApi.getName());
        author.setBiographie(valueApi.getBio());
        author.setWikipedia(valueApi.getWikipedia());
        author.setWebsite(valueApi.getWebsite());
        author.setBirthDate(valueApi.getBirth_date());
        author.setDeathDate(valueApi.getDeath_date());
        Log.e("mapper",valueApi.toString());
        //...
        return author;
    }
}
