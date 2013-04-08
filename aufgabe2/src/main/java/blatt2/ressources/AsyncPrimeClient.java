package blatt2.ressources;

import blatt1.ClientEndpoint;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class AsyncPrimeClient implements Runnable {

	private volatile boolean done = false;

	private final static int ONESECOND = 1000;
	private final long number;

	AsyncPrimeClient(long num) {
		this.number = num;
	}

	public void run() {
		ClientEndpoint endpoint = new ClientEndpoint();
		SocketAddress server = new InetSocketAddress("localhost", 4711);

		endpoint.send(server, this.number);

		System.out.print("Die Zahl " + this.number + " ist");

		Thread waitingThread = new Thread(new AsyncWaitingThread(endpoint, this));
		waitingThread.start();

		while (!done) {

			System.out.print(".");

			try {
				Thread.sleep(ONESECOND);
			} catch (InterruptedException e) {
				System.err.println("Something went wrong when sleeping for" +
						" one second." + e.getCause());
			}
		}
	}

	public void setDone(boolean d) {
		this.done = d;
	}

	public static void main(final String[] args) {

		for ( long i = 1000000000000000000L; i < 1000000000000000010L; i++ ) {
		    new AsyncPrimeClient(i).run();
			//Thread thread = new Thread(new AsyncPrimeClient(i));
			//thread.start();
		}
	}
}
