package com.trainingmug.ecommerce.model;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String name;
    private long maxRetailPrice;
    private int discountPercentage;
    private boolean isAvailable;
    private String company;
    private String category;
    private int manufactureYear;


}
