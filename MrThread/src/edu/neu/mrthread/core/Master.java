package edu.neu.mrthread.core;

import java.rmi.RemoteException;
import java.util.concurrent.CountDownLatch;

import edu.neu.mrthread.task.Job;

public class Master {

	private Job job;

	public Master() {
	}

	public void submitJob(Job job) {
		this.job = job;
	}

	public void runJob() throws RemoteException, InterruptedException {
		int index = 0;
		for (String taskClazz : job.tasks()) {
			runTask(index++, taskClazz);
		}
	}

	private void runTask(int index, String taskClazz) throws RemoteException, InterruptedException {
		runPrepare(index, taskClazz);
		run(index);
	}

	private void runPrepare(int index, String taskClazz) throws RemoteException {
		String name = job.getName();
		for (Server server : ServerRegistry.servers()) {
			if (server.isLocal()) {
				server.dispatch(name, taskClazz, index);
			} else {
				ServerRemote remote = ServerRegistry.find(server.getIp(), server.getPort());
				remote.dispatch(name, taskClazz, index);
			}
		}
	}

	private void run(int index) throws InterruptedException {
		CountDownLatch cDown = new CountDownLatch(ServerRegistry.size());
		for (Server server : ServerRegistry.servers()) {
			new Thread(new MasterRun(cDown, server)).start();
		}
		cDown.await();
	}

	class MasterRun implements Runnable {
		private CountDownLatch cDown;
		private Server server;

		private MasterRun(CountDownLatch cDown, Server server) {
			this.cDown = cDown;
			this.server = server;
		}

		@Override
		public void run() {
			try {
				if (server.isLocal()) {
					server.execute();
				} else {
					ServerRemote remote = ServerRegistry.find(server.getIp(), server.getPort());
					remote.execute();
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			cDown.countDown();
		}
	}
}
