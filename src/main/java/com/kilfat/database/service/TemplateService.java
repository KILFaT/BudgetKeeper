package com.kilfat.database.service;

import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.Category;
import com.kilfat.database.entity.Template;
import com.kilfat.database.repository.TemplateRepository;
import com.kilfat.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Secured({"USER","ADMIN"})
public class TemplateService {

    private TemplateRepository templateRepository;

    @Autowired
    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Transactional(readOnly = true)
    public Template getTemplate(Long id) {
        return templateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Template.class, id));
    }

    public Template saveTemplate(Template template) {
        return templateRepository.save(template);
    }

    public void deleteTemplate(Template template) {
        templateRepository.delete(template);
    }

    public List<Template> findTemplatesByAccount(Account account) {
        return templateRepository.findTemplatesByAccount(account);
    }

    public List<Template> findTemplatesByAccountAndCategory(Account account, Category category) {
        return templateRepository.findTemplatesByAccountAndCategory(account, category);
    }

    public void deleteTemplate(Long templateId) {
        templateRepository.deleteById(templateId);
    }
}
