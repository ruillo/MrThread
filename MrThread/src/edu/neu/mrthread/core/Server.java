package edu.neu.mrthread.core;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.CountDownLatch;

import edu.neu.mrthread.task.Task;

public class Server implements Remote {

	private String ip;
	private int port;
	private int workerSize;

	public Server(String params) {
		// TODO init ip,port,workerSize from line of server.properties
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public String getUrl() {
		// TODO URL = ip + port
		return null;
	}

	public boolean isLocal() {
		// TODO
		return true;
	}

	public void dispatch(String jobName, String taskClazz, int order) throws RemoteException {
		try {
			Iterable<Worker> workers = WorkerLocalPool.workers();
			for (Worker worker : workers) {
				Task task = (Task) Class.forName(taskClazz).newInstance();
				worker.prepare(getUrl(), jobName, task, order);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void execute() throws RemoteException {
		CountDownLatch cDown = new CountDownLatch(workerSize);
		Iterable<Worker> workers = WorkerLocalPool.workers();
		for (Worker worker : workers) {
			worker.setCountDown(cDown);
			new Thread(worker).start();
		}
		try {
			cDown.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void listen() throws RemoteException {
		// TODO RemoteProxy.register itself, then start RMI listen
	}

}
