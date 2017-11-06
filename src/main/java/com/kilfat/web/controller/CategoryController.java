package com.kilfat.web.controller;

import com.kilfat.config.ServiceConstants;
import com.kilfat.database.entity.Category;
import com.kilfat.database.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "{categoryId}",
        method = RequestMethod.GET)
    public @ResponseBody
    Category getCategory(
        @PathVariable("categoryId")
            Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @RequestMapping(value = "{categoryId}",
        method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putCategory(
        @PathVariable("categoryId")
            Long categoryId,
        @Valid
        @RequestBody
            Category category) {
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
    Category createCategory(
        @Valid
        @RequestBody
            Category category) {
        return categoryService.saveCategory(category);
    }
}
