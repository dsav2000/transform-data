package com.task.translator.processor;

import static org.junit.Assert.assertThat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringJoiner;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.task.translator.parser.ConfigurationParserException;

public class ProcessingManagerTest {

	private static final int TEST_FILE_SIZE = 10000;
	
	File genDataFolder = new File("temp");
	File genData = new File(genDataFolder, "data_gen.csv");
	File genDataOut = new File(genDataFolder, "data_gen.csv.out");

	@Before
	public void prepareInput() throws IOException {
		genDataFolder.mkdirs();
		try (FileWriter fis = new FileWriter(genData)) {
			fis.write("COL0\tCOL1\tCOL2\tCOL3\tCOL4\tCOL5\r\n");
			for (int i = 0; i < TEST_FILE_SIZE; i++) {
				StringJoiner joiner = new StringJoiner("\t", "", "\r\n");
				joiner.add("ID");
				for (int j = 0; j < 5; j++) {
					joiner.add("val" + i + "" + j);
				}
				fis.write(joiner.toString());
			}
		}
	}

	@Test
	public void testProcessing() throws ConfigurationParserException, ProcessorException, IOException {
		ProcessingManager manager = new ProcessingManager();
		manager.process("src/test/resources/headers.cfg", "src/test/resources/data.cfg",
				new String[] { genData.getAbsolutePath() });

		assertThat(countLines(genDataOut), Matchers.equalTo(countLines(genData)));
	}

	@After
	public void cleanFiles() {
		genData.delete();
		genDataOut.delete();
		genDataFolder.delete();
	}

	public static int countLines(File file) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(file));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}

}
