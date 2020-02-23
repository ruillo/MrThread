package edu.neu.mrthread.core;

import java.util.concurrent.CountDownLatch;

import edu.neu.mrthread.store.DataStore;
import edu.neu.mrthread.store.KeyLine;
import edu.neu.mrthread.store.opt.Group;
import edu.neu.mrthread.store.opt.Traverse;
import edu.neu.mrthread.task.Mapper;
import edu.neu.mrthread.task.Reducer;
import edu.neu.mrthread.task.Shuffler;
import edu.neu.mrthread.task.Task;
import edu.neu.mrthread.task.TaskContext;

public class Worker implements Runnable {

	private int id;
	private CountDownLatch cDown;
	private Task task;
	private TaskContext context;

	public Worker(int id) {
		this.id = id;
		this.context = new TaskContext();
	}

	public int getId() {
		return id;
	}

	public void setCountDown(CountDownLatch cDown) {
		this.cDown = cDown;
	}

	public void prepare(String url, String jobName, Task task, int order) {
		this.task = task;
		context.setWorkid(id);
		context.setJobName(jobName);
		context.setOrder(order);
		context.setLocalShard(DataStore.find(url, this.id));
		context.initWriter();
	}

	public final void run() {
		if (context.isMap()) {
			runMap();
		} else if (context.isShuffle()) {
			runShuffle();
		} else if (context.isReduce()) {
			runReduce();
		}
		cDown.countDown();
	}

	private void runMap() {
		Mapper mapper = (Mapper) task;
		Traverse traverse = new Traverse(context.getLocalShard(), context.getJobName(), context.getOrder() + "");
		traverse.connect();
		for (KeyLine keyLine : traverse) {
			mapper.map(keyLine.getKey(), keyLine.getLine());
		}
		traverse.close();
	}

	private void runShuffle() {
		Shuffler shuffler = (Shuffler) task;
		shuffler.shuffle();
	}

	private void runReduce() {
		Reducer reducer = (Reducer) task;
		Group group = new Group(context.getLocalShard(), context.getJobName(), context.getOrder() + "");
		for (Iterable<String> lines : group) {
			reducer.reduce(group.getTable(), lines);
		}
	}
}
