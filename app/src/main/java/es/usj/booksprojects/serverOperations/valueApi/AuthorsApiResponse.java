package es.usj.booksprojects.serverOperations.valueApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AuthorsApiResponse {
    @SerializedName("docs")
    private List<AuthorValueApi> authors;

    public List<AuthorValueApi> getAuthors() {
        return authors;
    }
}
