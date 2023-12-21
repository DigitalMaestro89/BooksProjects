package es.usj.booksprojects.serverOperations.valueApi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AuthorValueApi {
    @JsonProperty("author_name")
    private List<String> authors;

    @Override
    public String toString() {
        return "AuthorValueApi{" +
                "authors=" + authors+
                '}';
    }
    public List<String> getAuthors() {
        return authors;
    }
}
