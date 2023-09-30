package es.usj.booksprojects.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Books")
public class Book {
        @PrimaryKey
        private int id;
        private String title;
        private String externalReference;
        private String type;


        public Book(int id, String title) {
                this.id = id;
                this.title = title;
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

        public String getType() {
                return type;
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

        public void setType(String type) {
                this.type = type;
        }
}
