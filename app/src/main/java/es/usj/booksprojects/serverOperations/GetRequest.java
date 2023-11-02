package es.usj.booksprojects.serverOperations;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import es.usj.booksprojects.mapper.BookMapper;
import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.valueApi.BookValueApi;
import es.usj.booksprojects.serverOperations.valueApi.BooksApIResponse;

public class GetRequest {
    private final String QUERY = "https://openlibrary.org/search.json?q=";
    private final String COVERQUERY = "https://covers.openlibrary.org";

    public GetRequest() {
    }

    public List<Book> retrBooks(String searchName) {
        String bookQuery = QUERY.concat(searchName);
        List<Book> booksList = new ArrayList<>();

        try {
            // Créer un client HTTP
            HttpClient httpClient = HttpClients.createDefault();

            // Créer une requête GET avec l'URL
            HttpGet request = new HttpGet(bookQuery);

            // Exécuter la requête et obtenir la réponse
            HttpResponse response = httpClient.execute(request);

            // Lire la réponse JSON en tant que chaîne
            String jsonResponse = EntityUtils.toString(response.getEntity());

            // Convertir la réponse JSON en objets Book
            Gson gson = new Gson();
            BooksApIResponse booksResponse = gson.fromJson(jsonResponse, BooksApIResponse.class);

            // Vérifier si la réponse contient des livres
            if (booksResponse != null && booksResponse.getBooks() != null) {
                for (BookValueApi bookApi : booksResponse.getBooks()) {
                    Book book = BookMapper.toDomainBook(bookApi);
                    booksList.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booksList;
    }

}
