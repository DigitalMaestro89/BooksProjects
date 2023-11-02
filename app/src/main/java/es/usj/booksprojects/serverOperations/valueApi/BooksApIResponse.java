package es.usj.booksprojects.serverOperations.valueApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BooksApIResponse {

    @SerializedName("docs")
    private List<BookValueApi> books;

    public List<BookValueApi> getBooks() {
        return books;
    }
}
