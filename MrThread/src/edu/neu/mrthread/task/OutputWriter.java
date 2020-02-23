package edu.neu.mrthread.task;

import edu.neu.mrthread.store.KeyLine;
import edu.neu.mrthread.store.opt.Create;

public class OutputWriter {
	private Create create;

	public OutputWriter(TaskContext context) {
		create = new Create(context.getLocalShard(), context.getJobName(), context.getOrder() + "");
		create.connect();
	}

	public void write(String key, String line) {
		create.perform(new KeyLine(key, line));
	}

	public void close() {
		create.close();
	}
}
