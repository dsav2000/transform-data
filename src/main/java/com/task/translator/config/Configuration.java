package com.task.translator.config;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

	private Map<String, String> headersMap;
	
	private Map<String, String> dataMap;
	
	private String[] dataFileNames;
	
	public Configuration() {
		this.setHeadersMap(new HashMap<String, String>());
		this.setDataMap(new HashMap<String, String>());
	}

	public Map<String, String> getHeadersMap() {
		return headersMap;
	}

	public void setHeadersMap(Map<String, String> headersMap) {
		this.headersMap = headersMap;
	}

	public Map<String, String> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}

	public String[] getDataFileNames() {
		return dataFileNames;
	}

	public void setDataFileNames(String[] dataFileNames) {
		this.dataFileNames = dataFileNames;
	}
}
