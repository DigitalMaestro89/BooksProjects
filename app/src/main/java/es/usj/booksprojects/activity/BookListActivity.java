package es.usj.booksprojects.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        getRequest.retrBooks("maths", new GetRequestCallback() {
            @Override
            public void onSuccess(List<Book> books) {
                BookData.getInstance().setBooks(books);

                recyclerView = findViewById(R.id.rvBooks);
                recyclerView2 = findViewById(R.id.rvNewBooks);
                recyclerView3 = findViewById(R.id.rvYourList);

                adapter = new BookListAdapter(cardViewId, books);
                recyclerView.setLayoutManager(new GridLayoutManager(BookListActivity.this, 2));
                recyclerView.setAdapter(adapter);

                // Vous devez créer des adaptateurs distincts pour recyclerView2 et recyclerView3
                BookListAdapter adapter2 = new BookListAdapter(cardViewId, books);
                recyclerView2.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
                recyclerView2.setAdapter(adapter2);

                BookListAdapter adapter3 = new BookListAdapter(cardViewId, books);
                recyclerView3.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
                recyclerView3.setAdapter(adapter3);
            }

            @Override
            public void onFailure(Throwable t) {
                // Gérer l'échec
            }
        });
    }
}
