package com.kilfat.database.service.interfaces;


import com.kilfat.database.entity.Category;

public interface CategoryService {

    Category getCategory(Long id);

    Category saveCategory(Category category);

    void deleteCategory(Category category);

    void deleteCategory(Long categoryId);
}
