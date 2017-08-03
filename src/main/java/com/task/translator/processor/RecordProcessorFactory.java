package com.task.translator.processor;

public class RecordProcessorFactory {

	public static enum Type {
		DATA, HEADER
	};

	public RecordProcessor getRecordProcessor(Type type, ProcessorConfig processorConfig) {
		RecordProcessor processor = null;
		switch (type) {
		case DATA:
			processor = new DataRecordProcessor(processorConfig);
			break;
		case HEADER:
			processor = new HeaderRecordProcessor(processorConfig);
			break;
		}
		return processor;
	}
}
