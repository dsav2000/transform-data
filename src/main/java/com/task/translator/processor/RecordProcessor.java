package com.task.translator.processor;

import java.util.Map;
import java.util.StringJoiner;

public abstract class RecordProcessor {

	public static final String TAB = "\t";
	
	private ProcessorConfig processorConfig;
	
	public String[] getItems(String input) {
		return  input.split(TAB);
	}
	
	public RecordProcessor(ProcessorConfig processorConfig) {
		this.processorConfig = processorConfig;
	}
	
	public String processRecord(String input) {
		String[] items = getItems(input);
		return processRecordSplitted(items);
	}

	public String processRecordSplitted(String[] items) {
		StringJoiner out = new StringJoiner(TAB);
		Map<String, String> newValuesMap = getNewValuesMap(processorConfig);
		for (Integer index : processorConfig.getColumnIndexesToProcess()) {
			out.add(newValuesMap.getOrDefault(items[index], items[index]));
		}
		return out.toString();
	}

	public abstract Map<String, String> getNewValuesMap(ProcessorConfig config);
	
}