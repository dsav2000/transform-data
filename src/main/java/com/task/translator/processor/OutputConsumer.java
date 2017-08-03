package com.task.translator.processor;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OutputConsumer implements Closeable {

	public static final String DATA_FINISH = "DATA_FINISH";

	private String fileName;

	private BlockingQueue<String> sharedQueue;
	
	private CountDownLatch latch;

	public OutputConsumer(String fileName) {
		this.fileName = fileName;
		this.sharedQueue = new ArrayBlockingQueue<String>(1000000);
		this.latch = new CountDownLatch(1);
	}

	public void startConsume() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			try (FileWriter fileWriter = new FileWriter(fileName)) {
				BufferedWriter writer = new BufferedWriter(fileWriter);
				boolean process = true;
				while (process) {
					List<String> items = new ArrayList<String>();
					sharedQueue.drainTo(items);
					items.stream().filter(item->!item.equals(DATA_FINISH)).forEach(item->{
						try {
							writer.write(item);
							writer.write(System.getProperty("line.separator"));
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
					if (items.contains(DATA_FINISH)) {
						process = false;
						writer.flush();
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				latch.countDown();
			}
		});
	}

	public void write(String out) {
		try {
			sharedQueue.put(out);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stopConsume() {
		write(OutputConsumer.DATA_FINISH);
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {
		stopConsume();
	}
}
