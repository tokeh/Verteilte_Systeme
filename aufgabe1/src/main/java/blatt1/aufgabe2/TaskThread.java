package blatt1.aufgabe2;

import blatt1.ServerEndpoint;

public class TaskThread implements Runnable {

	private ServerEndpoint.Request request;
	private ServerEndpoint endpoint;
	private ThreadPooledPrimeServer server;

	public TaskThread(ServerEndpoint.Request req, ServerEndpoint end, ThreadPooledPrimeServer serv) {
		this.request = req;
		this.endpoint = end;
		this.server = serv;
	}

	public void run() {
		boolean prime = isPrime(request.getNumber());
		endpoint.send(request.getSender(), prime);
	}

	private boolean isPrime (long number) {
		if (number == -1) {
			this.server.setDone(true);
			System.err.println("PoisonPill swallowed.");
			return false;
		}

		for (long i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}
