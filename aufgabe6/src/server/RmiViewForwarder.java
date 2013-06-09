package server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;

import client.RmiViewReceiver;

import forum.framework.IForumView;
import forum.framework.Position;

public class RmiViewForwarder implements IForumView {
	
	private RmiViewReceiver receiver;

	public RmiViewForwarder(IForumView view) throws RemoteException {
		this.receiver = new RmiViewReceiver(view);
	}

	@Override
	public void notifyView(Map<String, Position> folks) throws IOException {
		this.receiver.notifyView(folks);
	}

}
