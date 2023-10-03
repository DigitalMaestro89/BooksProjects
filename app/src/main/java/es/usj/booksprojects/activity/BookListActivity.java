package es.usj.booksprojects.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import es.usj.booksprojects.R;
import es.usj.booksprojects.adapters.BookListAdapter;


public class BookListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private int cardViewId = R.layout.view_book_card;
    private BookListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        recyclerView = findViewById(R.id.rvBooks);
        adapter = new BookListAdapter(cardViewId);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }

}