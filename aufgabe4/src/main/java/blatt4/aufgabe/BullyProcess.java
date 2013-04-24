package blatt4.aufgabe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import blatt4.aufgabe.Message.MessageType;


public class BullyProcess extends Process {

	private HashMap<UUID, Long> joinedElection;
	private boolean electionRunning = false;
	private Thread timerThread;

	public BullyProcess(int id) {
		super(id);
		this.joinedElection = new HashMap<UUID, Long>();
	}

	public void run() {

		while(true) {

			
			if (this.msgQueue.peek() != null) {

				Message message = null;

				try {
					message = this.msgQueue.take();
				} catch (InterruptedException e) {
					System.err.println("Something went wrong when reading" +
							" from the message queue! " + e.getCause());
				}

				if (message.getType().equals(MessageType.ELECT)) {

					if (message.getSender() < this.getID()) {
						this.destinations.get(message.getSender()).receiveMessage(
								new Message(MessageType.RESPONSE, this.getID(), message.getUuid()));

						if (!this.joinedElection.containsKey(message.getUuid())) {
							this.joinedElection.put(message.getUuid(), message.getTime());
							this.reelect(message.getUuid());
						}
					}
				} else if (message.getType().equals(MessageType.RESPONSE)) {
					this.setElectionRunning(false);
				}

			} else if (this.isElectionRunning()) {

				if (this.timerThread != null && !this.timerThread.isAlive()) {
					this.setElectionRunning(false);
					System.out.println("Process " + (this.getID() + 1) + " is new leader!");
					setChanged();
					notifyObservers(this.getID());
				}
			}
		}
	}

	@Override
	public synchronized void startElection() {
		this.setElectionRunning(true);

		UUID electionUUID = UUID.randomUUID();

		for (Map.Entry<Integer, Process> dest : this.destinations.entrySet()) {

			if(dest.getValue().getID() > this.getID()) {
				dest.getValue().receiveMessage(new Message(MessageType.ELECT,
					this.getID(), electionUUID));
			}
		}

		this.timerThread = new Thread(new TimerThread());
		timerThread.start();
		System.err.printf("Started election. Process: %d, UUID: %s%n", this.getID(), electionUUID);
	}

	public synchronized boolean isElectionRunning() {
		return this.electionRunning;
	}

	public synchronized void setElectionRunning(Boolean bool) {
		this.electionRunning = bool;
	}

	private synchronized void reelect(UUID uuid) {
		this.setElectionRunning(true);

		for (Map.Entry<Integer, Process> dest : this.destinations.entrySet()) {

			if(dest.getValue().getID() > this.getID()) {
				dest.getValue().receiveMessage(new Message(MessageType.ELECT,
					this.getID(), uuid));
			}
		}

		this.timerThread = new Thread(new TimerThread());
		timerThread.start();
		System.err.printf("Reelection. Process: %d, UUID: %s%n", this.getID(), uuid.toString());
	}
}
