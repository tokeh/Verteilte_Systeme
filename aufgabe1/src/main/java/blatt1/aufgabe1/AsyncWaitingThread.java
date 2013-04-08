package blatt1.aufgabe1;

import blatt1.ClientEndpoint;

public class AsyncWaitingThread implements Runnable {

	private ClientEndpoint endpoint;

	public AsyncWaitingThread (ClientEndpoint endP) {
		this.endpoint = endP;
	}

	public void run() {

		boolean isPrime = endpoint.blockingReceive();

		System.out.println((isPrime ? " eine " : " keine ")
				+ "Primzahl");
	}

}
