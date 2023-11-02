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
    private List<String> isbnList;

    @JsonProperty("language")
    private List<String> languages;

    @JsonProperty("publish_date")
    private List<String> publishDates;

    @JsonProperty("publish_year")
    private List<String> publishYears;

    @JsonProperty("cover_i")
    private String coverI;

    @JsonProperty("cover_edition_key")
    private String coverEditionKey;

    @Override
    public String toString() {
        return "BookValueApi{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", authorsKeys=" + authorsKeys +
                ", medianPageNumber='" + medianPageNumber + '\'' +
                ", isbnList=" + isbnList +
                ", languages=" + languages +
                ", publishDates=" + publishDates +
                ", publishYears=" + publishYears +
                ", coverI='" + coverI + '\'' +
                ", coverEditionKey='" + coverEditionKey + '\'' +
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
        return isbnList;
    }

    public List<String> getLanguages() {
        return languages;
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

    public String getCoverEditionKey() {
        return coverEditionKey;
    }

}
