package org.learning.java.springilmiofotoalbum.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "photos")
public class Photo {


//    titolo
//    descrizione
//    url
//    visibile
//    categorie


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private String url;
    private Boolean visible;

    @ManyToMany
    @JoinTable(
            name = "photo_category",
            joinColumns = @JoinColumn(name = "photo_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;


    // GETTERS AND SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    // EQUALS AND HASHCODE


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(getId(), photo.getId()) && Objects.equals(getTitle(), photo.getTitle()) && Objects.equals(getDescription(), photo.getDescription()) && Objects.equals(getUrl(), photo.getUrl()) && Objects.equals(getVisible(), photo.getVisible()) && Objects.equals(getCategories(), photo.getCategories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getUrl(), getVisible(), getCategories());
    }
}
