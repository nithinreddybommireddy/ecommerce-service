package com.trainingmug.ecommerce.controller;

import com.trainingmug.ecommerce.exception.CustomerExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.trainingmug.ecommerce.model.Product;
import com.trainingmug.ecommerce.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 1. Get products by availability
    @GetMapping("/availability/{status}")
    public ResponseEntity<List<Product>> getProductsByAvailability(
            @PathVariable boolean status) {
        return ResponseEntity.ok(
                productService.getProductsByAvailability(status));
    }

    // 2. Get products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<Map<String, List<Product>>> getProductsByCategory(
            @PathVariable String category) {
        return ResponseEntity.ok(productService.getProductsByCategory());
    }

    // 3. Get products above price
    @GetMapping("/price-above/{price}")
    public ResponseEntity<List<Product>> getProductsAbovePrice(
            @PathVariable double price) {
        return ResponseEntity.ok(
                productService.getAllProductsAbovePrice(price));
    }

    // 4. Get all product names
    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllNames() {
        return ResponseEntity.ok(
                productService.getAllProductNames());
    }

    // 5. Count available products
    @GetMapping("/available/count")
    public ResponseEntity<Long> getAvailableCount() {
        return ResponseEntity.ok(
                productService.getAvailableProductsCount());
    }

    // 6. Check company exists
    @GetMapping("/company-exists/{company}")
    public ResponseEntity<Boolean> existsByCompany(
            @PathVariable String company) {
        return ResponseEntity.ok(
                productService.existsByCompany(company));
    }

    // 7. Check all products available
    @GetMapping("/all-available")
    public ResponseEntity<Boolean> allProductsAvailable() {
        return ResponseEntity.ok(
                productService.allProductsAvailable());
    }

    // 8. First product
    @GetMapping("/first")
    public ResponseEntity<Optional<Product>> getFirstProduct() {
        return ResponseEntity.ok(
                productService.getFirstProduct());
    }

    // 9. Unique categories
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getUniqueCategories() {
        return ResponseEntity.ok(
                productService.getAllUniqueCategories());
    }

    // 10. Top N expensive products
    @GetMapping("/top-expensive/{n}")
    public ResponseEntity<List<Product>> getTopNProducts(
            @PathVariable int n) {
        return ResponseEntity.ok(
                productService.getTopNExpensiveProducts(n));
    }

    // 11. Sort by price ascending
    @GetMapping("/sort/price-asc")
    public ResponseEntity<List<Product>> sortPriceAsc() {
        return ResponseEntity.ok(
                productService.sortProductsByPriceAsc());
    }

    // 12. Sort by price descending
    @GetMapping("/sort/price-desc")
    public ResponseEntity<List<Product>> sortPriceDesc() {
        return ResponseEntity.ok(
                productService.sortProductsByPriceDesc());
    }

    // 13. Total inventory value
    @GetMapping("/inventory-value")
    public ResponseEntity<Long> inventoryValue() {
        return ResponseEntity.ok(
                productService.getTotalInventoryValue());
    }

    // 14. Total price after discount
    @GetMapping("/discounted-total")
    public ResponseEntity<Double> discountedTotal() {
        return ResponseEntity.ok(
                productService.getTotalPriceAfterDiscount());
    }

    // 15. Manufactured after year
    @GetMapping("/manufactured-after/{year}")
    public ResponseEntity<List<Product>> manufacturedAfter(
            @PathVariable int year) {
        return ResponseEntity.ok(
                productService.getProductsByManufacturedAfter(year));
    }

    // 16. Available and above price
    @GetMapping("/available-price")
    public ResponseEntity<List<Product>> availableAndPriceAbove(
            @RequestParam boolean available,
            @RequestParam double price) {
        return ResponseEntity.ok(
                productService.getProductsByAvailableAndPriceAbove(
                        available, price));
    }

    // 17. Count products by category
    @GetMapping("/count-by-category")
    public ResponseEntity<Map<String, Long>> countByCategory() {
        return ResponseEntity.ok(
                productService.getAll()
                        .stream()
                        .collect(Collectors.groupingBy(
                                Product::getCategory,
                                Collectors.counting())));
    }

    // 18. Group by category
    @GetMapping("/group/category")
    public ResponseEntity<Map<String, List<Product>>> groupByCategory() {
        return ResponseEntity.ok(
                productService.getProductsByCategory());
    }

    // 19. Group by company
    @GetMapping("/group/company")
    public ResponseEntity<Map<String, List<Product>>> groupByCompany() {
        return ResponseEntity.ok(
                productService.getProductsByCompany());
    }

    // 20. Partition by availability
    @GetMapping("/partition")
    public ResponseEntity<Map<Boolean, List<Product>>> partitionProducts() {
        return ResponseEntity.ok(
                productService.partitionProductsByAvailability());
    }

    // 21. Most expensive product
    @GetMapping("/most-expensive")
    public ResponseEntity<Optional<Product>> mostExpensive() {
        return ResponseEntity.ok(
                productService.getMostExpensiveProduct());
    }

    // 22. Cheapest product
    @GetMapping("/cheapest")
    public ResponseEntity<Optional<Product>> cheapest() {
        return ResponseEntity.ok(
                productService.getCheapestProduct());
    }

    // 23. Product ID -> Product map
    @GetMapping("/map")
    public ResponseEntity<Map<Integer, Product>> productMap() {
        return ResponseEntity.ok(
                productService.mapProductsById());
    }

    // 24. Average price by category
    @GetMapping("/avg-price-category")
    public ResponseEntity<Map<String, Double>> avgPriceCategory() {
        return ResponseEntity.ok(
                productService.getAveragePriceByCategory());
    }

    // 25. Top 3 expensive products by category
    @GetMapping("/top3-category")
    public ResponseEntity<Map<String, List<Product>>> top3Category() {
        return ResponseEntity.ok(
                productService.getTop3ExpensiveProductsByCategory());
    }

    // Existing CRUD APIs

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(
            @PathVariable int id) {
        return ResponseEntity.ok(
                productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Product> save(
            @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.save(product));
    }

    @PutMapping
    public ResponseEntity<Product> update(
            @RequestBody Product product) {
        return ResponseEntity.ok(
                productService.update(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable int id) {
        productService.delete(id);
        return ResponseEntity.ok("Deleted Successfully");
    }

}


