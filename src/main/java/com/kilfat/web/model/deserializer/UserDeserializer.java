package com.kilfat.web.model.deserializer;

import static com.kilfat.web.model.deserializer.DeserializerHelper.getField;
import static com.kilfat.web.model.deserializer.DeserializerHelper.getFieldNode;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.kilfat.web.model.UserDTO;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.DatatypeConverter;

public class UserDeserializer extends JsonDeserializer<UserDTO> {

    @Override
    public UserDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(getField(node, "username"));
        userDTO.setPassword(getField(node, "password"));
        userDTO.setEmail(getField(node, "email"));
        String base64 = getField(node, "image");
        if (base64 != null) {
            userDTO.setImage(DatatypeConverter.parseBase64Binary(base64));
        }
        userDTO.setUserRole(getRoles(getFieldNode(node, "userRole")));
        return userDTO;
    }

    private Set<String> getRoles(JsonNode nodes) {
        Set<String> roles = new HashSet<>();
        if (nodes == null) {
            return roles;
        }
        for (JsonNode node : nodes) {
            roles.add(node.get("role").textValue());
        }
        return roles;
    }
}
