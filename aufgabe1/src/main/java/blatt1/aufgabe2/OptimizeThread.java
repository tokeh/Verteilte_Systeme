package blatt1.aufgabe2;

import blatt1.ressources.PrimeClient4ServerMeasurement;

public class OptimizeThread implements Runnable {

	private ThreadPooledPrimeServer server;

	public OptimizeThread(ThreadPooledPrimeServer ser) {
		this.server = ser;
	}

	public void run() {
		System.out.println("Start optimizing server...");
		long bestTime = Long.MAX_VALUE;
		long currentTime = 0;

		while (true) {
			int currentNumberOfThreads = this.server.getNumberOfThreads();
			
			System.out.println("Current number of threads: " + currentNumberOfThreads);
			
			long before = System.nanoTime();

			//Measure performance of server.
			try {
				PrimeClient4ServerMeasurement.main(new String[0]);
			} catch (InterruptedException e) {
				System.err.println("Measurement failed!" + e.getCause());
			}
			
			long after = System.nanoTime();

			currentTime = (after - before) / 1000000000;

			//If duration is less, increase number of threads and measure again.
			if (currentTime < bestTime) {
				bestTime = currentTime;
			} else {
				//If duration is equal or higher, decrease number of threads and finish.
				this.server.setNumberOfThreads(--currentNumberOfThreads);
				break;
			}

			this.server.setNumberOfThreads(++currentNumberOfThreads);
		}

		System.out.println(this.server.getNumberOfThreads() + " is the optimal" +
				" number of threads.");
		System.out.println("Finished optimizing server. Have fun...");
	}
}
