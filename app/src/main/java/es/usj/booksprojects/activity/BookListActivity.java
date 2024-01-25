package es.usj.booksprojects.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import es.usj.booksprojects.R;
import es.usj.booksprojects.adapters.BookListAdapter;
import es.usj.booksprojects.data.AuthorData;
import es.usj.booksprojects.data.BookData;
import es.usj.booksprojects.database.AppDatabase;
import es.usj.booksprojects.database.BookDao;
import es.usj.booksprojects.model.Author;
import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.GetRequest;
import es.usj.booksprojects.serverOperations.callback.AuthorGetRequestCallback;
import es.usj.booksprojects.serverOperations.callback.BookGetRequestCallback;
import es.usj.booksprojects.serverOperations.callback.ImageBookGetRequestCallback;


public class BookListActivity extends AppCompatActivity {

    private RecyclerView rvYourList;
    private RecyclerView rvNewBooks;
    private RecyclerView rvFavorite;
    private int cardViewId = R.layout.view_book_card;
    private BookListAdapter adapterYourList;
    private BookListAdapter adapterNewList;
    private BookListAdapter adapterFavorite;
    public static BookData yourList;
    public static BookData newList;

    public static BookData favoriteList;


    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        ImageView ivSearchImageView = findViewById(R.id.ivSearch);
        ivSearchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookListActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        GetRequest getRequest = new GetRequest();
        GetRequest getRequest2 = new GetRequest();

        rvYourList = findViewById(R.id.rvYourList);
        rvNewBooks = findViewById(R.id.rvNewBooks);
        rvFavorite = findViewById(R.id.rvFavorites);

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        // Obtenez les DAO KeyValueDao et BookDao
        BookDao bookDao = db.bookDao();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                List<Book> favoriteBooks= bookDao.getAllBooks();
                favoriteList = new BookData(favoriteBooks);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapterFavorite = new BookListAdapter(cardViewId, favoriteList.getBooks(),"FavoriteList");
                rvFavorite.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rvFavorite.setAdapter(adapterFavorite);
                super.onPostExecute(aVoid);
            }
        }.execute();

        getRequest.retrBooks("Harry Potter", 15, new BookGetRequestCallback() {
            @Override
            public void onSuccess(List<Book> books) {
                Log.i("DEBUG", "LIST BOOK HARRY POTTER : Livres récupérés avec succès");
                Log.i("DEBUG","Taille livre " + Integer.toString(books.size()));

                yourList = new BookData(books);
                adapterYourList = new BookListAdapter(cardViewId, new ArrayList<>(books),"YourList");

                rvYourList.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rvYourList.setAdapter(adapterYourList);

                for (Book bookItem:yourList.getBooks()) {
                    Log.d("DEBUG", "Je rentre dans la boucle" + books.toString());
                    GetRequest getRequestImage = new GetRequest();
                    getRequestImage.retrBookImage(bookItem, new ImageBookGetRequestCallback() {
                        @Override
                        public void onSuccess(Bitmap image) {
                            Log.d("DEBUG","OnSuccees ImageBOOK "+bookItem.toString());
                            rvYourList.setAdapter(adapterYourList);
                        }

                        @Override
                        public void onFailure() {
                            Log.e("DEBUG","Onfailure ImageBOOK " + bookItem.toString());
                        }
                    });

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
                Log.d("DEBUG", "Je sors de la boucle" + books.toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Request DEBUG", t.getMessage());
            }
        });


        getRequest2.retrBooks("The Lord of the Rings", 15,  new BookGetRequestCallback() {
            @Override
            public void onSuccess(List<Book> books) {
                Log.i("DEBUG", "onSuccess() : Livres récupérés avec succès");
                Log.i("Taille DEBUG", Integer.toString(books.size()));

                newList = new BookData(books);

                    adapterNewList = new BookListAdapter(cardViewId, new ArrayList<>(books),"NewBooks");
                rvNewBooks.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rvNewBooks.setAdapter(adapterNewList);

                for (Book bookItem:newList.getBooks()) {
                    Log.i("DEBUG",books.toString());
                    GetRequest getRequestImage = new GetRequest();
                    getRequestImage.retrBookImage(bookItem, new ImageBookGetRequestCallback() {
                        @Override
                        public void onSuccess(Bitmap image) {
                            Log.i("DEBUG",bookItem.toString());
                            rvNewBooks.setAdapter(adapterNewList);
                        }

                        @Override
                        public void onFailure() {

                        }
                    });

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
                Log.e("DEBUG", t.getMessage());
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterFavorite = new BookListAdapter(cardViewId, favoriteList.getBooks(), "FavoriteList");
        rvFavorite.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
        rvFavorite.setAdapter(adapterFavorite);
    }

}
