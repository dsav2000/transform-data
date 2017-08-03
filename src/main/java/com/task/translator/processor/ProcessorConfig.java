package com.task.translator.processor;

import java.util.List;

import com.task.translator.config.Configuration;

public class ProcessorConfig {

	private List<Integer> columnIndexesToProcess;

	private Configuration inputConfiguration;
	
	public List<Integer> getColumnIndexesToProcess() {
		return columnIndexesToProcess;
	}

	public void setColumnIndexesToProcess(List<Integer> columnIndexesToProcess) {
		this.columnIndexesToProcess = columnIndexesToProcess;
	}

	public Configuration getInputConfiguration() {
		return inputConfiguration;
	}

	public void setInputConfiguration(Configuration inputConfiguration) {
		this.inputConfiguration = inputConfiguration;
	}

}
