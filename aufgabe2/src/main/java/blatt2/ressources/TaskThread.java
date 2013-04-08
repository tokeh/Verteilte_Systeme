package blatt2.ressources;

import blatt1.ServerEndpoint;

public class TaskThread implements Runnable {

	private ServerEndpoint.Request request;
	private ServerEndpoint endpoint;

	public TaskThread(ServerEndpoint.Request req, ServerEndpoint end) {
		this.request = req;
		this.endpoint = end;
	}

	public void run() {
		boolean prime = isPrime(request.getNumber());
		endpoint.send(request.getSender(), prime);
	}

	private boolean isPrime (long number) {

		for (long i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}
