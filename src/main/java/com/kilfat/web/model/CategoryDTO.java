package com.kilfat.web.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kilfat.database.entity.Category;
import com.kilfat.database.entity.User;
import com.kilfat.web.model.deserializer.CategoryDeserializer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonDeserialize(using = CategoryDeserializer.class)
public class CategoryDTO {

    private Long id;

    @NotNull
    @Size(min = 5,
        max = 100)
    private String name;

    @NotNull
    @Size(min = 5,
        max = 20)
    private String userName;


    public CategoryDTO() {
    }

    public CategoryDTO(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }

    public static CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setUserName(category.getUser().getUsername());
        return categoryDTO;
    }

    public static Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        User user = new User();
        user.setUsername(categoryDTO.getUserName());
        category.setUser(user);
        return category;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
