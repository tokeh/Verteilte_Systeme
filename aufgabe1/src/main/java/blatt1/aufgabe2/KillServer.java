package blatt1.aufgabe2;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import blatt1.ClientEndpoint;

public class KillServer {
	    
	public static void main(String[] args) {
		ClientEndpoint endpoint = new ClientEndpoint();
		SocketAddress server = new InetSocketAddress("localhost", 4711);
		
		endpoint.send(server, -1);
		endpoint.send(server, -1);
	}    
}
