package com.trainingmug.ecommerce.service;


import com.trainingmug.ecommerce.exception.ProductAlreadyExists;
import com.trainingmug.ecommerce.exception.ProductNotFoundException;

import com.trainingmug.ecommerce.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService  {

    Product save(Product product) throws ProductAlreadyExists;
    List<Product> getAll();
    List<Product> getProductsByAvailability(boolean isAvailable);

    boolean getProductByCategory(String category);

    Product getProductById(int id) throws ProductNotFoundException;

    List<Product> getAllProductsAbovePrice(double price);

    List<String> getAllProductNames();

    long getAvailableProductsCount();

    boolean existsByCompany(String company);

    boolean allProductsAvailable();

    Optional<Product> getFirstProduct();

    List<String> getAllUniqueCategories();

    List<Product> getTopNExpensiveProducts(int n);

    List<Product> sortProductsByPriceAsc();

    List<Product> sortProductsByPriceDesc();

    Long getTotalInventoryValue();

    double getTotalPriceAfterDiscount();

    List<Product> getProductsByManufacturedAfter(int year);

    List<Product> getProductsByAvailableAndPriceAbove(boolean isAvailable, double price);

    Map<String, List<Product>> getProductsByCategory();

    Map<String, List<Product>> getProductsByCompany();

    Map<Boolean, List<Product>> partitionProductsByAvailability();

    Optional<Product> getMostExpensiveProduct();

    Optional<Product> getCheapestProduct();

    Map<Integer, Product> mapProductsById();

    Map<String, Double> getAveragePriceByCategory();

    Map<String, List<Product>> getTop3ExpensiveProductsByCategory();

}


