package com.busel.spring.shop_rest.controllers;

import com.busel.spring.shop_rest.entities.Category;
import com.busel.spring.shop_rest.entities.Product;
import com.busel.spring.shop_rest.exception_handling.NoSuchCategoryException;
import com.busel.spring.shop_rest.services.CategoryService;
import com.busel.spring.shop_rest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/shop")
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> showAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        return allProducts;
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable int id) {
        Product product = productService.getProduct(id);
        return product;
    }

    @PostMapping(path = "/products", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProduct(@ModelAttribute Product product,
                                 @RequestParam("imageData") MultipartFile file) {
        int categoryId = Integer.parseInt(product.getCategory().getTitle());
        List<Category> listOfCategories = categoryService.getAllCategories();
        Category productCategory = new Category();
        listOfCategories.forEach(category -> {
            if (category.getId() == categoryId) {
                productCategory.setId(categoryId);
                productCategory.setTitle(category.getTitle());
            }
        });

        if (productCategory.getId() == null) {
            throw new NoSuchCategoryException("There is no category with ID = "
                    + categoryId + " in Database");
        }
        product.setCategory(productCategory);
        productService.saveProduct(product, file);
        return product;
    }

    @PutMapping("/products")
    public Product updateProduct(@ModelAttribute Product product,
                                 @RequestParam("imageData") MultipartFile file) {
        int categoryId = Integer.parseInt(product.getCategory().getTitle());
        List<Category> listOfCategories = categoryService.getAllCategories();
        Category productCategory = new Category();
        listOfCategories.forEach(category -> {
            if (category.getId() == categoryId) {
                productCategory.setId(categoryId);
                productCategory.setTitle(category.getTitle());
            }
        });

        if (productCategory.getId() == null) {
            throw new NoSuchCategoryException("There is no category with ID = "
                    + categoryId + " in Database");
        }
        product.setCategory(productCategory);
        productService.saveProduct(product, file);
        return product;
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable int id) {
        Product product = productService.getProduct(id);
        if (product.getId() > 0) {
            productService.deleteProduct(id);
        }
        return "Product with ID = " + id + " was deleted";
    }
}
