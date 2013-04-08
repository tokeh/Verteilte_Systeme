package blatt4.aufgabe;

public class GlobalSystem {
	private final static int PROCESSES = 10;

	void run() throws InterruptedException {
		Process[] processes = new Process[PROCESSES];

		for (int i = 0; i < processes.length; i++) {
			processes[i] = new BullyProcess(i);
		}

		for (Process source : processes) {
			for (Process destination : processes) {
				if (!source.equals(destination)) {
					source.connect(destination);
				}
			}
			new Thread(source).start();
		}

		processes[8].setActive(false);
		processes[9].setActive(false);
		processes[0].startElection();

		Thread.sleep(4000);
		processes[7].setActive(false);
		processes[4].startElection();
	}

	public static void main(String[] args) throws InterruptedException {
		new GlobalSystem().run();
	}

}