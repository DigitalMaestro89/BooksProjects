package fr.eilco.booksprojects.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.eilco.booksprojects.R;
import fr.eilco.booksprojects.adapters.BookListAdapter;
import fr.eilco.booksprojects.data.AuthorData;
import fr.eilco.booksprojects.data.BookData;
import fr.eilco.booksprojects.model.Author;
import fr.eilco.booksprojects.model.Book;
import fr.eilco.booksprojects.serverOperations.GetRequest;
import fr.eilco.booksprojects.serverOperations.callback.AuthorGetRequestCallback;
import fr.eilco.booksprojects.serverOperations.callback.BookGetRequestCallback;

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
        String searchQuery = searchEditText.getText().toString();

        if (!TextUtils.isEmpty(searchQuery)) {
            GetRequest getRequest = new GetRequest();
            getRequest.retrBooks(searchQuery, 35, new BookGetRequestCallback() {
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