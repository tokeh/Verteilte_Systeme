package server;

import java.io.IOException;
import java.util.Map;

import client.CorbaViewReceiver;

import forum.framework.IForumView;
import forum.framework.Position;
import gen.CorbaForumView;

public class CorbaViewForwarder implements IForumView {

	private CorbaViewReceiver receiver;

	public CorbaViewForwarder(CorbaForumView view) {
		this.receiver = new CorbaViewReceiver(view);
	}

	@Override
	public void notifyView(Map<String, Position> folks) throws IOException {
		this.receiver.notifyView(folks);
	}

}
