package com.kilfat.web.controller;

import com.kilfat.config.ServiceConstants;
import com.kilfat.database.entity.Template;
import com.kilfat.database.service.interfaces.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ServiceConstants.TEMPLATE_PATH)
public class TemplateController {
    private TemplateService templateService;

    @Autowired
    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "{templateId}", method = RequestMethod.GET)
    public @ResponseBody
    Template getTemplate(@PathVariable("templateId") Long templateId) {
        return templateService.getTemplate(templateId);
    }

    @RequestMapping(value = "{templateId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putTemplate(@PathVariable("templateId") Long templateId, @RequestBody
        Template template) {
        template.setId(templateId);
        templateService.saveTemplate(template);
    }

    @RequestMapping(value = "{templateId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTemplate(@PathVariable("templateId") Long templateId) {
        templateService.deleteTemplate(templateId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Template createTemplate(@RequestBody Template template) {
        return templateService.saveTemplate(template);
    }
}
