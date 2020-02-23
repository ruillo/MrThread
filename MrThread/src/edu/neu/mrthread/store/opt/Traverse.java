package edu.neu.mrthread.store.opt;

import java.util.Iterator;

import edu.neu.mrthread.store.KeyLine;
import edu.neu.mrthread.store.Operation;
import edu.neu.mrthread.store.Shard;

public class Traverse extends Operation implements Iterable<KeyLine>, Iterator<KeyLine> {

	public Traverse(Shard shard, String space, String table) {
		super(shard, space, table);
	}

	@Override
	public boolean hasNext() {
		// TODO
		return false;
	}

	@Override
	public KeyLine next() {
		// TODO
		return null;
	}

	@Override
	public Iterator<KeyLine> iterator() {
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
