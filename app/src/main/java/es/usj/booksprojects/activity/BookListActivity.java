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
import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.GetRequest;
import es.usj.booksprojects.serverOperations.callback.BookGetRequestCallback;
import es.usj.booksprojects.serverOperations.callback.ImageBookGetRequestCallback;


public class BookListActivity extends AppCompatActivity {

    private RecyclerView rvYourList;
    private RecyclerView rvNewBooks;
    private int cardViewId = R.layout.view_book_card;
    private BookListAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        GetRequest getRequest = new GetRequest();

        rvYourList = findViewById(R.id.rvYourList);
        rvNewBooks = findViewById(R.id.rvNewBooks);

        getRequest.retrBooks("Harry Potter", new BookGetRequestCallback() {
            @Override
            public void onSuccess(List<Book> books) {
                Log.d("BookListActivity", "onSuccess() : Livres récupérés avec succès");
                Log.e("Taille books", Integer.toString(books.size()));

                BookData.getInstance().setBooks(books);

                adapter = new BookListAdapter(cardViewId, new ArrayList<>(books));
                rvNewBooks.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rvNewBooks.setAdapter(adapter);
                rvYourList.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rvYourList.setAdapter(adapter);

                for (Book bookItem:books) {
                    Log.e("TestBooks",books.toString());
                    GetRequest getRequestImage = new GetRequest();
                    getRequestImage.retrBookImage(bookItem, new ImageBookGetRequestCallback() {
                        @Override
                        public void onSuccess(Bitmap image) {
                            Log.e("RetrImage",bookItem.toString());
                            adapter = new BookListAdapter(cardViewId, new ArrayList<>(books));
                            rvYourList.setAdapter(adapter);
                            rvNewBooks.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure() {

                        }
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
