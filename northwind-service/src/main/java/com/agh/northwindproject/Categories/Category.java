package com.agh.northwindproject.Categories;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@NoArgsConstructor
@Getter @Setter
public class Category {
    @Id
    private String id;

    private String categoryName;

    private String description;

    private String picture;

    public Category(CategoryRequestBody categoryRequestBody) {
        this.description = categoryRequestBody.getDescription();
        this.categoryName = categoryRequestBody.getCategoryName();
        this.picture = categoryRequestBody.getPicture();
    }
}

