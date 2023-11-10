package es.usj.booksprojects.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.io.IOException;
import java.util.List;

import es.usj.booksprojects.R;
import es.usj.booksprojects.adapters.BookListAdapter;
import es.usj.booksprojects.data.BookData;
import es.usj.booksprojects.mocks.BookMockData;
import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.GetRequest;


public class BookListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;

    private RecyclerView recyclerView3;
    private int cardViewId = R.layout.view_book_card;
    private BookListAdapter adapter;

    private GetRequest getRequest = new GetRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BookData books = BookData.getInstance();
        try {
            books.setBooks(getRequest.retrBooks("judo"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        List<Book> localDataSet= BookMockData.getMockBooks();
        recyclerView = findViewById(R.id.rvBooks);
        adapter = new BookListAdapter(cardViewId, books.getBooks());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2 = findViewById(R.id.rvYourList);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(adapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3 = findViewById(R.id.rvNewBooks);
        recyclerView3.setLayoutManager(layoutManager2);
        recyclerView3.setAdapter(adapter);

    }

}