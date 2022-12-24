package com.busel.spring.shop_rest.controllers;

import com.busel.spring.shop_rest.entities.Category;
import com.busel.spring.shop_rest.exception_handling.NoSuchCategoryException;
import com.busel.spring.shop_rest.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/shop")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> showAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    public Category getCategory(@PathVariable int id) {
        Category category = categoryService.getCategory(id);
        if (category == null) {
            throw new NoSuchCategoryException("There is no category with ID = " + id + " in Database");
        }
        return category;
    }

    @PostMapping("/categories")
    public Category addNewCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
        return category;
    }

    @PutMapping("/categories")
    public Category updateCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
        return category;
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable int id) {
        Category category = categoryService.getCategory(id);
        if (category == null) {
            throw new NoSuchCategoryException("There is no category with ID = " + id + " in Database");
        }
        categoryService.deleteCategory(id);
        return "Category with ID = " + id + " was deleted";
    }

}

