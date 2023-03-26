package com.busel.spring.shop_rest.services;

import com.busel.spring.shop_rest.entities.Category;
import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();

    public void saveCategory(Category category);

    public Category getCategory(int id);

    public void deleteCategory(int id);
}
