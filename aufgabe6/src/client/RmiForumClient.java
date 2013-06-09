package client;

import forum.framework.ForumClient;

public class RmiForumClient {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ForumClient client = new ForumClient(new RmiModelForwarder());
		client.register();
	}

}
