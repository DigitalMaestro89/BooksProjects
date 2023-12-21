package es.usj.booksprojects.serverOperations.apiService;


import es.usj.booksprojects.model.Author;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AuthorApiService {
    //@GET("authors/{authorKey}.json")
    //Call<Author> getAuthor(@Path("authorKey") String authorKey);
    @GET("search.json")
    Call<Author> getAuthor(
            @Query("q") String searchName
    );

}