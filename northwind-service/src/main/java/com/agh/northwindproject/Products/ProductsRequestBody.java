package com.agh.northwindproject.Products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ProductsRequestBody {
    private String productName;

    private String supplierID;

    private String categoryID;

    private int quantityPerUnit;

    private double unitPrice;

    private int unitsInStock;

    private int unitsInOrder;

    private int reorderLevel;

    private boolean discontinued;
}
