package com.agh.northwindproject.Products;

import com.agh.northwindproject.Categories.Category;
import com.agh.northwindproject.Suppliers.Supplier;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "products")
public class Product {
    @Id
    private BigInteger productID;

    private Supplier supplier;

    private Category category;

    private double unitPrice;

    private int unitsInStock;

    private int unitsInOrder;

    private int reorderLevel;

    private boolean discontinued;
}
