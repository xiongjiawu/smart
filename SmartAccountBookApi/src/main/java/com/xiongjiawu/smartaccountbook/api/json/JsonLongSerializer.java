package com.xiongjiawu.smartaccountbook.api.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class JsonLongSerializer extends JsonSerializer<Long> {

	@Override
	public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		
		if(value != null) {
			if(value.longValue() > 9007199254740991L) {
				gen.writeString(String.valueOf(value));
			} else {
				gen.writeNumber(value.longValue());
			}
			
		} else {
			gen.writeNull();
		}
	}
}
