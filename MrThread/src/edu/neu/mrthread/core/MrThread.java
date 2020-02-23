package edu.neu.mrthread.core;

import java.io.File;

public class MrThread {

	public static final String ROOT = "D:" + File.pathSeparator + "MrThread";

	public static enum TaskType {
		MAP, REDUCE, SHUFFLE, UNKNOWN
	};

	static {
		initServers();
		initStore();
		initStoreAccess();
		initWorker();
		initDistribution();
	}

	private static void initServers() {
		// TODO
	}

	private static void initStore() {
		// TODO
	}

	private static void initStoreAccess() {
		// TODO
	}

	private static void initWorker() {
		// TODO
	}

	private static void initDistribution() {
		// TODO
	}
}
