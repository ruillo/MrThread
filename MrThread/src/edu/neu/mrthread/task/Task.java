package edu.neu.mrthread.task;

public abstract class Task {

	protected TaskContext context;

	public final void setContext(TaskContext context) {
		this.context = context;
	}

	protected final void write(String key, String line) {
		context.getWriter().write(key, line);
	}
}
