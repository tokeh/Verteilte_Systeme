package blatt2.aufgabe1;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class StopThread implements Runnable {

	private ThreadPooledPrimeServer server;

	public StopThread(ThreadPooledPrimeServer ser) {
		this.server = ser;
	}

	public void run() {
		JFrame messageFrame = new JFrame();
		JOptionPane.showMessageDialog(messageFrame, "Press the button to stop the server...do it!...do it!",
				"Stop Server", JOptionPane.OK_OPTION);

		server.setDone(true);
		messageFrame.dispose();
	}
}
