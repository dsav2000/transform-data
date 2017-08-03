package com.task.translator;

import com.task.translator.parser.ConfigurationParserException;
import com.task.translator.processor.ProcessingManager;
import com.task.translator.processor.ProcessorException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws ConfigurationParserException, ProcessorException {
		ProcessingManager manager = new ProcessingManager();
		manager.process(args[0], args[1], new String[] { args[2] });
	}
}
