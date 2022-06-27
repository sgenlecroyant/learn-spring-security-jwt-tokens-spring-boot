package com.sgenlecroyant.security.config.websecurity.auth;

import java.io.IOException;

import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ResponseMessageSerializer extends JsonSerializer<Message>{

	@Override
	public void serialize(Message value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		LoggerFactory.getLogger(getClass()).info("serializing ...");

		gen.writeObject(value);
	}

}
