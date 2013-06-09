package client;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import forum.framework.ForumView;
import forum.framework.IForumView;
import forum.framework.Position;

public class RmiViewReceiver extends UnicastRemoteObject implements IRemoteForumView {

	private IForumView view;

	public RmiViewReceiver(IForumView view) throws RemoteException {
		this.view = view;
	}

	@Override
	public void notifyView(Map<String, Position> folks) throws IOException {
		this.view.notifyView(folks);
	}

}
