package com.task.translator.processor;

public class ProcessorException extends Exception {

	private static final long serialVersionUID = -3069483929426429078L;

	public ProcessorException(String msg, Throwable tr) {
		super(msg, tr);
	}
	
	public ProcessorException(String msg) {
		super(msg);
	}

}
