package com.kilfat.web.model.deserializer;

import static com.kilfat.web.model.deserializer.DeserializerHelper.getField;
import static com.kilfat.web.model.deserializer.DeserializerHelper.getLongField;

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
    public UserRoleDTO deserialize(JsonParser jsonParser, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setUserRoleId(getLongField(node, "userRoleId"));
        userRoleDTO.setUser(getField(node, "user"));
        userRoleDTO.setRole(getField(node, "role"));
        return userRoleDTO;
    }
}
