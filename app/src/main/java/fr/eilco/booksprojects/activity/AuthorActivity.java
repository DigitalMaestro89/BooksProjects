package fr.eilco.booksprojects.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import fr.eilco.booksprojects.R;
import fr.eilco.booksprojects.data.AuthorData;
import fr.eilco.booksprojects.model.Author;

public class AuthorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        Intent intent = getIntent();
        String authorKey = intent.getStringExtra("AUTHOR_KEY");

        if(authorKey != null){
            Author author;
            author = AuthorData.getInstance().getAuthor(authorKey);

            TextView tvAuthorName = findViewById(R.id.tvAuthorName);
            TextView tvBioAuthor = findViewById(R.id.tvBioAuthor);
            TextView tvBirthAuthor = findViewById(R.id.tvBirthAuthor);
            TextView tvDeathAuthor = findViewById(R.id.tvDeathAuthor);
            TextView tvWikipedia = findViewById(R.id.tvWikipediaAuthor);
            TextView tvWebsite = findViewById(R.id.tvWebsiteAuthor);

            tvAuthorName.setText(author.getName());
            tvBioAuthor.setText(author.getBiographie());
            tvBirthAuthor.setText(author.getBirthDate());
            tvDeathAuthor.setText(author.getDeathDate());
            tvWikipedia.setText(author.getWikipedia());
            tvWebsite.setText(author.getWebsite());
        }

    }
}