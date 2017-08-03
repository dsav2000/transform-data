package com.task.translator.parser;

public class ConfigurationParserException extends Exception {

	private static final long serialVersionUID = -8737930097041297516L;

	public ConfigurationParserException(String message, Throwable ex) {
		super(message, ex);
	}

	public ConfigurationParserException(String message) {
		super(message);
	}
	
}
