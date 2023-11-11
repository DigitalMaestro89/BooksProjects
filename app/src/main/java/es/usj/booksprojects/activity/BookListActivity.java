package es.usj.booksprojects.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.usj.booksprojects.R;
import es.usj.booksprojects.adapters.BookListAdapter;
import es.usj.booksprojects.data.BookData;
import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.GetRequest;
import es.usj.booksprojects.serverOperations.callback.GetRequestCallback;

public class BookListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private int cardViewId = R.layout.view_book_card;
    private BookListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        GetRequest getRequest = new GetRequest();

        recyclerView = findViewById(R.id.rvBooks);

        getRequest.retrBooks("maths", new GetRequestCallback() {
            @Override
            public void onSuccess(List<Book> books) {
                Log.e("Taille books", Integer.toString(books.size()));

                BookData.getInstance().setBooks(books);

                // Créez une instance de l'adaptateur pour le premier RecyclerView
                adapter = new BookListAdapter(cardViewId, new ArrayList<>(books));
                //adapter.notifyDataSetChanged();
                // Attachez l'adaptateur au RecyclerView après avoir défini le LayoutManager
                recyclerView.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setAdapter(adapter);
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                // Gérer l'échec
                Log.e("Request Failure", t.getMessage());
            }
        });
    }

}
