package edu.neu.mrthread.store;

import java.io.File;

import edu.neu.mrthread.core.MrThread;

public class Shard {

	private String ip;
	private int port;
	private int id;

	public Shard(String ip, int port, int id) {
		// TODO init ip,port,workerSize from line of ServerRegister.properties
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public int getId() {
		return id;
	}

	public String getRoot() {
		// TODO
		// if this is local(),
		return MrThread.ROOT;
		// else
		// return ip + port;
	}

	public String getSpacePath(String space) {
		return getRoot() + File.pathSeparator + space;
	}

	public String getTablePath(String space, String table) {
		return getRoot() + File.pathSeparator + space + File.pathSeparator + table;
	}

	public String getKeyPath(String space, String table, String key) {
		return getRoot() + File.pathSeparator + space + File.pathSeparator + table + File.pathSeparator + key;
	}

	public boolean isLocal() {
		// TODO
		return false;
	}
}
