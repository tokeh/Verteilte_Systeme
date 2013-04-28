package blatt4.aufgabe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import blatt4.aufgabe.Message.MessageType;

public class BullyProcess extends Process {

	public static final int TWENTY_SECONDS = 20000;
	
	private Map<UUID, Long> joinedElections;
	private boolean electionRunning;
	private boolean checkMaster;

	private int master;

	public BullyProcess(int id) {
		super(id);
		this.master = -1;
		this.electionRunning = false;
		this.checkMaster = false;
		this.joinedElections = new HashMap<UUID, Long>();
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
								new Message(MessageType.RESPONSE, this.getID(),
										message.getUuid(), System.currentTimeMillis()));
	
							if (!this.joinedElections.containsKey(message.getUuid())) {
								this.joinedElections.put(message.getUuid(), System.currentTimeMillis());
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
										this.getID(), null, System.currentTimeMillis()));
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
						this.destinations.get(message.getSender()).receiveMessage(
								new Message(MessageType.STILL_ALIVE, this.getID(),
										null, System.currentTimeMillis()));
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
						this.deleteUUIDS(message.getTime());
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
					this.getID(), uuid, System.currentTimeMillis()));
			}
		}
		
		new Thread(new TimerThread(this, MessageType.ELECTION_TIMER, 2000, false)).start();

		System.err.printf("Started election. Process: %d, UUID: %s%n", this.getID() + 1, uuid);
	}

	private void checkMaster() {

		if (this.active) {

			if (this.master >= 0 && this.master != this.getID()) {

				this.checkMaster = true;
				this.destinations.get(this.master).receiveMessage(
						new Message(MessageType.R_U_ALIVE, this.getID(), null, System.currentTimeMillis()));
				new Thread(new TimerThread(this, MessageType.MASTER_TIMER, 2000, false)).start();

			} else if (this.master != this.getID()){
				this.startElection(UUID.randomUUID());
			}
		}
	}

	private void deleteUUIDS(long time) {

		for (Map.Entry<UUID, Long> uuid : this.joinedElections.entrySet()) {

			if (uuid.getValue() < (time - TWENTY_SECONDS)) {
				this.joinedElections.remove(uuid);
				System.out.println("Deleted UUID: " + uuid.getKey());
				System.out.println("Message Time: " + time + " UUID Time: " + uuid.getValue());
				System.out.println("DIFF: " + (time - uuid.getValue()));
			}
		}
	}
}
