package com.kilfat.web.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kilfat.database.entity.Category;
import com.kilfat.web.model.deserializer.CategoryDeserializer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonDeserialize(using = CategoryDeserializer.class)
public class CategoryDTO {
    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    private String name;


    public static CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public static Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
