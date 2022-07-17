package com.xiongjiawu.smartaccountbook.api.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@JsonComponent
public class JsonDateDeserializer extends JsonDeserializer<Date> {
    private final Logger log = LoggerFactory.getLogger(JsonDateDeserializer.class);
    private String[] patterns = {"yyyy","yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-ddTHH:mm:ss.SSSZ"};
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String dateAsString = jsonParser.getText();
        if (dateAsString == null || "".equals(dateAsString) || dateAsString.length() < 1) {
            return null;
        }
        Date parseDate = null;
        try {
            parseDate = DateUtils.parseDate(dateAsString, patterns);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getCause());
        }

        return parseDate;
    }
}
