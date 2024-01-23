package es.usj.booksprojects.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.usj.booksprojects.R;
import es.usj.booksprojects.adapters.BookListAdapter;
import es.usj.booksprojects.adapters.SearchBookListAdapter;
import es.usj.booksprojects.data.BookData;
import es.usj.booksprojects.database.AppDatabase;
import es.usj.booksprojects.database.BookDao;
import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.GetRequest;
import es.usj.booksprojects.serverOperations.callback.BookGetRequestCallback;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button validerButton;
    private SearchView searchView;
    private TextView searchTextView;
    private BookListAdapter adapter;
    private BookData searchResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.searchView);
        validerButton = findViewById(R.id.validerButton);
        recyclerView = findViewById(R.id.recyclerView);
        searchTextView = findViewById(R.id.searchTextView);

        searchResults = new BookData();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3)); //2 colonnes


        adapter = new BookListAdapter(R.layout.view_book_card, searchResults.getBooks(), "SearchResults");
        recyclerView.setAdapter(adapter);

        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSearch();
            }
        });

        /*
        SearchBookListAdapter searchAdapter = new SearchBookListAdapter(R.layout.view_book_card, searchResults.getBooks());
        recyclerView.setAdapter(searchAdapter);

        searchAdapter.setOnItemClickListener(new SearchBookListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                String keyBook = book.getKey();
                Intent intent = new Intent(SearchActivity.this, BookActivity.class);
                intent.putExtra("BOOK_KEY", keyBook);
                intent.putExtra("BookListName", "SearchResults");
                startActivity(intent);
            }
        });*/

        recyclerView.setAdapter(adapter);

        TextView helloTextView = findViewById(R.id.searchTextView);
        helloTextView.setText("Recherche de Books");
    }

    private void performSearch() {
        String searchQuery = searchView.getQuery().toString();

        if (!TextUtils.isEmpty(searchQuery)) {
            GetRequest getRequest = new GetRequest();
            getRequest.retrBooks(searchQuery, new BookGetRequestCallback() {
                @Override
                public void onSuccess(List<Book> books) {
                    searchResults.setBooks(books);
                    adapter.updateBooks(books);  // Use the new method to update data
                    searchTextView.setText(getString(R.string.search_results, searchQuery));
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(SearchActivity.this, "Error during search", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please enter a valid search term", Toast.LENGTH_SHORT).show();
        }
    }
}