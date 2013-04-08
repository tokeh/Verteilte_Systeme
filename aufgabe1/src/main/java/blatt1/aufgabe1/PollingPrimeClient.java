package blatt1.aufgabe1;

import blatt1.ClientEndpoint;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class PollingPrimeClient {

	private final static int ONESECOND = 1000;
	private final long number;
	private Boolean isPrime;

	PollingPrimeClient(long num) {
		this.number = num;
	}

	public void run() {
		ClientEndpoint endpoint = new ClientEndpoint();
		SocketAddress server = new InetSocketAddress("localhost", 4711);

		endpoint.send(server, this.number);

		System.out.print("Die Zahl " + this.number + " ist");

		while( (this.isPrime = endpoint.nonBlockingReceive() ) == null) {
			System.out.print(".");

			try {
				Thread.sleep(ONESECOND);
			} catch (InterruptedException e) {
				System.err.println("Something went wrong when sleeping for" +
						" one second." + e.getCause());
			}

		}

		System.out.println((this.isPrime ? " eine " : " keine ")
				+ "Primzahl");
	}

	public static void main(final String[] args) {
		for ( long i = 1000000000000000000L; i < 1000000000000000010L; i++ ) {
		    new PollingPrimeClient(i).run();
		}
	}
}
