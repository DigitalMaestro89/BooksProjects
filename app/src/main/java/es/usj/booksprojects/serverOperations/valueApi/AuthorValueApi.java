package es.usj.booksprojects.serverOperations.valueApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorValueApi {
    @JsonProperty("name")
    private String name;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("wikipedia")
    private String wikipedia;

    @JsonProperty("website")
    private String website;

    @JsonProperty("birth_date")
    private String birth_date;

}
