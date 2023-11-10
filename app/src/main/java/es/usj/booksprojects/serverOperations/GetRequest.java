package es.usj.booksprojects.serverOperations;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.usj.booksprojects.mapper.BookMapper;
import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.valueApi.BookValueApi;
import es.usj.booksprojects.serverOperations.valueApi.BooksApIResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetRequest {

    private static final String QUERY = "https://openlibrary.org/search.json?q=";

    public interface Callback {
        void onSuccess(List<Book> books);

        void onFailure(Throwable t);
    }

    public void retrBooks(String searchName, Callback callback) {
        new AsyncTask<Void, Void, List<Book>>() {
            @Override
            protected List<Book> doInBackground(Void... voids) {
                try {
                    String bookQuery = QUERY.concat(searchName);
                    OkHttpClient client = new OkHttpClient();
                    List<Book> booksList = new ArrayList<>();

                    // Créer une requête GET avec l'URL
                    Request request = new Request.Builder()
                            .url(bookQuery)
                            .build();

                    // Exécuter la requête de manière synchrone
                    Response response = client.newCall(request).execute();

                    if (response.isSuccessful()) {
                        String jsonResponse = response.body().string();
                        Gson gson = new Gson();
                        BooksApIResponse booksResponse = gson.fromJson(jsonResponse, BooksApIResponse.class);

                        // Vérifier si la réponse contient des livres
                        if (booksResponse != null && booksResponse.getBooks() != null) {
                            for (BookValueApi bookApi : booksResponse.getBooks()) {
                                Book book = BookMapper.toDomainBook(bookApi);
                                booksList.add(book);
                            }
                            return booksList;
                        }
                    }
                } catch (IOException e) {
                    callback.onFailure(e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<Book> books) {
                super.onPostExecute(books);
                if (books != null) {
                    callback.onSuccess(books);
                }
            }
        }.execute();
    }
}
