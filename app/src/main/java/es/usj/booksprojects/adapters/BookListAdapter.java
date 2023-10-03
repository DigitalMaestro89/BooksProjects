package es.usj.booksprojects.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.usj.booksprojects.R;
import es.usj.booksprojects.mocks.BookMockData;
import es.usj.booksprojects.model.Book;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

    private List<Book> localDataSet;
    Context context;
    private int resourceId;
    //the Id of the layout we will repeat as many times we have items in the list

    public BookListAdapter( int resourceId){
        //this.context = context;
        this.resourceId = resourceId;
        localDataSet= BookMockData.getMockBooks();
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
        Book book = localDataSet.get(position);
        holder.tvBookTitle.setText(book.getTitle().toString());
    }

    @Override
    public int getItemCount() {
        return BookMockData.getMockBooks().size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{
        private TextView tvBookTitle;

        public BookViewHolder(View cardView){
            super(cardView);
            tvBookTitle = cardView.findViewById(R.id.tvBookTitle);
        }
    }

}
