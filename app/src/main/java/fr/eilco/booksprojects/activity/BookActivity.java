package fr.eilco.booksprojects.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import fr.eilco.booksprojects.R;
import fr.eilco.booksprojects.data.ImageData;
import fr.eilco.booksprojects.database.AppDatabase;
import fr.eilco.booksprojects.database.BookDao;
import fr.eilco.booksprojects.model.Book;

public class BookActivity extends AppCompatActivity {


    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent intent = getIntent();
        if (intent != null) {
            String keyBook = intent.getStringExtra("BOOK_KEY");
            String bookListName = intent.getStringExtra("BookListName");

            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            // Obtenez les DAO KeyValueDao et BookDao
            BookDao bookDao = db.bookDao();
            ImageView starImage = findViewById(R.id.ivStarBook);

            if (keyBook != null) {
                Book book;
                if (bookListName.equals("NewBooks")){
                    book = BookListActivity.newList.getBookByKey(keyBook);
                }else if(bookListName.equals("FavoriteList")){
                    book = BookListActivity.favoriteList.getBookByKey(keyBook);
                }else if(bookListName.equals("YourList")){
                    book = BookListActivity.yourList.getBookByKey(keyBook);
                } else {
                    book = SearchActivity.searchList.getBookByKey(keyBook);
                }

                Log.i("DEBUG", "BookACTivity : "+book.toString());

                boolean isStarred = book.isStar();
                if(isStarred){
                    starImage.setImageResource(android.R.drawable.btn_star_big_on);
                }else {
                    starImage.setImageResource(android.R.drawable.btn_star_big_off);
                }

                ImageView imageView = findViewById(R.id.ivBook);
                String bookCoverIsbn = book.getPrincipalIsbn();
                if(bookCoverIsbn != null){
                    imageView.setImageBitmap(ImageData.getInstance().getImage(bookCoverIsbn));
                }
                TextView tvTitleBook = findViewById(R.id.tvTitleBook);
                TextView tvAuthorName = findViewById(R.id.tvAuthorNameBook);
                TextView tvBookYear = findViewById(R.id.tvPublishYearBook);
                TextView tvPageNumber = findViewById(R.id.tvNumberPageBook);

                tvTitleBook.setText(book.getTitle());
                tvAuthorName.setText(book.getAuthorName());
                tvBookYear.setText(book.getFirstPublishYear());
                tvPageNumber.setText(book.getPageNumber());


                tvAuthorName.setOnClickListener(view -> {
                    Intent newIntent = new Intent(BookActivity.this, AuthorActivity.class);
                    String authorKey = book.getAuthorKey();
                    newIntent.putExtra("AUTHOR_KEY", authorKey);
                    startActivity(newIntent);
                });

                starImage.setOnClickListener(view -> {
                    boolean isStared = !book.isStar();

                    // mise à jour de l'image Star
                    if (isStared) {
                        starImage.setImageResource(android.R.drawable.btn_star_big_on);
                        book.setStar(true);

                        // Utilisez AsyncTask pour insérer le livre dans la base de données Room
                        new AsyncTask<Void, Void, List<Book>>() {
                            @Override
                            protected List<Book> doInBackground(Void... voids) {
                                // Vérifiez d'abord si le livre existe dans la base de données
                                if (!bookDao.containsBook(book.getKey())) {
                                    // Aucun livre similaire trouvé, vous pouvez insérer le nouveau livre
                                    bookDao.insert(book);
                                    BookListActivity.favoriteList.getBooks().add(book);
                                    Log.i("Insert DAO BOOk", book.getTitle());
                                }
                                return bookDao.getAllBooks();
                            }

                            @Override
                            protected void onPostExecute(List<Book> books) {
                                Log.i("Liste DAO books", Integer.toString(books.size()));
                            }
                        }.execute();
                    } else {
                        starImage.setImageResource(android.R.drawable.btn_star_big_off);
                        // mise à jour de l'état du livre
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                bookDao.deleteBookByKey(book.getKey());
                                BookListActivity.favoriteList.getBooks().remove(book);
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                            }
                        }.execute();

                        book.setStar(false);
                    }
                });

            }
        }
    }
}