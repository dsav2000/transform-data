package com.task.translator.processor;

import java.util.Map;

public class HeaderRecordProcessor extends RecordProcessor {

	public HeaderRecordProcessor(ProcessorConfig processorConfig) {
		super(processorConfig);
	}

	@Override
	public Map<String, String> getNewValuesMap(ProcessorConfig config) {
		return config.getInputConfiguration().getHeadersMap();
	}

}
