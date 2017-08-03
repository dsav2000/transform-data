package com.task.translator.parser;

public class ConfigurationParserFactory {

	public ConfigurationParser getConfigurationParser() {
		return new CSVConfigurationParser();
	}
}
