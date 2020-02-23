package edu.neu.mrthread.store.opt;

import edu.neu.mrthread.store.Operation;
import edu.neu.mrthread.store.Shard;

public class Clear extends Operation {
	public Clear(Shard shard, String space, String table) {
		super(shard, space, table);
	}

	public void perform() {
		// TODO remove all folders and files of the path
	}

	@Override
	public void connect() {
		// TODO
	}

	@Override
	public void close() {
		// TODO
	}

}
