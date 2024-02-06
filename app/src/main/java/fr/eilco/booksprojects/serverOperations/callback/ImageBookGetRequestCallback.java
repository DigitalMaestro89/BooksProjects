package fr.eilco.booksprojects.serverOperations.callback;

import android.graphics.Bitmap;

import fr.eilco.booksprojects.data.ImageData;
import fr.eilco.booksprojects.model.Book;
import fr.eilco.booksprojects.serverOperations.GetRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface ImageBookGetRequestCallback {
    void onSuccess(Bitmap image);
    void onFailure();

    class ImageResponseHandler implements Callback<ResponseBody> {

        private final ImageBookGetRequestCallback callback;
        private final Book book;
        private final String isbn;

        public ImageResponseHandler(ImageBookGetRequestCallback callback, Book book, String isbn) {
            this.callback = callback;
            this.book = book;
            this.isbn = isbn;
        }

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
            callback.onFailure(); // Signaler l'échec au callback
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            callback.onFailure(); // Signaler l'échec au callback
        }
    }
}
