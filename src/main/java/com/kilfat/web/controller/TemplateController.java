package com.kilfat.web.controller;

import com.kilfat.config.ServiceConstants;
import com.kilfat.database.entity.Template;
import com.kilfat.database.service.interfaces.TemplateService;
import com.kilfat.web.model.TemplateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    TemplateDTO getTemplate(@PathVariable("templateId") Long templateId) {
        Template template = templateService.getTemplate(templateId);
        return TemplateDTO.convertToDTO(template);
    }

    @RequestMapping(value = "{templateId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putTemplate(@PathVariable("templateId") Long templateId,
                            @Valid @RequestBody TemplateDTO templateDTO) {
        Template template = TemplateDTO.convertToEntity(templateDTO);
        template.setId(templateId);
        templateService.saveTemplate(template);
    }

    @RequestMapping(value = "{templateId}",
            method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTemplate(@PathVariable("templateId") Long templateId) {
        templateService.deleteTemplate(templateId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    TemplateDTO createTemplate(
            @Valid @RequestBody TemplateDTO templateDTO) {
        Template template = TemplateDTO.convertToEntity(templateDTO);
        template = templateService.saveTemplate(template);
        return TemplateDTO.convertToDTO(template);
    }
}
