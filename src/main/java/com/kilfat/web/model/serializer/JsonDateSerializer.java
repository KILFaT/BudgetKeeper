package com.kilfat.web.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(ServiceConstants.UNIX_DATE_FORMAT);

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
        throws IOException, JsonProcessingException {
        String formattedDate = String.valueOf(date.getTime() / 1000L);
        gen.writeString(formattedDate);
    }
}