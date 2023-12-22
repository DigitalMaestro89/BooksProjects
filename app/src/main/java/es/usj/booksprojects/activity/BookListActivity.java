package es.usj.booksprojects.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import es.usj.booksprojects.R;
import es.usj.booksprojects.adapters.BookListAdapter;
import es.usj.booksprojects.data.BookData;
import es.usj.booksprojects.model.Author;
import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.GetRequest;
import es.usj.booksprojects.serverOperations.callback.AuthorGetRequestCallback;
import es.usj.booksprojects.serverOperations.callback.BookGetRequestCallback;
import es.usj.booksprojects.serverOperations.callback.ImageBookGetRequestCallback;


public class BookListActivity extends AppCompatActivity {

    private RecyclerView rvYourList;
    private RecyclerView rvNewBooks;
    private int cardViewId = R.layout.view_book_card;
    private BookListAdapter adapterYourList;
    private BookListAdapter adapterNewList;
    public static BookData yourList;
    public static BookData newList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        GetRequest getRequest = new GetRequest();
        GetRequest getRequest2 = new GetRequest();

        rvYourList = findViewById(R.id.rvYourList);
        rvNewBooks = findViewById(R.id.rvNewBooks);

        getRequest.retrBooks("Harry Potter", new BookGetRequestCallback() {
            @Override
            public void onSuccess(List<Book> books) {
                Log.d("BookListActivity", "onSuccess() : Livres récupérés avec succès");
                Log.e("Taille books", Integer.toString(books.size()));

                yourList = new BookData(books);
                adapterYourList = new BookListAdapter(cardViewId, new ArrayList<>(books),"YourList");

                rvYourList.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rvYourList.setAdapter(adapterYourList);

                for (Book bookItem:books) {
                    Log.e("TestBooks",books.toString());
                    GetRequest getRequestImage = new GetRequest();
                    getRequestImage.retrBookImage(bookItem, new ImageBookGetRequestCallback() {
                        @Override
                        public void onSuccess(Bitmap image) {
                            Log.e("RetrImage",bookItem.toString());
                            rvYourList.setAdapter(adapterYourList);
                        }

                        @Override
                        public void onFailure() {

                        }
                    });

                    GetRequest getRequestAuthor = new GetRequest();
                    getRequestAuthor.retrAuthor("OL2622837A", new AuthorGetRequestCallback() {
                        @Override
                        public void onSuccess(Author author) {
                            Log.e("AUTHOR TEST", author.toString());
                        }

                        @Override
                        public void onFailure(Throwable t) {}
                    });
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Request Failure", t.getMessage());
            }
        });


        getRequest2.retrBooks("The Lord of the Rings", new BookGetRequestCallback() {
            @Override
            public void onSuccess(List<Book> books) {
                Log.d("BookListActivity", "onSuccess() : Livres récupérés avec succès");
                Log.e("Taille books", Integer.toString(books.size()));

                newList = new BookData(books);

                adapterNewList = new BookListAdapter(cardViewId, new ArrayList<>(books),"NewBooks");
                rvNewBooks.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rvNewBooks.setAdapter(adapterNewList);

                for (Book bookItem:books) {
                    Log.e("TestBooks",books.toString());
                    GetRequest getRequestImage = new GetRequest();
                    getRequestImage.retrBookImage(bookItem, new ImageBookGetRequestCallback() {
                        @Override
                        public void onSuccess(Bitmap image) {
                            Log.e("RetrImage",bookItem.toString());
                            rvNewBooks.setAdapter(adapterNewList);
                        }

                        @Override
                        public void onFailure() {

                        }
                    });

                    GetRequest getRequestAuthor = new GetRequest();
                    getRequestAuthor.retrAuthor("OL2622837A", new AuthorGetRequestCallback() {
                        @Override
                        public void onSuccess(Author author) {
                            Log.e("AUTHOR TEST", author.toString());
                        }

                        @Override
                        public void onFailure(Throwable t) {}
                    });
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Request Failure", t.getMessage());
            }
        });

    }
}
