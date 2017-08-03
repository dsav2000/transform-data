package com.task.translator.processor;

import java.util.LinkedList;
import java.util.List;

import com.task.translator.config.Configuration;

public class ProcessorConfigFactory {

	public ProcessorConfig getProcessorConfig(String headerRow, Configuration config) {

		List<Integer> indexesToProcess = getIndexes(headerRow, config);
		
		return createProcessorConfig(indexesToProcess, config);
	}

	private ProcessorConfig createProcessorConfig(List<Integer> indexesToProcess, Configuration config) {
		ProcessorConfig result = new ProcessorConfig();
		result.setColumnIndexesToProcess(indexesToProcess);
		result.setInputConfiguration(config);
		return result;
	}

	private List<Integer> getIndexes(String headerRow, Configuration config) {
		String[] headers = headerRow.split("\t");
		List<Integer> indexesToProcess = new LinkedList<Integer>();
		
		for (int i = 0; i < headers.length; i++) {
			String header = headers[i].trim();
			if(config.getHeadersMap().containsKey(header)) {
				indexesToProcess.add(i);
			};
		}
		return indexesToProcess;
	}
}
