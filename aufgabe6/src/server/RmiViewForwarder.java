package server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;

import forum.framework.IForumView;
import forum.framework.Position;

public class RmiViewForwarder implements IForumView {
	
	private IForumView view;

	public RmiViewForwarder(IForumView view) throws RemoteException {
		this.view = view;
	}

	@Override
	public void notifyView(Map<String, Position> folks) throws IOException {
		this.view.notifyView(folks);
	}

}
