package com.busel.spring.shop_rest.services;

import com.busel.spring.shop_rest.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    public void saveProduct(Product product, MultipartFile file);

    public Product getProduct(int id);

    public void deleteProduct(int id);
}
