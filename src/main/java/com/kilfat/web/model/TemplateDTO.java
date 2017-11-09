package com.kilfat.web.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kilfat.web.model.deserializer.TemplateDeserializer;

@JsonDeserialize(using = TemplateDeserializer.class)
public class TemplateDTO {

}
