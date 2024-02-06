package fr.eilco.booksprojects.serverOperations.valueApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookValueApi {

    @JsonProperty("title")
    private String title;

    @JsonProperty("key")
    private String key;

    @JsonProperty("author_name")
    private List<String> author_name;

    @JsonProperty("author_key")
    private List<String> author_key;

    @JsonProperty("number_of_pages_median")
    private String number_of_pages_median;

    @JsonProperty("isbn")
    private List<String> isbn;

    @JsonProperty("first_publish_year")
    private String first_publish_year;

    public String getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }

    public List<String> getAuthor_name() {
        return author_name;
    }

    public List<String> getAuthor_key() {
        return author_key;
    }

    public String getNumber_of_pages_median() {
        return number_of_pages_median;
    }

    public List<String> getIsbn() {
        return isbn;
    }

    public String getFirst_publish_year() {
        return first_publish_year;
    }

    @Override
    public String toString() {
        return "BookValueApi{" +
                "title='" + title + '\'' +
                ", key='" + key + '\'' +
                ", author_name=" + author_name +
                ", author_key=" + author_key +
                ", number_of_pages_median='" + number_of_pages_median + '\'' +
                ", isbn=" + isbn +
                ", first_publish_year=" + first_publish_year +
                '}';
    }
}
