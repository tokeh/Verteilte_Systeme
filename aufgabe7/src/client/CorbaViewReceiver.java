package client;

import forum.framework.IForumView;
import gen.CorbaForumView;
import gen.CorbaForumViewPOA;
import gen.PositionedAvatar;

public class CorbaViewReceiver extends CorbaForumViewPOA {

	private IForumView view;

	public CorbaViewReceiver(IForumView view) {
		this.view = view;
	}

	@Override
	public void notifyView(PositionedAvatar[] folks) {
		this.view.notifyView(folks);
	}

}
