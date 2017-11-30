package com.kilfat.web.model.deserializer;

import static com.kilfat.web.model.deserializer.DeserializerHelper.getField;
import static com.kilfat.web.model.deserializer.DeserializerHelper.getLongField;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.kilfat.web.model.CategoryDTO;

import java.io.IOException;

public class CategoryDeserializer extends JsonDeserializer<CategoryDTO> {

    @Override
    public CategoryDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(getLongField(node, "id"));
        categoryDTO.setName(getField(node, "name"));
        categoryDTO.setUserName(getField(node, "userName"));
        return categoryDTO;
    }
}
