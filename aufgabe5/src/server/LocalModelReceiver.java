package server;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

import forum.framework.ForumModel;
import forum.framework.IForumModel;
import forum.framework.IForumView;

public class LocalModelReceiver implements IForumModel, Runnable {

	private static final LocalModelReceiver INSTANCE = new LocalModelReceiver();
	private ForumModel model;

	private LocalModelReceiver() {
		this.model = ForumModel.INSTANCE;
	}

	@Override
	public void deregisterView(String name) throws NotBoundException,
			IOException {
		this.model.deregisterView(name);
	}

	@Override
	public void moveEast(String name) throws NotBoundException, IOException {
		this.model.moveEast(name);
	}

	@Override
	public void moveNorth(String name) throws NotBoundException, IOException {
		this.model.moveNorth(name);
	}

	@Override
	public void moveSouth(String name) throws NotBoundException, IOException {
		this.model.moveSouth(name);
	}

	@Override
	public void moveWest(String name) throws NotBoundException, IOException {
		this.model.moveWest(name);
	}

	@Override
	public void registerView(String name, IForumView view)
			throws AlreadyBoundException, IOException {
		this.model.registerView(name, new LocalViewForwarder(view));
	}

	@Override
	public void run() {
		
	}

	public static LocalModelReceiver getInstance() {
		return INSTANCE;
	}

}
