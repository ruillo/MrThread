package edu.neu.mrthread.task;

import edu.neu.mrthread.core.MrThread.TaskType;
import edu.neu.mrthread.store.Shard;

public class TaskContext {

	private int workid;
	private String jobName;
	private TaskType type;
	private int order;
	private Shard localShard;
	private OutputWriter writer;

	public void initWriter() {
		if (writer != null) {
			writer.close();
		}
		writer = new OutputWriter(this);
	}

	public int getWorkid() {
		return workid;
	}

	public void setWorkid(int workid) {
		this.workid = workid;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public boolean isMap() {
		return type == TaskType.MAP;
	}

	public boolean isReduce() {
		return type == TaskType.REDUCE;
	}

	public boolean isShuffle() {
		return type == TaskType.SHUFFLE;
	}

	public void setType(TaskType type) {
		this.type = type;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Shard getLocalShard() {
		return localShard;
	}

	public void setLocalShard(Shard localShard) {
		this.localShard = localShard;
	}

	public OutputWriter getWriter() {
		return writer;
	}

}
