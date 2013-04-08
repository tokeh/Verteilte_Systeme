package blatt4.aufgabe;

import java.util.Map;
import java.util.UUID;

import blatt4.aufgabe.Message.MessageType;


public class BullyProcess extends Process {

	private UUID startedElectionUUID;
	private boolean electionRunning = false;
	private Thread timerThread;

	public BullyProcess(int id) {
		super(id);
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
						this.startElection();
					}
				} else if (message.getType().equals(MessageType.RESPONSE)) {

					if (message.getUuid().equals(this.startedElectionUUID)) {
						this.setElectionRunning(false);
					}
				}
			} else if (this.isElectionRunning()) {

				if (this.timerThread != null && !this.timerThread.isAlive()) {
					this.setElectionRunning(false);
					System.out.println("Process " + this.getID() + " is new leader!");
				}
			}
		}
	}

	@Override
	public synchronized void startElection() {
		this.setElectionRunning(true);
		this.setElectionUUID(UUID.randomUUID());

		for (Map.Entry<Integer, Process> dest : this.destinations.entrySet()) {

			if(dest.getValue().getID() > this.getID()) {
				dest.getValue().receiveMessage(new Message(MessageType.ELECT,
					this.getID(), this.startedElectionUUID));
			}
		}

		this.timerThread = new Thread(new TimerThread());
		timerThread.start();
	}

	private void setElectionUUID(UUID id) {
		this.startedElectionUUID = id;
	}

	public synchronized boolean isElectionRunning() {
		return this.electionRunning;
	}

	public synchronized void setElectionRunning(Boolean bool) {
		this.electionRunning = bool;
	}
}
