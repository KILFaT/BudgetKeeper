package com.kilfat.database.repository;

import com.kilfat.database.entity.Category;
import com.kilfat.database.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAllByUser(User user);
}
