package blatt2.aufgabe2;

import java.lang.management.ManagementFactory;

import blatt2.ressources.PrimeClient4ServerMeasurement;
import com.sun.management.OperatingSystemMXBean;

public class OptimizeThread implements Runnable {

	private ThreadPooledPrimeServer server;

	public OptimizeThread(ThreadPooledPrimeServer ser) {
		this.server = ser;
	}

	public void run() {
		System.out.println("Start optimizing server...");
		OperatingSystemMXBean osBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

		long runTime = System.nanoTime();
		long cpuTime = osBean.getProcessCpuTime();

		//Measure performance of server.
		try {
			PrimeClient4ServerMeasurement.main(new String[0]);
		} catch (InterruptedException e) {
			System.err.println("Measurement failed!" + e.getCause());
		}

		long runTimeEnd = System.nanoTime();
		long cpuTimeEnd = osBean.getProcessCpuTime();

		//Calculate cpu load of task, print result and set optimal thread number.
		cpuTime = cpuTimeEnd - cpuTime;
		runTime = runTimeEnd - runTime;
		double cpuLoad = (double) cpuTime / runTime;
		System.out.println("Number of Threads(exact): " + osBean.getAvailableProcessors() / cpuLoad);
		int numberOfThreads = (int) Math.ceil(osBean.getAvailableProcessors() / cpuLoad);

		server.setNumberOfThreads(numberOfThreads);

		System.out.println("CPU load of current job is: " + cpuLoad);
		System.out.println(numberOfThreads + " Threads will be used.");
		System.out.println("Finished optimizing server. Have fun...");
	}
}
