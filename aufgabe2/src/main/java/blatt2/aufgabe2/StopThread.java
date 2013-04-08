package blatt2.aufgabe2;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import javax.swing.JOptionPane;

import blatt1.ClientEndpoint;

public class StopThread implements Runnable {

	public void run() {
		JOptionPane.showMessageDialog(null, "Press OK to stop the server ...do it! ...do it!",
				"Stop Server", JOptionPane.INFORMATION_MESSAGE);

		ClientEndpoint endpoint = new ClientEndpoint();
		SocketAddress server = new InetSocketAddress("localhost", 4711);

		endpoint.send(server, -1);
	}
}
