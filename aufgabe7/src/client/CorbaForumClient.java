package client;

import forum.framework.ForumClient;

public class CorbaForumClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CorbaModelForwarder modelForwarder = new CorbaModelForwarder();
		
		try {
			ForumClient client = new ForumClient(modelForwarder);
			client.register();
		} catch (Exception e) {
			System.err.println("Client crashed!");
		}
	}

}
