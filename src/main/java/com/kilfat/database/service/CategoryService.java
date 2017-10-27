package com.kilfat.database.service;


import com.kilfat.database.entity.Category;
import com.kilfat.database.repository.CategoryRepository;
import com.kilfat.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Category.class, id));
    }

    public Category saveCategory(Category Category) {
        return categoryRepository.save(Category);
    }

    public void deleteCategory(Category Category) {
        categoryRepository.delete(Category);
    }
}
