package com.vassarlabs.common.service.api;

import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Service to read/convert json to object and vice versa using jackson
 * @author vaibhav
 *
 */
public interface IJsonObjectMapperService {

	/**
	 * Convert json object to given Class <E> using {@link Class} in jackson
	 * @param jsonString
	 * @param classType
	 * @return
	 * @throws IOException
	 */
	public <E> E convertJsonStringToClassData(String jsonString, Class<E> classType) throws IOException;

	/**
	 * Convert json object to given Class <E> using {@link TypeReference}  in jackson
	 * @param jsonString
	 * @param classType
	 * @return
	 * @throws IOException
	 */
	public <E> E convertJsonStringToClassData(String jsonString, TypeReference<E> typeRef) throws IOException;

	/**
	 * Convert any object to String using jackson
	 * @param jsonObject
	 * @return
	 * @throws IOException
	 */
	public String convertObjectToJsonString(Object jsonObject) throws IOException;

}
