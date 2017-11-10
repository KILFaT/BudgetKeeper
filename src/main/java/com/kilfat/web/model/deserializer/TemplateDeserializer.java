package com.kilfat.web.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.kilfat.web.model.TemplateDTO;

import java.io.IOException;

import static com.kilfat.web.model.deserializer.DeserializerHelper.getIntegerField;
import static com.kilfat.web.model.deserializer.DeserializerHelper.getLongField;

public class TemplateDeserializer extends JsonDeserializer<TemplateDTO> {

    @Override
    public TemplateDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        TemplateDTO dto = new TemplateDTO();
        dto.setId(getLongField(node, "id"));
        dto.setCategoryId(getLongField(node, "categoryId"));
        dto.setAccountId(getLongField(node, "accountId"));
        dto.setAmount(getIntegerField(node, "amount"));
        return dto;
    }
}
