package com.kilfat.web.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.kilfat.web.model.UserRoleDTO;

import java.io.IOException;

public class UserRoleDeserializer extends JsonDeserializer<UserRoleDTO> {
    @Override
    public UserRoleDTO deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO

        return null;
    }
}
