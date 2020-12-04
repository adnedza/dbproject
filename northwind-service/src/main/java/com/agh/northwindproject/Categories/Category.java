package com.agh.northwindproject.Categories;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//categories - kategorie oferowanych produktów

@Document(collection = "categories")
@NoArgsConstructor
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
}

