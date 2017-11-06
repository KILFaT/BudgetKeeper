package com.kilfat.database.service.interfaces;

import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.Category;
import com.kilfat.database.entity.Template;

import java.util.List;

public interface TemplateService {

    Template getTemplate(Long id);

    Template saveTemplate(Template template);

    void deleteTemplate(Template template);

    List<Template> findTemplatesByAccount(Account account);

    List<Template> findTemplatesByAccountAndCategory(Account account, Category category);

    void deleteTemplate(Long templateId);
}
