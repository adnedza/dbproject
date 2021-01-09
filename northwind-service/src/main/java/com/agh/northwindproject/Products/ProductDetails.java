package com.agh.northwindproject.Products;

import com.agh.northwindproject.Categories.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetails {
    private String productID;
    private String productName;
    private String supplierID;
    private Category category;

    public ProductDetails(Product product) {
        this.productID = product.getId();
        this.productName = product.getProductName();
        this.supplierID = product.getSupplierID();
        this.category = product.getCategory();
    }
}
