package com.agh.northwindproject.Products;

import com.agh.northwindproject.Categories.Category;
import com.agh.northwindproject.Suppliers.Supplier;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@NoArgsConstructor
@Getter @Setter
public class Product {
    @Id
    private String id;

    private String productName;

    private Supplier supplier;

    private Category category;

    private int quantityPerUnit;

    private double unitPrice;

    private int unitsInStock;

    private int unitsInOrder;

    private int reorderLevel;

    private boolean discontinued;

    public Product(String productName, Supplier supplier, Category category,
                   int quantityPerUnit, double unitPrice, int unitsInStock,
                   int unitsInOrder, int reorderLevel, boolean discontinued) {
        this.productName = productName;
        this.supplier = supplier;
        this.category = category;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsInOrder = unitsInOrder;
        this.reorderLevel = reorderLevel;
        this.discontinued = discontinued;
    }
}
