package es.usj.booksprojects.serverOperations.apiService;

import es.usj.booksprojects.serverOperations.valueApi.BooksApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookApiService {
    @GET("search.json")
    Call<BooksApiResponse> getBooks(
            @Query("q") String searchName,
            @Query("limit") int limit);
}
