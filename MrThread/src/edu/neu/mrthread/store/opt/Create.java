package edu.neu.mrthread.store.opt;

import edu.neu.mrthread.store.KeyLine;
import edu.neu.mrthread.store.Operation;
import edu.neu.mrthread.store.Shard;

public class Create extends Operation {

	public Create(Shard shard, String space, String table) {
		super(shard, space, table);
	}
	public void perform(KeyLine keyLine) {
		// TODO Create the line with the key in the local shard, 
		// cache and flush
	}

	public void connect() {
		// TODO
	}
	

	@Override
	public void close() {
		// TODO
	}
}
