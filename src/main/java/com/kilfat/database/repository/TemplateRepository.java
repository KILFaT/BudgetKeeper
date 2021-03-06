package com.kilfat.database.repository;

import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.Category;
import com.kilfat.database.entity.Template;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends CrudRepository<Template, Long> {

    List<Template> findTemplatesByAccount(Account account);

    List<Template> findTemplatesByAccountAndCategory(Account account, Category category);
}
