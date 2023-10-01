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
        private String externalReference;  // isbn ?

        private List<Integer> genres;

        private int author;


        public Book(int id, String title) {
                this.id = id;
                this.title = title;
                genres = new ArrayList<>();
        }

        public int getId() {
                return id;
        }

        public String getExternalReference() {
                return externalReference;
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

        public void setExternalReference(String externalReference) {
                this.externalReference = externalReference;
        }

        public void setAuthor(int author) {
                this.author = author;
        }

        public void setGenres(List<Integer> genres) {
                this.genres = genres;
        }
}
