package edu.neu.mrthread.store.opt;

import java.util.Iterator;

import edu.neu.mrthread.store.Operation;
import edu.neu.mrthread.store.Shard;

public class ScanKey extends Operation implements Iterable<String>, Iterator<String> {

	public ScanKey(Shard shard, String space, String table) {
		super(shard, space, table);
	}

	@Override
	public boolean hasNext() {
		// TODO
		return false;
	}

	@Override
	public String next() {
		// TODO
		return null;
	}

	@Override
	public Iterator<String> iterator() {
		return this;
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
