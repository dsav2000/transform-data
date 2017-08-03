package com.task.translator.parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;

public class CSVConfigurationParser implements ConfigurationParser {

	public Map<String, String> parseConfiguration(String fileName) throws ConfigurationParserException {
		File input = new File(fileName);
		if(!input.exists()) {
			throw new ConfigurationParserException("Configuration file is not found: " + input.getAbsolutePath());
		}
		
		Map<String, String> configuration = new HashMap<String, String>();
		CSVParser parser = null;
		try {
			parser = CSVParser.parse(input, Charset.defaultCharset(), CSVFormat.TDF);
			for (CSVRecord record : parser) {
				String key = record.get(0);
				String value = record.get(1);
				configuration.put(key, value);
			}
		} catch(IOException ex) {
			throw new ConfigurationParserException("Unable to parse input CSV configuration: " + fileName, ex);
		} finally {
			IOUtils.closeQuietly(parser);
		}
		return configuration;
	}

}
