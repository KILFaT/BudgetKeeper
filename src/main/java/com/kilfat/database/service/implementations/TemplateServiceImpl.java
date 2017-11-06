package com.kilfat.database.service.implementations;

import com.kilfat.database.entity.Account;
import com.kilfat.database.entity.Category;
import com.kilfat.database.entity.Template;
import com.kilfat.database.repository.TemplateRepository;
import com.kilfat.database.service.interfaces.TemplateService;
import com.kilfat.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class TemplateServiceImpl implements TemplateService {

    private TemplateRepository templateRepository;

    @Autowired
    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Transactional(readOnly = true)
    public Template getTemplate(Long id) {
        return templateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
            String.format("%s with id=%s is not found!", "Template", id)));
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
