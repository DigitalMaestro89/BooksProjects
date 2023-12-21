package es.usj.booksprojects.serverOperations.callback;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import es.usj.booksprojects.model.Author;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface AuthorGetRequestCallback extends Callback<AuthorsApiResponse> {

    void onSuccess(List<Author> authors);
    void onFailure(Throwable t);

    @Override
    default void onResponse(Call<AuthorsApiResponse> call, Response<AuthorsApiResponse> response) {
        List<String> authorsList = new ArrayList<>();
        if (response.isSuccessful()) {
            AuthorsApiResponse authorsResponse = response.body();
            if (authorsResponse != null && authorsResponse.getAuthors() != null) {
                /*for (AuthorValueApi authorApi : authorsResponse.getAuthors()) {
                    Author author = AuthorMapper.toDomainAuthor(authorApi);
                    authorsList.add(author);
                }*/

                //authorsList = authorsResponse.getAuthors()
                // onSuccess(authorsList);
            }
        } else {
            onFailure(new Exception("Request failed: " + response.code()));
        }
    }

    @Override
    default void onFailure(@NonNull Call<AuthorsApiResponse> call, Throwable t) {
        onFailure(t);
    }
}
