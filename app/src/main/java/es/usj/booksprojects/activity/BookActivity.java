package es.usj.booksprojects.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import es.usj.booksprojects.R;
import es.usj.booksprojects.data.BookData;
import es.usj.booksprojects.data.ImageData;
import es.usj.booksprojects.model.Book;

public class BookActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent intent = getIntent();
        if (intent != null) {
            String keyBook = intent.getStringExtra("BOOK_KEY");
            String bookListName = intent.getStringExtra("BookListName");

            if (keyBook != null) {
                Book book;
                if (bookListName.equals("NewBooks")){
                    book = BookListActivity.newList.getBookByKey(keyBook);
                }else{
                    book = BookListActivity.yourList.getBookByKey(keyBook);
                }

                Log.i("DEBUG", "BookACTivity : "+book.toString());

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


            }
        }
    }
}