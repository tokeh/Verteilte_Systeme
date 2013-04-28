package blatt4.aufgabe;

import blatt4.aufgabe.Message.MessageType;

public class TimerThread implements Runnable {

	private BullyProcess process;
	private MessageType type;
	private long sleep;
	boolean repeat;

	public TimerThread(BullyProcess proc, MessageType type, long sleep, boolean repeat) {
		this.process = proc;
		this.type = type;
		this.sleep = sleep;
		this.repeat = repeat;
	}

	@Override
	public void run() {

		while(this.repeat) {
			try {
				Thread.sleep(this.sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			this.process.receiveMessage(new Message(this.type, -1, null));
		}
	}

}
