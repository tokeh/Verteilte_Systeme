package blatt4.aufgabe;

public class TimerThread implements Runnable {

	public void run() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.err.println("Something went wrong when sleeping! " + e.getCause());
		}
	}
}
