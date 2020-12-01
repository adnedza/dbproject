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
    private BigInteger categoryID;

    private String description;

    private String picture;

    public Category(String description, String picture) {
        this.description = description;
        this.picture = picture;
    }
}

