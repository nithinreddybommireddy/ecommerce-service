package com.trainingmug.ecommerce.util;

import com.trainingmug.ecommerce.model.Product;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
@Component
public class CsvReader {
    public List<Product> getProductFromCsv() throws FileNotFoundException {
        List<Product> products = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C://Users//nithi//Downloads//products.csv"));
            br.readLine();

            String productData = br.readLine();
            while (br.readLine() != null) {
                String[] data = br.readLine().split(",");
                Product product = new Product();
                product.setId(Integer.parseInt(data[0]));
                product.setName(data[1]);
                product.setMaxRetailPrice(Long.parseLong(data[2]));
                product.setDiscountPercentage(Integer.parseInt(data[3]));
                product.setAvailable(Boolean.parseBoolean(data[4]));
                product.setCompany(data[5]);
                product.setCategory(data[6]);
                product.setManufactureYear(Integer.parseInt(data[7]));
                products.add(product);
                productData = br.readLine();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return products;
    }
}
