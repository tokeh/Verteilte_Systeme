package client;

import forum.framework.ForumClient;

public class CorbaForumClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//run with -ORBInitialPort 1050 args

		CorbaModelForwarder modelForwarder = new CorbaModelForwarder(args);
		
		try {
			ForumClient client = new ForumClient(modelForwarder);
			client.register();
		} catch (Exception e) {
			System.err.println("Client crashed!");
			e.printStackTrace();
		}
	}

}
