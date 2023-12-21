package es.usj.booksprojects.serverOperations.apiService;

import android.graphics.Bitmap;

import es.usj.booksprojects.serverOperations.valueApi.BooksApiResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookApiService {
    @GET("search.json")
    Call<BooksApiResponse> getBooks(
            @Query("q") String searchName,
            @Query("limit") int limit);

    // https://covers.openlibrary.org/b/isbn/9780130501042-S.jpg
    @GET("b/isbn/{isbn}-M.jpg")
    Call<ResponseBody> getImageBook(
            @Path("isbn") String isbn);


}
