package client;

import gen.CorbaForumView;
import gen.CorbaForumViewPOA;
import gen.PositionedAvatar;

public class CorbaViewReceiver extends CorbaForumViewPOA {

	private CorbaForumView view;

	public CorbaViewReceiver(CorbaForumView view) {
		this.view = view;
	}

	@Override
	public void notifyView(PositionedAvatar[] folks) {
		this.view.notifyView(folks);
	}

}
