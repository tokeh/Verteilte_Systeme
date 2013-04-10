package blatt4.aufgabe;

import java.util.UUID;

public class Message {
	public enum MessageType {
		ELECT, RESPONSE
	}

	private final MessageType type;
	private final int sender;
	private final UUID uuid;

	private final long time;

	public Message(MessageType type, int sender, UUID uuid) {
		this.type = type;
		this.sender = sender;
		this.uuid = uuid;
		this.time = System.currentTimeMillis();
	}

	public int getSender() {
		return sender;
	}

	public MessageType getType() {
		return type;
	}

	public UUID getUuid() {
		return uuid;
	}

	public long getTime() {
		return this.time;
	}
}