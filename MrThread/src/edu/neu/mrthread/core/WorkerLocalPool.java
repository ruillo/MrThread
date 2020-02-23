package edu.neu.mrthread.core;

import java.util.ArrayList;
import java.util.List;

public class WorkerLocalPool {
	private static List<Worker> pool = new ArrayList<Worker>();

	public static void init() {
		//TODO local worker
	}

	public static void clear() {
		pool.clear();
	}

	public static Iterable<Worker> workers() {
		return pool;
	}

	public static Worker worker(int index) {
		return pool.get(index);
	}

	public static int size() {
		return pool.size();
	}
}
