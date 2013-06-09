package server;

import forum.framework.ForumServer;

public class CorbaForumServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CorbaModelReceiver modelReceiver = new CorbaModelReceiver();
		
		ForumServer server = new ForumServer(modelReceiver);

		try {
			server.run();
		} catch (Exception e) {
			System.err.println("Server crashed!");
		}
	}

}
