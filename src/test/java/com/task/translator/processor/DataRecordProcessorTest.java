package com.task.translator.processor;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.task.translator.config.Configuration;
import com.task.translator.config.ConfigurationBuilder;
import com.task.translator.parser.ConfigurationParserException;

public class DataRecordProcessorTest {

	@Test
	public void testDataRecordProcessor() throws ConfigurationParserException, ProcessorException {
		String[] dataFileNames = new String[] { "src/test/resources/data.csv" };
		
		Configuration config = new ConfigurationBuilder().setDataConfigFile("src/test/resources/data.cfg")
				.setHeadersConfigFile("src/test/resources/headers.cfg")
				.setDataFileNames(dataFileNames).build();
		
		String headerRow = ProcessingManager.getHeaderRows(dataFileNames);

		ProcessorConfig processorConfig = new ProcessorConfigFactory().getProcessorConfig(headerRow, config);
		
		RecordProcessor dataProcessor = new RecordProcessorFactory().getRecordProcessor(RecordProcessorFactory.Type.DATA, processorConfig);
		
		String result = dataProcessor.processRecord("ID\tval11\tval12\tval13\tval14\tval15");
		
		assertThat(result, Matchers.equalTo("NEWID\tval11\tval12\tval14"));
	}
}
