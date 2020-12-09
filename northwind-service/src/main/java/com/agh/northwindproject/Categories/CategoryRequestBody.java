package com.agh.northwindproject.Categories;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CategoryRequestBody {
    private String categoryName;

    private String description;

    private String picture;
}
