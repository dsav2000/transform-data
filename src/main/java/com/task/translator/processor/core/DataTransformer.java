package com.task.translator.processor.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.opencsv.CSVReader;
import com.task.translator.processor.OutputConsumer;
import com.task.translator.processor.RecordProcessor;

public class DataTransformer {

	public static Stream<String[]> csvStream(InputStream in) {
		final CSVReader cr = new CSVReader(new InputStreamReader(in), RecordProcessor.TAB.charAt(0));
		return StreamSupport.stream(new CsvSpliterator(cr), false).onClose(() -> {
			try {
				cr.close();
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		});
	}

	public void transformFile(String fileName, RecordProcessor processor, OutputConsumer out, int headerLength)
			throws FileNotFoundException, IOException {
		out.startConsume();
		try (FileInputStream fis = new FileInputStream(fileName)) {
			fis.skip(headerLength+1);
			csvStream(fis).parallel().map(fields -> processor.processRecordSplitted(fields))
					.forEach(out::write);
		}
	}
}
