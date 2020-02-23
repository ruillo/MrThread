package edu.neu.mrthread.store.opt;

import java.util.Iterator;

import edu.neu.mrthread.store.Operation;
import edu.neu.mrthread.store.Shard;

public class Group extends Operation implements Iterable<Iterable<String>>, Iterator<Iterable<String>> {

	private ScanKey scanKey;
	private Query query;

	public Group(Shard shard, String space, String table) {
		super(shard, space, table);
		scanKey = new ScanKey(shard, space, table);
		scanKey.connect();
	}

	@Override
	public boolean hasNext() {
		return scanKey.hasNext();
	}

	@Override
	public Iterable<String> next() {
		String key = scanKey.next();
		if (query != null) {
			query.close();
		}
		query = new Query(shard, space, table, key);
		query.connect();
		return query;
	}

	@Override
	public Iterator<Iterable<String>> iterator() {
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
