package es.usj.booksprojects.serverOperations.apiService;

import es.usj.booksprojects.model.Author;
import es.usj.booksprojects.serverOperations.valueApi.AuthorsApiResponse;
import es.usj.booksprojects.serverOperations.valueApi.BooksApiResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AuthorApiService {
    //@GET("authors/{authorKey}.json")
    //Call<Author> getAuthor(@Path("authorKey") String authorKey);
    @GET("search.json")
    Call<AuthorsApiResponse> getAuthors(
            @Query("q") String searchName,
            @Query("limit") int limit
    );





}