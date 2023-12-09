package es.usj.booksprojects.serverOperations.callback;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.List;

import es.usj.booksprojects.data.ImageData;
import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.GetRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface ImageBookGetRequestCallback extends Callback<ResponseBody> {

    void onSuccess();

    @Override
    default void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
    }

    @Override
    default void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.e("GetImage","onFailure");
    }
}
