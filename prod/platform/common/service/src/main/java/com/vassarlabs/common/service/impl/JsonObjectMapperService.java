package com.vassarlabs.common.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vassarlabs.common.service.api.IJsonObjectMapperService;

@Component
public class JsonObjectMapperService implements IJsonObjectMapperService{

	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public <E> E convertJsonStringToClassData(String jsonString, Class<E> classT) throws IOException{
		
		if(jsonString == null ||jsonString == "{}" )
			return null;
		
		return objectMapper.readValue(jsonString, classT);
	}
	
	@Override
	public <E> E convertJsonStringToClassData(String jsonString, TypeReference<E> typeRef) throws IOException{
		
		if(jsonString == null ||jsonString == "{}" )
			return null;
		
		return objectMapper.readValue(jsonString, typeRef);
	}
	
	@Override
	public String convertObjectToJsonString(Object jsonObject) throws IOException {
		
		if(jsonObject == null)
			return null;
		
		return objectMapper.writeValueAsString(jsonObject);
		
	}
}