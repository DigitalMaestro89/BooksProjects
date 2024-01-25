package es.usj.booksprojects.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.usj.booksprojects.R;
import es.usj.booksprojects.activity.BookActivity;
import es.usj.booksprojects.data.ImageData;
import es.usj.booksprojects.model.Book;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

    private final List<Book> localDataSet;
    //Context context;
    private int resourceId;
    //the Id of the layout we will repeat as many times we have items in the list

    private String listName;

    public BookListAdapter( int resourceId, List<Book> books, String listName){
        //this.context = context;
        this.localDataSet = books;
        this.resourceId = resourceId;
        this.listName = listName;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(resourceId,parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        if(localDataSet != null && position < localDataSet.size()){
            Book book = localDataSet.get(position);
            holder.tvBookTitle.setText(book.getTitle().toString());
            String isbn = book.getPrincipalIsbn();
            if(isbn != null){
                ImageView imageView =  holder.itemView.findViewById(R.id.imageView);
                Bitmap imageBook = ImageData.getInstance().getImage(isbn);
                if(imageBook!=null){
                    imageView.setImageBitmap(ImageData.getInstance().getImage(isbn));
                }
            }

            // Gestion du clic sur la carte
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Récupérer le livre associé à cette carte
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        String keyBook = book.getKey();
                        // Ouvrir une nouvelle activité en transmettant les données du livre
                        Intent intent = new Intent(view.getContext(), BookActivity.class);
                        intent.putExtra("BOOK_KEY", keyBook);
                        intent.putExtra("BookListName",listName);
                        view.getContext().startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }


    public class BookViewHolder extends RecyclerView.ViewHolder{
        private TextView tvBookTitle;

        public BookViewHolder(View cardView){
            super(cardView);
            tvBookTitle = cardView.findViewById(R.id.tvBookTitle);
        }
    }

    public void updateBooks(List<Book> newBooks) {
        localDataSet.clear();
        localDataSet.addAll(newBooks);
        notifyDataSetChanged();
    }

}
