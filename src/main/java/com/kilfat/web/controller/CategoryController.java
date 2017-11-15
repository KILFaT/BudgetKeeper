package com.kilfat.web.controller;

import com.kilfat.config.ServiceConstants;
import com.kilfat.database.entity.Category;
import com.kilfat.database.service.interfaces.CategoryService;
import com.kilfat.web.model.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping(value = ServiceConstants.CATEGORY_PATH)
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "names",
        method = RequestMethod.GET)
    public @ResponseBody
    List<CategoryDTO> getCategories() {
        List<Category> categories = categoryService.getCategories();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for (Category category : categories) {
            categoryDTOs.add(CategoryDTO.convertToDTO(category));
        }
        return categoryDTOs;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "{categoryId}",
        method = RequestMethod.GET)
    public @ResponseBody
    CategoryDTO getCategory(
        @PathVariable("categoryId")
            Long categoryId) {
        Category category = categoryService.getCategory(categoryId);
        return CategoryDTO.convertToDTO(category);
    }

    @RequestMapping(value = "{categoryId}",
        method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putCategory(
        @PathVariable("categoryId")
            Long categoryId,
        @Valid
        @RequestBody
            CategoryDTO categoryDTO) {
        Category category = CategoryDTO.convertToEntity(categoryDTO);
        category.setId(categoryId);
        categoryService.saveCategory(category);
    }

    @RequestMapping(value = "{categoryId}",
        method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(
        @PathVariable("categoryId")
            Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    CategoryDTO createCategory(
        @Valid
        @RequestBody
            CategoryDTO categoryDTO) {
        Category category = CategoryDTO.convertToEntity(categoryDTO);
        category = categoryService.saveCategory(category);
        return CategoryDTO.convertToDTO(category);
    }
}
