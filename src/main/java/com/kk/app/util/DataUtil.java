package com.kk.app.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DataUtil {

	private static Map<String , List<String>> idMap;

	public DataUtil(){
		System.out.println("+++++++++ initializing data map");
		idMap = new HashMap<>();
	}

	public static Map<String, List<String>> getIdMap() {
		return idMap;
	}

	public static void setIdMap(Map<String, List<String>> idMap) {
		DataUtil.idMap = idMap;
	}
	
}
