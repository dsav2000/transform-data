package com.task.translator.processor;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.task.translator.config.Configuration;
import com.task.translator.config.ConfigurationBuilder;
import com.task.translator.parser.ConfigurationParserException;

public class HeaderRecordProcessorTest {

	@Test
	public void testProcessor() throws ConfigurationParserException, ProcessorException {
		
		String[] dataFileNames = new String[] { "src/test/resources/data.csv" };
		
		Configuration config = new ConfigurationBuilder().setDataConfigFile("src/test/resources/data.cfg")
				.setHeadersConfigFile("src/test/resources/headers.cfg")
				.setDataFileNames(dataFileNames).build();

		String headerRow = ProcessingManager.getHeaderRows(dataFileNames);

		ProcessorConfig processorConfig = new ProcessorConfigFactory().getProcessorConfig(headerRow, config);
		
		RecordProcessor headerProcessor = new RecordProcessorFactory().getRecordProcessor(RecordProcessorFactory.Type.HEADER, processorConfig);
		
		String newHeader = headerProcessor.processRecord(headerRow);
		
		assertThat("NEWCOL\tNEWCOL1\tNEWCOL2\tNEWCOL4", Matchers.equalTo(newHeader));
	}
}
