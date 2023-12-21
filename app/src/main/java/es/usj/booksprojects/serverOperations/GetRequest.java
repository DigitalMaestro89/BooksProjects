package es.usj.booksprojects.serverOperations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.apiService.AuthorApiService;
import es.usj.booksprojects.serverOperations.apiService.BookApiService;
import es.usj.booksprojects.serverOperations.callback.AuthorGetRequestCallback;
import es.usj.booksprojects.serverOperations.callback.BookGetRequestCallback;
import es.usj.booksprojects.serverOperations.callback.ImageBookGetRequestCallback;
import es.usj.booksprojects.serverOperations.valueApi.AuthorValueApi;
import es.usj.booksprojects.serverOperations.valueApi.BooksApiResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GetRequest {

    private static final String BASE_URL = "https://openlibrary.org/";
    private static final String BASE_URL_COVER = "https://covers.openlibrary.org/";

    public void retrBooks(String searchName, BookGetRequestCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookApiService apiService = retrofit.create(BookApiService.class);
        Call<BooksApiResponse> call = apiService.getBooks(searchName,5);

        call.enqueue(callback);
    }

    public void retrBookImage(Book book, ImageBookGetRequestCallback callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_COVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookApiService apiService = retrofit.create(BookApiService.class);

        if (book.getIsbnList() != null) {
            for (String isbn : book.getIsbnList()) {
                Call<ResponseBody> call = apiService.getImageBook(isbn);
                ImageBookGetRequestCallback.ImageResponseHandler responseHandler = new ImageBookGetRequestCallback.ImageResponseHandler(callback, book, isbn);
                call.enqueue(responseHandler);
            }
        }
    }

    public void retrAuthor(String authorKey, AuthorGetRequestCallback callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AuthorApiService apiService = retrofit.create(AuthorApiService.class);
        Call<AuthorValueApi> call = apiService.getAuthor(authorKey);

        call.enqueue(callback);
    }

    public static Bitmap convertResponseBodyToBitmap(ResponseBody responseBody) {
        Bitmap bitmap = null;
        try {
            byte[] bytes = responseBody.bytes();
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
