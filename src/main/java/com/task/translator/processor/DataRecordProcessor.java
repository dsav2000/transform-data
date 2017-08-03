package com.task.translator.processor;

import java.util.Map;

public class DataRecordProcessor extends RecordProcessor {

	public DataRecordProcessor(ProcessorConfig processorConfig) {
		super(processorConfig);
	}

	@Override
	public Map<String, String> getNewValuesMap(ProcessorConfig config) {
		return config.getInputConfiguration().getDataMap();
	}

}
