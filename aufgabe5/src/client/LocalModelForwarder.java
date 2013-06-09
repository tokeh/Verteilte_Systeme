package client;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

import server.LocalModelReceiver;

import forum.framework.ForumView;
import forum.framework.IForumModel;
import forum.framework.IForumView;

public class LocalModelForwarder implements IForumModel {

	private LocalModelReceiver receiver;

	public LocalModelForwarder() {
		this.receiver = LocalModelReceiver.getInstance();
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
		this.receiver.registerView(name, new LocalViewReceiver(view));
	}

}
