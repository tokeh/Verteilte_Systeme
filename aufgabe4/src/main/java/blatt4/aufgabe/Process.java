package blatt4.aufgabe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Process implements Runnable {
	private final int id;
	private boolean active = true;
	protected BlockingQueue<Message> msgQueue = new LinkedBlockingQueue<Message>();
	protected final Map<Integer, Process> destinations = new HashMap<Integer, Process>();

	public abstract void startElection();

	public Process(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	void connect(Process destination) {
		destinations.put(destination.getID(), destination);
	}

	protected void receiveMessage(Message message) {
		if (active) {
			msgQueue.add(message);
		}
	}

}