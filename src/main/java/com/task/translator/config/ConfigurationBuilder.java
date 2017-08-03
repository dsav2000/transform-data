package com.task.translator.config;

import com.task.translator.parser.ConfigurationParser;
import com.task.translator.parser.ConfigurationParserException;
import com.task.translator.parser.ConfigurationParserFactory;

public class ConfigurationBuilder {

	private String headersConfigFile;
	
	private String dataConfigFile;
	
	private String[] dataFileNames;
	
	public ConfigurationBuilder setHeadersConfigFile(String fileName) {
		this.headersConfigFile = fileName;
		return this;
	}
	
	public ConfigurationBuilder setDataConfigFile(String fileName) {
		this.dataConfigFile = fileName;
		return this;
	}
	
	public ConfigurationBuilder setDataFileNames(String[] fileNames) {
		this.dataFileNames = fileNames;
		return this;
	}

	public Configuration build() throws ConfigurationParserException {
		Configuration config = new Configuration();
		ConfigurationParser parser = new ConfigurationParserFactory().getConfigurationParser();
		if(headersConfigFile!=null) {
			config.setHeadersMap(parser.parseConfiguration(headersConfigFile));
		}
		if(dataConfigFile!=null) {
			config.setDataMap(parser.parseConfiguration(dataConfigFile));
		}
		config.setDataFileNames(dataFileNames);
		return config;
	}
}
