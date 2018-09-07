package com.nikhilgupta.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtils {
	
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public static <T> T convertJsonToObject(String jsonFile , Class<T> classType) {
		T object = null;
		try {
			OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			object = OBJECT_MAPPER.readValue(new File("src/main/resources/jsonFiles/"+FilenameUtils.getName(jsonFile)),classType);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
}
