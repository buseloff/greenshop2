package com.busel.spring.shop_rest.services;

import com.busel.spring.shop_rest.daos.ProductDAO;
import com.busel.spring.shop_rest.entities.Product;
import com.busel.spring.shop_rest.exception_handling.NoSuchProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    @Transactional
    public Product getProduct(int id) {
        Product foundProduct = productDAO.getProduct(id);
        if (foundProduct == null) {
            throw new NoSuchProductException("There is no product with ID = " + id + " in Database");
        }
        return foundProduct;
    }

    @Override
    @Transactional
    public void saveProduct(Product product, MultipartFile file) {
        int maxUploadFileSize = 2 * 1024 * 1024;
        if (file.getSize() > maxUploadFileSize) {
            throw new RuntimeException("The size of image is too large. Max size must be equal "
                    + maxUploadFileSize / (1024 * 1024) + " Mb");
        }
        try {
            product.setImageData(file.getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        productDAO.saveProduct(product);
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }
}
