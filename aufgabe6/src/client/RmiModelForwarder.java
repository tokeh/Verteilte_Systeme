package client;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import server.IRemoteForumModel;

import forum.framework.IForumModel;
import forum.framework.IForumView;

public class RmiModelForwarder implements IForumModel {

	private IRemoteForumModel receiver;

	public RmiModelForwarder() throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("127.0.0.1");
		this.receiver = (IRemoteForumModel) registry.lookup("server");
	}

	@Override
	public void deregisterView(String name) throws NotBoundException,
			IOException {
		this.receiver.deregisterView(name);
	}

	@Override
	public void moveEast(String name) throws NotBoundException, IOException {
		this.receiver.moveEast(name);
	}

	@Override
	public void moveNorth(String name) throws NotBoundException, IOException {
		this.receiver.moveNorth(name);
	}

	@Override
	public void moveSouth(String name) throws NotBoundException, IOException {
		this.receiver.moveSouth(name);
	}

	@Override
	public void moveWest(String name) throws NotBoundException, IOException {
		this.receiver.moveWest(name);
	}

	@Override
	public void registerView(String name, IForumView view)
			throws AlreadyBoundException, IOException {
		this.receiver.registerView(name, new RmiViewReceiver(view));
	}

}
