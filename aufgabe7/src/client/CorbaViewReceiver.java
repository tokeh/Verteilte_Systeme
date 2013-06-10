package client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
		Map<String, forum.framework.Position> map = new HashMap<String, forum.framework.Position>();
        
        for (PositionedAvatar folk : folks) {
            map.put(folk.name, forum.framework.Position.getPosition(folk.position.x, folk.position.y));
        }
        
        try {
            this.view.notifyView(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
