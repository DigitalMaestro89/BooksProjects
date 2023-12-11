package es.usj.booksprojects.serverOperations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import es.usj.booksprojects.data.ImageData;
import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.apiService.BookApiService;
import es.usj.booksprojects.serverOperations.callback.BookGetRequestCallback;
import es.usj.booksprojects.serverOperations.callback.ImageBookGetRequestCallback;
import es.usj.booksprojects.serverOperations.valueApi.BooksApiResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

        if(book.getIsbnList()!= null){
            for (String isbn:book.getIsbnList()) {
                Call<ResponseBody> call = apiService.getImageBook(isbn);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            String contentType = response.headers().get("content-type");
                            if(book.getPrincipalIsbn() == null){
                                if (contentType != null && contentType.equals("image/jpeg")) {
                                    Bitmap image = GetRequest.convertResponseBodyToBitmap(response.body());
                                    ImageData.getInstance().addImage(isbn, image);
                                    book.setPrincipalIsbn(isbn);
                                    callback.onSuccess(); // Passer les données au callback
                                    return;
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        }
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
