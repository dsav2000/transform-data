package com.task.translator.parser;

import java.util.Map;

public interface ConfigurationParser {

	public Map<String, String> parseConfiguration(String fileName) throws ConfigurationParserException;
}
