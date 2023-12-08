package es.usj.booksprojects.serverOperations.callback;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import es.usj.booksprojects.mapper.BookMapper;
import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.valueApi.BookValueApi;
import es.usj.booksprojects.serverOperations.valueApi.BooksApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface BookGetRequestCallback extends Callback<BooksApiResponse> {

    void onSuccess(List<Book> books);

    void onFailure(Throwable t);

    @Override
    default void onResponse(Call<BooksApiResponse> call, Response<BooksApiResponse> response) {
        List<Book> booksList = new ArrayList<>();
        if (response.isSuccessful()) {
            BooksApiResponse booksResponse = response.body();
            if (booksResponse != null && booksResponse.getBooks() != null) {
                for (BookValueApi bookApi : booksResponse.getBooks()) {
                    Book book = BookMapper.toDomainBook(bookApi);
                    booksList.add(book);
                }
                onSuccess(booksList);
            }
        } else {
            onFailure(new Exception("Request failed: " + response.code()));
        }
    }

    @Override
    default void onFailure(@NonNull Call<BooksApiResponse> call, Throwable t) {
        onFailure(t);
    }
}
