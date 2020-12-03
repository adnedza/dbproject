package com.agh.northwindproject.Categories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

//categories - kategorie oferowanych produktów

@Document(collection = "categories")
@Getter @Setter
public class Category {
    @Id
    private String id;

    private String categoryName;

    private String description;

    private String picture;

    public Category(String description, String categoryName, String picture) {
        this.description = description;
        this.categoryName = categoryName;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}

