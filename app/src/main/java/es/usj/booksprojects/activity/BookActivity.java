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
            if (keyBook != null) {
                Book book = BookData.getInstance().getBookByKey(keyBook);
                Log.e("Book Tag", book.toString());


                ImageView imageView = findViewById(R.id.ivBook);
                imageView.setImageBitmap(ImageData.getInstance().getImage(book.getPrincipalIsbn()));

                TextView tvTitleBook = findViewById(R.id.tvTitleBook);
                tvTitleBook.setText(book.getTitle());
                TextView tvAuthorName = findViewById(R.id.tvAuthorNameBook);
                tvAuthorName.setText(book.getAuthorName());

            }
        }
    }
}