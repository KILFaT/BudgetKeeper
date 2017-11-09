package com.kilfat.web.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.kilfat.web.model.AccountDTO;

import java.io.IOException;

import static com.kilfat.web.model.deserializer.DeserializerHelper.*;

public class AccountDeserializer extends JsonDeserializer<AccountDTO> {

    @Override
    public AccountDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(getLongField(node, "id"));
        accountDTO.setUserName(getField(node, "username"));
        accountDTO.setAccountType(getField(node, "accountType"));
        accountDTO.setAmount(getIntegerField(node, "amount"));
        return accountDTO;
    }
}