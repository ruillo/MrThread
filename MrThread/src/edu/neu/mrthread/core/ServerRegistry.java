package edu.neu.mrthread.core;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class ServerRegistry {

	private static List<Server> registry = new ArrayList<Server>();

	public static void init() {
		// TODO server.properties the first line, then start listen the local one
	}

	public static Server local() {
		// TODO return the local server on the running computers
		return null;
	}

	public static Iterable<Server> servers() {
		return registry;
	}

	public static int size() {
		return registry.size();
	}

	public static void register() throws RemoteException {
		Server server = local();
		Registry registry = LocateRegistry.createRegistry(server.getPort());
		registry.rebind(Server.class.getSimpleName(), server);
	}

	public static ServerRemote find(String ip, int port) throws RemoteException {
		Registry registry = LocateRegistry.getRegistry(ip, port);
		ServerRemote server = null;
		try {
			server = (ServerRemote) registry.lookup(Server.class.getSimpleName());

		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return server;
	}
}
