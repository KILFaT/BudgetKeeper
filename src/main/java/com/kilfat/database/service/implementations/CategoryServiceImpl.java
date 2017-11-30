package com.kilfat.database.service.implementations;


import com.kilfat.database.entity.Category;
import com.kilfat.database.repository.CategoryRepository;
import com.kilfat.database.service.interfaces.CategoryService;
import com.kilfat.database.service.interfaces.UserService;
import com.kilfat.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
            String.format("%s with id=%s is not found!", "Category", id)));
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        Iterable<Category> iterable = categoryRepository.findAll();
        iterable.iterator().forEachRemaining(categories::add);
        return categories;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getUserCategories() {
        return categoryRepository.findAllByUser(UserService.getCurrentUser().getUser());
    }
}
