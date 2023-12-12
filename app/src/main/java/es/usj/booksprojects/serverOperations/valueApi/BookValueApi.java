package es.usj.booksprojects.serverOperations.valueApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookValueApi {

    @JsonProperty("title")
    private String title;

    @JsonProperty("author_name")
    private List<String> authors;

    @JsonProperty("author_key")
    private List<String> authorsKeys;

    @JsonProperty("number_of_pages_median")
    private String medianPageNumber;

    @JsonProperty("isbn")
    private List<String> isbn;

    @JsonProperty("publish_date")
    private List<String> publishDates;

    @JsonProperty("publish_year")
    private List<String> publishYears;

    @JsonProperty("cover_i")
    private String coverI;


    @Override
    public String toString() {
        return "BookValueApi{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", authorsKeys=" + authorsKeys +
                ", medianPageNumber='" + medianPageNumber + '\'' +
                ", isbnList=" + isbn +
                ", publishDates=" + publishDates +
                ", publishYears=" + publishYears +
                ", coverI='" + coverI + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<String> getAuthorsKeys() {
        return authorsKeys;
    }

    public String getMedianPageNumber() {
        return medianPageNumber;
    }

    public List<String> getIsbnList() {
        return isbn;
    }


    public List<String> getPublishDates() {
        return publishDates;
    }

    public List<String> getPublishYears() {
        return publishYears;
    }

    public String getCoverI() {
        return coverI;
    }


}
