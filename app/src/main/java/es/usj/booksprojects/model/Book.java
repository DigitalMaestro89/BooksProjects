package es.usj.booksprojects.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "Books")
public class Book {
        @PrimaryKey
        private int id;

        private String key;

        private String title;

        private String principalIsbn;

        private List<String> isbnList = new ArrayList<>();

        private String firstPublishYear;

        private String pageNumber;

        private String authorName;

        private String authorKey;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFirstPublishYear() {
        return firstPublishYear;
    }

    public void setFirstPublishYear(String firstPublishYear) {
        this.firstPublishYear = firstPublishYear;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorKey() {
        return authorKey;
    }

    public void setAuthorKey(String authorKey) {
        this.authorKey = authorKey;
    }

    public String getTitle() {
                return title;
        }

    public String getPrincipalIsbn() {
        return principalIsbn;
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


    public void setPrincipalIsbn(String principalIsbn) {
        this.principalIsbn = principalIsbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", principalIsbn='" + principalIsbn + '\'' +
                ", isbnList=" + isbnList +
                ", firstPublishYear='" + firstPublishYear + '\'' +
                ", pageNumber='" + pageNumber + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorKey='" + authorKey + '\'' +
                '}';
    }
}
