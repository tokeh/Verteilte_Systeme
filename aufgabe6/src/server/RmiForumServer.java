package server;

import forum.framework.ForumServer;

public class RmiForumServer {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ForumServer server = new ForumServer(new RmiModelReceiver());
		server.run();
	}

}
