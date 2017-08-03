package com.task.translator.processor;

import java.io.File;
import java.util.Scanner;

import com.task.translator.config.Configuration;
import com.task.translator.config.ConfigurationBuilder;
import com.task.translator.parser.ConfigurationParserException;
import com.task.translator.processor.core.DataTransformer;

public class ProcessingManager {

	public void process(String headersConfigFileName, String dataConfigFileName, String[] dataFileNames)
			throws ConfigurationParserException, ProcessorException {
		Configuration config = getConfiguration(headersConfigFileName, dataConfigFileName, dataFileNames);

		String headerRow = getHeaderRows(dataFileNames);

		ProcessorConfig processorConfig = getProcessorConfig(config, headerRow);
		
		RecordProcessor headerProcessor = getHeaderProcessor(processorConfig);
		
		RecordProcessor dataProcessor = getDataProcessor(processorConfig);
		
		String newHeader = headerProcessor.processRecord(headerRow);
		
		DataTransformer transofrmer = new DataTransformer();
		
		for (String fileName : dataFileNames) {
			try(OutputConsumer out = new OutputConsumer(fileName + ".out")) {
				out.write(newHeader);
				transofrmer.transformFile(fileName, dataProcessor, out, newHeader.length());
			} catch(Exception ex) {
				throw new ProcessorException("Unable to process input file: " + fileName, ex);
			}
		}
	}

	private RecordProcessor getHeaderProcessor(ProcessorConfig processorConfig) {
		return new RecordProcessorFactory().getRecordProcessor(RecordProcessorFactory.Type.HEADER, processorConfig);
	}

	private RecordProcessor getDataProcessor(ProcessorConfig processorConfig) {
		return new RecordProcessorFactory().getRecordProcessor(RecordProcessorFactory.Type.DATA, processorConfig);
	}

	private ProcessorConfig getProcessorConfig(Configuration config, String headerRow) {
		return new ProcessorConfigFactory().getProcessorConfig(headerRow, config);
	}

	private Configuration getConfiguration(String headersConfigFileName, String dataConfigFileName,
			String[] dataFileNames) throws ConfigurationParserException {
		return new ConfigurationBuilder().setHeadersConfigFile(headersConfigFileName)
				.setDataConfigFile(dataConfigFileName).setDataFileNames(dataFileNames).build();
	}

	public static String getHeaderRows(String[] dataFileNames) throws ProcessorException {
		String headerRow = null;
		if (dataFileNames != null && dataFileNames.length > 0) {
			File file = new File(dataFileNames[0]);
			try (Scanner sc = new Scanner(file)) {
				if (sc.hasNextLine()) {
					headerRow = sc.nextLine();
				}
			} catch (Exception e) {
				throw new ProcessorException("Unable to parse data file.", e);
			}
		} 
		return headerRow;
	}
}
