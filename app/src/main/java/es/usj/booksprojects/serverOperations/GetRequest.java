package es.usj.booksprojects.serverOperations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.List;

import es.usj.booksprojects.data.ImageData;
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
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GetRequest {

    private static final String BASE_URL = "https://openlibrary.org/";
    private static final String BASE_URL_COVER = "https://covers.openlibrary.org/";

    public void retrBooks(String searchName, int limit, BookGetRequestCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookApiService apiService = retrofit.create(BookApiService.class);
        Call<BooksApiResponse> call = apiService.getBooks(searchName,limit);

        call.enqueue(callback);
    }

    public void retrBookImage(Book book, ImageBookGetRequestCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_COVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookApiService apiService = retrofit.create(BookApiService.class);

        if (book.getIsbnList() != null && !book.getIsbnList().isEmpty()) {
            retrieveImageForIsbn(apiService, book, callback, book.getIsbnList(), 0);
        } else {
            callback.onFailure();
        }
    }

    private void retrieveImageForIsbn(BookApiService apiService, Book book, ImageBookGetRequestCallback callback, List<String> isbnList, int index) {
        if (index >= isbnList.size()) {
            callback.onFailure();
            return;
        }

        String isbn = isbnList.get(index);
        Call<ResponseBody> call = apiService.getImageBook(isbn);
        ImageBookGetRequestCallback.ImageResponseHandler responseHandler =
                new ImageBookGetRequestCallback.ImageResponseHandler(callback, book, isbn) {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            String contentType = response.headers().get("content-type");
                            if (book.getPrincipalIsbn() == null) {
                                if (contentType != null && contentType.equals("image/jpeg")) {
                                    Bitmap image = GetRequest.convertResponseBodyToBitmap(response.body());
                                    ImageData.getInstance().addImage(isbn, image);
                                    book.setPrincipalIsbn(isbn);
                                    callback.onSuccess(image); // Passer les données au callback
                                    return;
                                }
                            }
                        }
                        retrieveImageForIsbn(apiService, book, callback, isbnList, index + 1);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        retrieveImageForIsbn(apiService, book, callback, isbnList, index + 1);
                    }
                };
        call.enqueue(responseHandler);
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
