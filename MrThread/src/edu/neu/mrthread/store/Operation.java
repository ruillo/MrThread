package edu.neu.mrthread.store;

public abstract class Operation {

	protected Shard shard;
	protected String space;
	protected String table;
	protected String key;

	public Operation(Shard shard, String space, String table) {
		this.shard = shard;
		this.space = space;
		this.table = table;
	}

	public Shard getShard() {
		return shard;
	}

	public String getSpace() {
		return space;
	}

	public String getTable() {
		return table;
	}

	public final void setKey(String key) {
		this.key = key;
	}

	public abstract void connect();

	public abstract void close();
}
