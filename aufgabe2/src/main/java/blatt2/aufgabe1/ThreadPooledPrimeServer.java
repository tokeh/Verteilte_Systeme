package blatt2.aufgabe1;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import blatt1.ServerEndpoint;
import blatt2.ressources.TaskThread;

public class ThreadPooledPrimeServer {

	private static volatile boolean done = false;

	private final ServerEndpoint endpoint;
	private int numberOfThreads;
	private ExecutorService executor;
	private final boolean optimize;

	//Server with dynamic thread pool.
	ThreadPooledPrimeServer() {
		this.endpoint = new ServerEndpoint();
		this.numberOfThreads = -1;
		this.executor = Executors.newCachedThreadPool();
		this.optimize = false;
	}

	//Server with fixed thread pool.
	ThreadPooledPrimeServer(int numOfThreads, boolean opti) {
		this.endpoint = new ServerEndpoint();
		this.numberOfThreads = numOfThreads;
		this.executor = Executors.newFixedThreadPool(this.numberOfThreads);
		this.optimize = opti;
	}

	void run() {

		//Calls optimize method in new thread if desired.
		if (this.optimize) {
			Thread optimizeThread = new Thread(new OptimizeThread(this));
			optimizeThread.start();
		}

		System.out.println("Thread Pooled PrimeServer up up and awaaay...");

		//Starting server-stopping thread.
		Thread stopThread = new Thread(new StopThread(this));
		stopThread.start();

		//Server running normally until done == true.
		while (!done) {
			ServerEndpoint.Request request = this.endpoint.blockingReceive();
			executor.execute(new TaskThread(request, this.endpoint));
		}

		//Shutting down after swallowing poison pill.
		System.err.println("Shutting down...");

		this.executor.shutdown();
		if (this.executor.isShutdown()) {
			System.err.println("Server is shut down.");
		}
	}

	public int getNumberOfThreads() {
		return this.numberOfThreads;
	}

	public synchronized void setNumberOfThreads(int num) {
		this.executor.shutdown();
		this.executor = Executors.newFixedThreadPool(num);
		this.numberOfThreads = num;
	}

	public synchronized void setDone(boolean d) {
		done = d;
	}

	public static void main(final String[] args) {

		Scanner sc = new Scanner(System.in);

		//Asking to check the optimal number of threads.
		System.out.println("Should I check the optimal number of" +
				" threads first?");
		boolean answerOk = false;
		boolean optimize = false;
		int threadNumber = 0;

		do {
			String answer = sc.nextLine();
			if (answer.equals("yes")) {
				System.out.println("What did your mother always tell you? " +
						"What's the magic word?");
				continue;

			} else if (answer.equals("yes please")) {
				answerOk = true;
				optimize = true;
				threadNumber = 1;
				continue;

			} else if (answer.equals("no")) {
				answerOk = true;
				//Manual definition of the number of threads.
				System.out.println("How many threads shall be used?" +
						" 0 for a dynamic pool.");
				threadNumber = sc.nextInt();
			}
		} while (!answerOk);

		if (threadNumber > 0) {
			//Starting server with fixed thread pool.
			new ThreadPooledPrimeServer(threadNumber, optimize).run();
		} else {
			//Starting server with dynamic thread pool.
			new ThreadPooledPrimeServer().run();
		}

		sc.close();
	}
}
