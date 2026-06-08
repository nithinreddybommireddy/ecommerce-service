package com.trainingmug.ecommerce.service.impl;

import com.trainingmug.ecommerce.exception.ProductAlreadyExists;
import com.trainingmug.ecommerce.exception.ProductNotFoundException;
import com.trainingmug.ecommerce.model.Product;
import com.trainingmug.ecommerce.repository.ProductRepository;
import com.trainingmug.ecommerce.service.ProductService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product save(@Nonnull Product product) throws ProductAlreadyExists {

        productRepository.findById(product.getId())
                .ifPresent(p -> {
                    throw new ProductAlreadyExists(
                            "Product exists with id: " + product.getId());
                });
        return productRepository.save(product);

    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByAvailability(boolean isAvailable) {
        return productRepository.findAll().stream().filter(product -> product.isAvailable() == isAvailable).toList();

    }

    @Override
    public boolean getProductByCategory(String category) {
        return productRepository.findAll().stream().anyMatch(product -> product.getCategory().equalsIgnoreCase(category));
    }

    @Override
    public Product getProductById(int id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public List<Product> getAllProductsAbovePrice(double price) {
        return productRepository.findAll().stream().filter(product -> product.getMaxRetailPrice() > price).toList();
    }

    @Override
    public List<String> getAllProductNames() {
        return productRepository.findAll().stream().map(Product::getName).distinct().toList();

    }

    @Override
    public long getAvailableProductsCount() {
        return productRepository.findAll().stream().filter(Product::isAvailable).count();
    }

    @Override
    public boolean existsByCompany(String company) {
        return productRepository.findAll().stream().anyMatch(product -> product.getCompany().equalsIgnoreCase(company));
    }

    @Override
    public boolean allProductsAvailable() {
        return productRepository.findAll().stream().allMatch(Product::isAvailable);
    }

    @Override
    public Optional<Product> getFirstProduct() {
        return productRepository.findAll().stream().findFirst();
    }

    @Override
    public List<String> getAllUniqueCategories() {
        return productRepository.findAll().stream().map(Product::getCategory).distinct().toList();
    }

    @Override
    public List<Product> getTopNExpensiveProducts(int n) {
        return productRepository.findAll().stream().sorted(Comparator.comparing(Product::getMaxRetailPrice).reversed()).limit(n).toList();
    }

    @Override
    public List<Product> sortProductsByPriceAsc() {
        return productRepository.findAll().stream().sorted(Comparator.comparing(Product::getMaxRetailPrice)).toList();
    }

    @Override
    public List<Product> sortProductsByPriceDesc() {
        return productRepository.findAll().stream().sorted(Comparator.comparing(Product::getMaxRetailPrice, Comparator.reverseOrder())).toList();
    }

    @Override
    public Long getTotalInventoryValue() {
        return productRepository.findAll().stream().mapToLong(Product::getMaxRetailPrice).sum();
    }

    @Override
    public double getTotalPriceAfterDiscount() {
        return productRepository.findAll().stream().mapToDouble(product -> product.getMaxRetailPrice() - (product.getMaxRetailPrice() * product.getDiscountPercentage() / 100)).sum();
    }

    @Override
    public List<Product> getProductsByManufacturedAfter(int year) {
        return productRepository.findAll().stream().filter(product -> product.getManufactureYear() >= year).toList();
    }

    @Override
    public List<Product> getProductsByAvailableAndPriceAbove(boolean isAvailable, double price) {
        return productRepository.findAll().stream().filter(product -> product.isAvailable() == isAvailable).filter(product -> product.getMaxRetailPrice() > price).toList();
    }

    @Override
    public Map<String, List<Product>> getProductsByCategory() {
        return productRepository.findAll().stream().collect(Collectors.groupingBy(Product::getCategory));
    }

    @Override
    public Map<String, List<Product>> getProductsByCompany() {
        return productRepository.findAll().stream().collect(Collectors.groupingBy(Product::getCompany));
    }

    @Override
    public Map<Boolean, List<Product>> partitionProductsByAvailability() {
        return productRepository.findAll().stream().collect(Collectors.partitioningBy(Product::isAvailable));
    }

    @Override
    public Optional<Product> getMostExpensiveProduct() {
        return productRepository.findAll().stream().max(Comparator.comparingDouble(Product::getMaxRetailPrice));
    }

    @Override
    public Optional<Product> getCheapestProduct() {
        return productRepository.findAll().stream().min(Comparator.comparingDouble(Product::getMaxRetailPrice));
    }

    @Override
    public Map<Integer, Product> mapProductsById() {
        return productRepository.findAll().stream().collect(Collectors.toMap(Product::getId, product -> product));
    }

    @Override
    public Map<String, Double> getAveragePriceByCategory() {
        return productRepository.findAll().stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getMaxRetailPrice)));
    }

    @Override
    public Map<String, List<Product>> getTop3ExpensiveProductsByCategory() {
        return productRepository.findAll().stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.collectingAndThen(Collectors.toList(), products -> products.stream().sorted(Comparator.comparing(Product::getMaxRetailPrice).reversed()).limit(3).toList())));

    }
}



