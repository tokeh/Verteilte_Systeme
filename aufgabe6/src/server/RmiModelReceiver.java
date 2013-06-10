package server;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import forum.framework.ForumModel;
import forum.framework.IForumView;

public class RmiModelReceiver extends UnicastRemoteObject implements IRemoteForumModel, Runnable {

	private static ForumModel model;

	public RmiModelReceiver() throws RemoteException, AlreadyBoundException {
		model = ForumModel.INSTANCE;
	}

	@Override
	public void deregisterView(String name) throws NotBoundException,
			IOException {
		model.deregisterView(name);
	}

	@Override
	public void moveEast(String name) throws NotBoundException, IOException {
		model.moveEast(name);
	}

	@Override
	public void moveNorth(String name) throws NotBoundException, IOException {
		model.moveNorth(name);
	}

	@Override
	public void moveSouth(String name) throws NotBoundException, IOException {
		model.moveSouth(name);
	}

	@Override
	public void moveWest(String name) throws NotBoundException, IOException {
		model.moveWest(name);
	}

	@Override
	public void registerView(String name, IForumView view)
			throws AlreadyBoundException, IOException {
		model.registerView(name, new RmiViewForwarder(view));
	}

	@Override
	public void run() {
		Registry registry;
		try {
			registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			registry.rebind("server", this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
