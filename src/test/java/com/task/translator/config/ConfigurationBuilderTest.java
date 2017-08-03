package com.task.translator.config;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.task.translator.parser.ConfigurationParserException;

public class ConfigurationBuilderTest {

	@Test
	public void testConfigurationBuilder() throws ConfigurationParserException {
		Configuration config = new ConfigurationBuilder().setDataConfigFile("src/test/resources/data.cfg")
				.setHeadersConfigFile("src/test/resources/headers.cfg")
				.setDataFileNames(new String[] { "src/test/resources/data.csv" }).build();
		assertThat(config.getDataMap().get("ID"), Matchers.equalTo("NEWID"));
		assertThat(config.getDataMap().get("ID2"), Matchers.equalTo("NEWID2"));
		assertThat(config.getDataMap().get("ID3"), Matchers.equalTo("NEWID3"));
		
		assertThat(config.getHeadersMap().get("COL0"), Matchers.equalTo("NEWCOL"));
	}
}
