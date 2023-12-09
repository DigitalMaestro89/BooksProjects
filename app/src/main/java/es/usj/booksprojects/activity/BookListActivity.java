package es.usj.booksprojects.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.usj.booksprojects.R;
import es.usj.booksprojects.adapters.BookListAdapter;
import es.usj.booksprojects.data.BookData;
import es.usj.booksprojects.data.ImageData;
import es.usj.booksprojects.model.Book;
import es.usj.booksprojects.serverOperations.GetRequest;
import es.usj.booksprojects.serverOperations.callback.BookGetRequestCallback;
import es.usj.booksprojects.serverOperations.callback.ImageBookGetRequestCallback;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class BookListActivity extends AppCompatActivity {

    private RecyclerView rvYourList;
    private RecyclerView rvNewBooks;
    private int cardViewId = R.layout.view_book_card;

    private ImageView imageViewTest;
    private BookListAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("BookListActivity", "onCreate() : Avant l'appel à retrBooks()");

        Book bookTest = new Book();
        List<String> isbnTestList = new ArrayList<>();
        //isbnTestList.add("sdfgh");
        isbnTestList.add("9788372780119");
        //isbnTestList.add("571921");
        bookTest.setIsbnList(isbnTestList);
        bookTest.setPrincipalIsbn("9788372780119");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        GetRequest getRequest = new GetRequest();

        rvYourList = findViewById(R.id.rvYourList);
        rvNewBooks = findViewById(R.id.rvNewBooks);

        getRequest.retrBooks("maths", new BookGetRequestCallback() {
            @Override
            public void onSuccess(List<Book> books) {
                Log.d("BookListActivity", "onSuccess() : Livres récupérés avec succès");
                Log.e("Taille books", Integer.toString(books.size()));

                BookData.getInstance().setBooks(books);

                adapter = new BookListAdapter(cardViewId, new ArrayList<>(books));
                rvNewBooks.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rvNewBooks.setAdapter(adapter);
                rvYourList.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rvYourList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Request Failure", t.getMessage());
            }
        });

        imageViewTest = findViewById(R.id.imageViewTest);

        GetRequest getRequest2 = new GetRequest();
        getRequest2.retrBookImage(bookTest, new ImageBookGetRequestCallback() {

            @Override
            public void onSuccess() {
                Log.e("GetImage","onSuccess");
                // Une fois que l'image est récupérée avec succès, vous pouvez l'afficher dans imageViewTest
                Bitmap imageBitmap = ImageData.getInstance().getImage(bookTest.getPrincipalIsbn()); // Récupérer l'image associée au principal ISBN
                if (imageBitmap != null) {
                    runOnUiThread(() -> {
                        // Mettre à jour l'imageView avec l'image récupérée
                        imageViewTest.setImageBitmap(imageBitmap);
                    });
                }
            }

        });

    }

}
