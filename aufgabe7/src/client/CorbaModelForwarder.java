package client;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import server.CorbaModelReceiver;

import forum.framework.IForumModel;
import forum.framework.IForumView;
import gen.CorbaForumModel;
import gen.CorbaForumModelHelper;
import gen.CorbaForumView;

public class CorbaModelForwarder implements IForumModel {
	
	private CorbaForumModel receiver;

	public CorbaModelForwarder() {

		String[] args = {"-ORBInitialPort", "1050"};
		ORB orb = ORB.init(args, null);

		try {

			NamingContextExt nameService = NamingContextExtHelper.narrow(
					orb.resolve_initial_references("NameService"));

			this.receiver = CorbaForumModelHelper.narrow(
				nameService.resolve_str("HelloServer"));

		} catch (InvalidName e) {
			System.err.println("Model Forwarder crashed!");
		} catch (NotFound e) {
			System.err.println("Model Forwarder crashed!");
		} catch (CannotProceed e) {
			System.err.println("Model Forwarder crashed!");
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			System.err.println("Model Forwarder crashed!");
		}
	}

	@Override
	public void deregisterView(String name) throws NotBoundException,
			IOException {
		try {
			this.receiver.deregisterView(name);
		} catch (gen.NotBoundException e) {
			System.err.println("deregisterView crashed!");
		}
	}

	@Override
	public void moveEast(String name) throws NotBoundException, IOException {
		try {
			this.receiver.moveEast(name);
		} catch (gen.NotBoundException e) {
			System.err.println("moveEast crashed!");
		}
	}

	@Override
	public void moveNorth(String name) throws NotBoundException, IOException {
		try {
			this.receiver.moveNorth(name);
		} catch (gen.NotBoundException e) {
			System.err.println("moveNorth crashed!");
		}
	}

	@Override
	public void moveSouth(String name) throws NotBoundException, IOException {
		try {
			this.receiver.moveSouth(name);
		} catch (gen.NotBoundException e) {
			System.err.println("moveSouth crashed!");
		}
	}

	@Override
	public void moveWest(String name) throws NotBoundException, IOException {
		try {
			this.receiver.moveWest(name);
		} catch (gen.NotBoundException e) {
			System.err.println("moveWest crashed!");
		}
	}

	@Override
	public void registerView(String name, IForumView view)
			throws AlreadyBoundException, IOException {
		try {
			this.receiver.registerView(name, (CorbaForumView) view);
		} catch (gen.AlreadyBoundException e) {
			System.err.println("registerView crashed!");
		}
	}

}
