package edu.neu.mrthread.task;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import edu.neu.mrthread.core.Master;
import edu.neu.mrthread.core.MrThread.TaskType;

public class Job {

	private String name;
	private List<String> tasks;
	private List<TaskType> taskTypes;
	private Master master;

	public Job(String name) {
		this.name = name;
		this.tasks = new ArrayList<String>();
		this.master = new Master();
	}

	public String getName() {
		return this.name;
	}

	public void setMapper(String mapper) {
		tasks.add(mapper);
		taskTypes.add(TaskType.MAP);
	}

	public void setReducer(String reducer) {
		if (validate()) {
			tasks.add(Shuffler.class.getName());
			taskTypes.add(TaskType.SHUFFLE);
			tasks.add(reducer);
			taskTypes.add(TaskType.REDUCE);
		} else {
			throw new RuntimeException("A reducer should be after a Mapper");
		}
	}

	public void submit() {
		master.submitJob(this);
		try {
			master.runJob();
		} catch (RemoteException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Iterable<String> tasks() {
		return tasks;
	}

	public TaskType taskType(String task) {
		int index = tasks.indexOf(task);
		return index == -1 ? taskTypes.get(index) : TaskType.UNKNOWN;
	}

	private boolean validate() {
		return taskTypes.get(taskTypes.size() - 1) == TaskType.MAP;
	}

}
