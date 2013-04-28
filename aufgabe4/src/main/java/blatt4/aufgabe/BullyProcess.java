package blatt4.aufgabe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import blatt4.aufgabe.Message.MessageType;

public class BullyProcess extends Process {

	private Map<Long, UUID> joinedElections;
	private boolean electionRunning;
	private boolean checkMaster;

	private int master;

	public BullyProcess(int id) {
		super(id);
		this.master = -1;
		this.electionRunning = false;
		this.checkMaster = false;
		this.joinedElections = new HashMap<Long, UUID>();
	}

	public void run() {

		new Thread(new TimerThread(this, MessageType.CHECK_MASTER, 5000, true)).start();
		new Thread(new TimerThread(this, MessageType.DELETE_UUIDS, 20000, true)).start();

		while(true) {
			
			try {
				Message message = this.msgQueue.take();
				
				switch(message.getType()) {

					case ELECT:
						if (message.getSender() < this.getID()) {
	
							this.destinations.get(message.getSender()).receiveMessage(
								new Message(MessageType.RESPONSE, this.getID(), message.getUuid()));
	
							if (this.joinedElections.isEmpty() || !this.joinedElections.containsValue(message.getUuid())) {
								this.joinedElections.put(System.currentTimeMillis(), message.getUuid());
								this.startElection(message.getUuid());
							}
						};
						break;
						
					case RESPONSE:
						this.electionRunning = false;
						break;
						
					case ELECTION_TIMER:
						if (this.electionRunning) {
							this.electionRunning = false;
							this.master = this.getID();
	
							for (Map.Entry<Integer, Process> dest : this.destinations.entrySet()) {
	
								dest.getValue().receiveMessage(new Message(MessageType.NEW_MASTER,
										this.getID(), null));
							}
	
							System.out.println("Process " + (this.getID() + 1) + " is new leader!");
							setChanged();
							notifyObservers(this.getID());
						};
						break;
					
					case CHECK_MASTER:
						this.checkMaster();
						break;
	
					case R_U_ALIVE:
						this.destinations.get(message.getSender()).receiveMessage(new Message(MessageType.STILL_ALIVE, this.getID(), null));
						break;
					
					case STILL_ALIVE:
						if (this.checkMaster) {
							this.checkMaster = false;
						};
						break;
					
					case MASTER_TIMER:
						if (this.checkMaster) {
							startElection(UUID.randomUUID());
						}
						break;
					
					case NEW_MASTER:
						this.master = message.getSender();
						break;

					case DELETE_UUIDS:
						System.out.println("Delete UUIDS");
						break;
				}

			} catch (InterruptedException e) {
				System.err.println("Something went wrong when waiting!");
			}
		}
	}

	@Override
	protected void startElection(UUID uuid) {
		this.electionRunning = true;

		for (Map.Entry<Integer, Process> dest : this.destinations.entrySet()) {

			if(dest.getValue().getID() > this.getID()) {
				dest.getValue().receiveMessage(new Message(MessageType.ELECT,
					this.getID(), uuid));
			}
		}
		
		new Thread(new TimerThread(this, MessageType.ELECTION_TIMER, 2000, false)).start();

		System.err.printf("Started election. Process: %d, UUID: %s%n", this.getID() + 1, uuid);
	}

	private void checkMaster() {
		if (this.active) {
			if (this.master >= 0 && this.master != this.getID()) {
				this.checkMaster = true;
				this.destinations.get(this.master).receiveMessage(new Message(MessageType.R_U_ALIVE, this.getID(), null));
				new Thread(new TimerThread(this, MessageType.MASTER_TIMER, 2000, false)).start();
			} else if (this.master < 0) {
				this.startElection(UUID.randomUUID());
			}
		}
	}
}
