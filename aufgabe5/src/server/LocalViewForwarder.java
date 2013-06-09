package server;

import java.io.IOException;
import java.util.Map;

import client.LocalViewReceiver;

import forum.framework.IForumView;
import forum.framework.Position;

public class LocalViewForwarder implements IForumView {

	private LocalViewReceiver receiver;

	public LocalViewForwarder(IForumView view) {
		this.receiver = new LocalViewReceiver(view);
		//alternativ this.receiver = (LocalViewReceiver) view;
		//müsste eigentlich auch gehen, da view ein LocalViewReceiver ist.
	}

	@Override
	public void notifyView(Map<String, Position> folks) throws IOException {
		this.receiver.notifyView(folks);
	}

}
