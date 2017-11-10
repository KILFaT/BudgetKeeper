package com.kilfat.web.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.kilfat.web.model.FundsTransactionDTO;

import java.io.IOException;

import static com.kilfat.web.model.deserializer.DeserializerHelper.*;

public class FundsTransactionDeserializer extends JsonDeserializer<FundsTransactionDTO> {
    @Override
    public FundsTransactionDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        FundsTransactionDTO dto = new FundsTransactionDTO();
        dto.setAccountId(getLongField(node, "accountId"));
        dto.setAmount(getIntegerField(node, "amount"));
        dto.setCategoryId(getLongField(node, "categoryId"));
        dto.setDate(getDateField(node, "date"));
        dto.setId(getLongField(node, "id"));
        dto.setTransactionType(getField(node, "transactionType"));
        return dto;
    }
}
