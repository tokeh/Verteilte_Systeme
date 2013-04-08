package blatt2.ressources;

import blatt1.ClientEndpoint;

public class AsyncWaitingThread implements Runnable {

	private ClientEndpoint endpoint;
	private AsyncPrimeClient client;

	public AsyncWaitingThread (ClientEndpoint endP, AsyncPrimeClient cl) {
		this.endpoint = endP;
		this.client = cl;
	}

	public void run() {

		boolean isPrime = endpoint.blockingReceive();

		System.out.println((isPrime ? " eine " : " keine ")
				+ "Primzahl");
		
		this.client.setDone(true);
	}
}
