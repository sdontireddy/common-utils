package com.ucbos.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
/**
 * JSONUtilities
 * @author sdontireddy
 *
 */
public class JSONUtil {

	private static final Configuration configuration = Configuration.builder()
			.jsonProvider(new JacksonJsonNodeJsonProvider()).mappingProvider(new JacksonMappingProvider()).build();
	
	
	public static JsonNode updateJSON(String jsonString , String jsonPath , String newValue){
		JsonNode tempNode = JsonPath.using(configuration).parse(jsonString)
		.set(jsonPath, newValue).json();
		
		return tempNode;
	}

}
