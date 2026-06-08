package com.trainingmug.ecommerce.repository;


import com.trainingmug.ecommerce.model.Product;
import com.trainingmug.ecommerce.util.CsvReader;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Repository
public class ProductRepository {
    private final CsvReader csvReader;
    private final List<Product> products;

    public ProductRepository(CsvReader csvReader) throws IOException {
        this.csvReader = csvReader;
        this.products = this.csvReader.getProductFromCsv();

    }
    public Product save(Product product){
        this.products.add(product);
        return product;
    }

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst();

    }

    public Product update(Product product) {
        return (Product) products.stream().filter(p -> p.getId() == product.getId()).map(p -> {
            p.setName(product.getName());
            p.setMaxRetailPrice(product.getMaxRetailPrice());
            p.setDiscountPercentage(product.getDiscountPercentage());
            p.setAvailable(product.isAvailable());
            p.setCompany(product.getCompany());
            p.setCategory(product.getCategory());
            p.setManufactureYear(product.getManufactureYear());
            return p;
        });
    }

    public void delete(int id) {
        products.removeIf(p -> p.getId() == id);
    }
}



