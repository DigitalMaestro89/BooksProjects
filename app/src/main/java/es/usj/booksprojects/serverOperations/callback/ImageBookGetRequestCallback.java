package es.usj.booksprojects.serverOperations.callback;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.List;

import es.usj.booksprojects.model.Book;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface ImageBookGetRequestCallback extends Callback<ResponseBody> {

    void onSuccess();

    @Override
    default void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            String contentType = response.headers().get("content-type");
            if (contentType != null && contentType.equals("image/jpeg")) {
                // Téléchargez l'image ici
                // Appeler la méthode callback.onImageDownloadSuccess(bitmap);
                onSuccess();
                return;
            }
        }
    }

    @Override
    default void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.e("GetImage","onFailure");
        // Gérer les échecs de téléchargement de l'image
    }
}
