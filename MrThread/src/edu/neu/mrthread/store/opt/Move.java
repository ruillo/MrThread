package edu.neu.mrthread.store.opt;

import edu.neu.mrthread.store.Operation;
import edu.neu.mrthread.store.Shard;

public abstract class Move extends Operation {

	public Move(Shard shard, String space, String table) {
		super(shard, space, table);
	}

	public void perform() {
		// TODO move all files in the key, which satisfes the check(), of all shards to the local shard
	}

	public abstract boolean check(String key);

	@Override
	public void connect() {
		// TODO
	}

	@Override
	public void close() {
		// TODO
	}

}
