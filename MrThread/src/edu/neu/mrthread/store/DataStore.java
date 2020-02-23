package edu.neu.mrthread.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore {

	// String key is URL of a Server
	private static Map<String, List<Shard>> store = new HashMap<String, List<Shard>>();

	public static void init() {
		// TODO init as same with ServerRegistry 
	}

	public static Shard find(String url, int workerId) {
		return store.get(url).get(workerId);
	}

	public static Iterable<Shard> list(String url) {
		return store.get(url);
	}

}
