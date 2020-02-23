package edu.neu.mrthread.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRemote extends Remote {

	public void dispatch(String jobName, String taskClazz, int order) throws RemoteException;

	public void execute() throws RemoteException;
}
