package es.usj.booksprojects.serverOperations;

import es.usj.booksprojects.serverOperations.apiService.BookApiService;
import es.usj.booksprojects.serverOperations.callback.BookGetRequestCallback;
import es.usj.booksprojects.serverOperations.valueApi.BooksApiResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GetRequest {

    private static final String BASE_URL = "https://openlibrary.org/";

    public void retrBooks(String searchName, BookGetRequestCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookApiService apiService = retrofit.create(BookApiService.class);
        Call<BooksApiResponse> call = apiService.getBooks(searchName,25);

        call.enqueue(callback);
    }
}
