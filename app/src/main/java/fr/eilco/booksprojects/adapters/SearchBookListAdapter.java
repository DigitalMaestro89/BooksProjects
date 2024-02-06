package fr.eilco.booksprojects.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.eilco.booksprojects.R;
import fr.eilco.booksprojects.data.ImageData;
import fr.eilco.booksprojects.model.Book;

public class SearchBookListAdapter extends RecyclerView.Adapter<SearchBookListAdapter.SearchBookViewHolder> {

    private final List<Book> searchDataSet;
    private int resourceId;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    public SearchBookListAdapter(int resourceId, List<Book> searchDataSet) {
        this.resourceId = resourceId;
        this.searchDataSet = searchDataSet;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public SearchBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(resourceId, parent, false);
        return new SearchBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchBookViewHolder holder, int position) {
        if (searchDataSet != null && position < searchDataSet.size()) {
            Book book = searchDataSet.get(position);
            holder.tvBookTitle.setText(book.getTitle().toString());
            String isbn = book.getPrincipalIsbn();
            if (isbn != null) {
                ImageView imageView = holder.itemView.findViewById(R.id.imageView);
                imageView.setImageBitmap(ImageData.getInstance().getImage(isbn));
            }

            // Handle click on the item
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(book);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return searchDataSet.size();
    }

    public static class SearchBookViewHolder extends RecyclerView.ViewHolder {
        private TextView tvBookTitle;

        public SearchBookViewHolder(View cardView) {
            super(cardView);
            tvBookTitle = cardView.findViewById(R.id.tvBookTitle);
        }
    }

    public void updateSearchResults(List<Book> newBooks) {
        searchDataSet.clear();
        searchDataSet.addAll(newBooks);
        notifyDataSetChanged();
    }
}