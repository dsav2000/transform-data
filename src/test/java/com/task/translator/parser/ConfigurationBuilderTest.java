package com.task.translator.parser;

import static org.junit.Assert.assertThat;

import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.task.translator.parser.ConfigurationParser;
import com.task.translator.parser.ConfigurationParserException;
import com.task.translator.parser.ConfigurationParserFactory;

public class ConfigurationBuilderTest {

	@Test
	public void testBuilder() throws ConfigurationParserException {
		ConfigurationParserFactory factory = new ConfigurationParserFactory();
		ConfigurationParser parser = factory.getConfigurationParser();
		Map<String, String> heads = parser.parseConfiguration("src/test/resources/headers.cfg");

		assertThat("NEWCOL", Matchers.equalTo(heads.get("COL0")));
		assertThat("NEWCOL1", Matchers.equalTo(heads.get("COL1")));
		assertThat("NEWCOL2", Matchers.equalTo(heads.get("COL2")));
		assertThat("NEWCOL4", Matchers.equalTo(heads.get("COL4")));
	}
}
