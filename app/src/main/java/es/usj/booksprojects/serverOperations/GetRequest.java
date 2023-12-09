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
        Call<BooksApiResponse> call = apiService.getBooks(searchName,25);

        call.enqueue(callback);
    }

    public void retrBookImage(Book book, ImageBookGetRequestCallback callback) {

        Log.d("GetRequest", "retrBookImage() : Début de la méthode");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_COVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookApiService apiService = retrofit.create(BookApiService.class);

        for (String isbn:book.getIsbnList()) {
            Call<ResponseBody> call = apiService.getImageBook(isbn);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.d("call",call.request().toString());
                    Log.d("headers",response.headers().toString());
                    Log.d("OnResponse", "onResponse() : Succès de la réponse pour ISBN : " + isbn);
                    //if (response.isSuccessful()) {
                        //Log.d("GetRequest", "onResponse() : Succès de la réponse pour ISBN : " + isbn);
                        //Log.d("Headers",response.body().toString());
                        Log.d("response",Integer.toString(response.code()));
                        String contentType = response.headers().get("content-type");
                        Log.d("contentType Str",contentType);
                        if (contentType != null && contentType.equals("image/jpeg")) {
                            Log.d("ContentType", "Image() : Succès de la réponse pour ISBN : " + isbn);
                            Bitmap image = convertResponseBodyToBitmap(response.body());
                            ImageData.getInstance().addImage(isbn, image);
                            book.setPrincipalIsbn(isbn);

                           return;
                        }
                    //}
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("GetRequest", "onFailure() : Échec de la réponse pour ISBN : " + isbn + ", Erreur : " + t.getMessage());
                }
            });

    }
    // https://openlibrary.org/search.json?q=Harry+Potter&limit=10
}


    private Bitmap convertResponseBodyToBitmap(ResponseBody responseBody) {
        Bitmap bitmap = null;
        try {
            // Convertir ResponseBody en tableau de bytes
            byte[] bytes = responseBody.bytes();

            // Convertir les bytes en Bitmap
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
