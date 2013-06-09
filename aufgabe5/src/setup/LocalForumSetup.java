package setup;

import client.LocalModelForwarder;
import server.LocalModelReceiver;
import forum.framework.ForumClient;
import forum.framework.ForumServer;
import forum.framework.ForumView;

public class LocalForumSetup {

	private LocalForumSetup() { }
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ForumServer server = new ForumServer(LocalModelReceiver.getInstance());

		ForumClient clientOne = new ForumClient(new LocalModelForwarder());
		ForumClient clientTwo = new ForumClient(new LocalModelForwarder());
		clientOne.register();
		clientTwo.register();
	}

}
