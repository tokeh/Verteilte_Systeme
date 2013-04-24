package blatt4.aufgabe;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import blatt4.aufgabe.Message.MessageType;

public class BullyProcess extends Process {

	private UUID joinedElections;
	private boolean electionRunning = false;

	public BullyProcess(int id) {
		super(id);
	}

	public void run() {

		while(true) {
			
			try {
				Message message = this.msgQueue.poll(2, TimeUnit.SECONDS);

				if (message != null) {

					if (message.getType().equals(MessageType.ELECT)) {

						if (message.getSender() < this.getID()) {

							this.destinations.get(message.getSender()).receiveMessage(
									new Message(MessageType.RESPONSE, this.getID(), message.getUuid()));

							if (this.joinedElections == null || !this.joinedElections.equals(message.getUuid())) {
								this.joinedElections = message.getUuid();
								this.startElection(message.getUuid());
							}
						}
					} else if (message.getType().equals(MessageType.RESPONSE)) {
						this.electionRunning = false;
					}

				} else if (message == null && this.electionRunning) {
					this.electionRunning = false;
					System.out.println("Process " + (this.getID() + 1) + " is new leader!");
					setChanged();
					notifyObservers(this.getID());
				}
			} catch (InterruptedException e) {
				System.err.println("Something went wrong when waiting!");
			}
		}
	}

	@Override
	public void startElection(UUID uuid) {
		this.electionRunning = true;

		for (Map.Entry<Integer, Process> dest : this.destinations.entrySet()) {

			if(dest.getValue().getID() > this.getID()) {
				dest.getValue().receiveMessage(new Message(MessageType.ELECT,
					this.getID(), uuid));
			}
		}

		System.err.printf("Started election. Process: %d, UUID: %s%n", this.getID() + 1, uuid);
	}
}
