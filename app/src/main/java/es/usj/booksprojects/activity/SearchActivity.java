package es.usj.booksprojects.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.usj.booksprojects.R;
import es.usj.booksprojects.adapters.BookListAdapter;
import es.usj.booksprojects.adapters.SearchBookListAdapter;
import es.usj.booksprojects.data.AuthorData;
import es.usj.booksprojects.data.BookData;
import es.usj.booksprojects.database.AppDatabase;
import es.usj.booksprojects.database.BookDao;
import es.usj.booksprojects.model.Author;
import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.GetRequest;
import es.usj.booksprojects.serverOperations.callback.AuthorGetRequestCallback;
import es.usj.booksprojects.serverOperations.callback.BookGetRequestCallback;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button validerButton;
    //private SearchView searchView;
    private EditText searchEditText;
    private TextView searchTextView;
    private BookListAdapter adapter;
    private BookData searchResults;
    public static BookData searchList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //searchView = findViewById(R.id.searchView);
        searchEditText = findViewById(R.id.searchEditText);
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
        recyclerView.setAdapter(adapter);

        TextView helloTextView = findViewById(R.id.searchTextView);
        helloTextView.setText("Recherche de Books");
    }

    private void performSearch() {
        //String searchQuery = searchView.getQuery().toString();
        String searchQuery = searchEditText.getText().toString();

        if (!TextUtils.isEmpty(searchQuery)) {
            GetRequest getRequest = new GetRequest();
            getRequest.retrBooks(searchQuery, 99, new BookGetRequestCallback() {
                @Override
                public void onSuccess(List<Book> books) {
                    searchList = new BookData(books);
                    searchResults.setBooks(books);
                    adapter.updateBooks(books);  // Use the new method to update data
                    searchTextView.setText(getString(R.string.search_results, searchQuery));

                    for (Book bookItem:searchList.getBooks()){
                        Log.d("DEBUG", "Je rentre dans la boucle" + books.toString());
                        String authorKey = bookItem.getAuthorKey();
                        if(!AuthorData.getInstance().haveAuthor(authorKey)){
                            GetRequest getRequestAuthor = new GetRequest();

                            getRequestAuthor.retrAuthor(authorKey, new AuthorGetRequestCallback() {
                                @Override
                                public void onSuccess(Author author) {
                                    author.setKey(authorKey);
                                    Log.d("DEBUG","OnSuccees Author "+author.toString());
                                    AuthorData.getInstance().addAuthor(author);
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    Log.e("DEBUG","Onfailure Author ");
                                }
                            });
                        }
                    }

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