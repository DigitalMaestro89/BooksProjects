package fr.eilco.booksprojects.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Author")
public class Author {

    @PrimaryKey
    private int id;

    private String key;

    private String name;

    private String biographie;

    private String wikipedia;

    private String website;

    private String birthDate;

    private String deathDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", biographie='" + biographie + '\'' +
                ", wikipedia='" + wikipedia + '\'' +
                ", website='" + website + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", deathDate='" + deathDate + '\'' +
                '}';
    }
}
