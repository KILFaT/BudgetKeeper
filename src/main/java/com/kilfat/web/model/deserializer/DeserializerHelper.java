package com.kilfat.web.model.deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.kilfat.config.ServiceConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeserializerHelper {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(ServiceConstants.DATE_FORMAT);

    public static JsonNode getFieldNode(JsonNode node, String field) {
        if (node.get(field) != null) {
            return node.get(field);
        }
        return null;
    }

    public static String getField(JsonNode node, String field) {
        if (node.get(field) != null) {
            return node.get(field).textValue();
        }
        return "";
    }

    public static Integer getIntegerField(JsonNode node, String field) {
        if (node.get(field) != null) {
            return node.get(field).intValue();
        }
        return null;
    }

    public static Long getLongField(JsonNode node, String field) {
        if (node.get(field) != null) {
            return node.get(field).longValue();
        }
        return null;
    }

    public static Date getDateField(JsonNode node, String field) {
        long date;
        if (node.get(field) == null || node.get(field).isLong() == false) {
            return null;
        }
        date = node.get(field).asLong();
        try {
            return dateFormat.parse(date*1000L);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
