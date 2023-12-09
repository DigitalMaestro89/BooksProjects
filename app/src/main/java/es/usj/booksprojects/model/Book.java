package es.usj.booksprojects.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "Books")
public class Book {
        @PrimaryKey
        private int id;

        private String title;
        private List<String> isbnList;

    private List<Integer> genres;

        private int author;

   public Book(int id, String title){
       this.id = id;
       this.title = title;
   }

    public Book() {
    }

        public int getId() {
                return id;
        }

    public List<String> getIsbnList() {
        return isbnList;
    }

    public String getTitle() {
                return title;
        }

        public int getAuthor() {
                return author;
        }

        public List<Integer> getGenres() {
                return genres;
        }

        public void setId(int id) {
                this.id = id;
        }

        public void setTitle(String title) {
                this.title = title;
        }

    public void setIsbnList(List<String> isbnList) {
        this.isbnList = isbnList;
    }

    public void setAuthor(int author) {
                this.author = author;
        }

        public void setGenres(List<Integer> genres) {
                this.genres = genres;
        }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genres=" + genres +
                ", author=" + author +
                '}';
    }
}
