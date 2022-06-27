package com.sgenlecroyant.security.config.websecurity.auth;

import java.io.IOException;

import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class ResponseMessageDeserializer extends JsonDeserializer<Message>{

	@Override
	public Message deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		Message readValueAs = p.readValueAs(Message.class);
		LoggerFactory.getLogger(getClass()).info("deserializing ...");
		return readValueAs;
	}

}
