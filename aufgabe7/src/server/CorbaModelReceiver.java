package server;

import java.io.IOException;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import forum.framework.ForumModel;
import gen.AlreadyBoundException;
import gen.CorbaForumModel;
import gen.CorbaForumModelHelper;
import gen.CorbaForumModelPOA;
import gen.CorbaForumView;
import gen.NotBoundException;

public class CorbaModelReceiver extends CorbaForumModelPOA implements Runnable {
	
	private ForumModel model;
	private String[] args;
	
	public CorbaModelReceiver(String[] args) {
		this.args = args;
		this.model = ForumModel.INSTANCE;
	}

	@Override
	public void run() {

		try {
			
			ORB orb = ORB.init(this.args, null);

			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate(); 
	
			NamingContextExt nameService = NamingContextExtHelper.narrow(
					orb.resolve_initial_references("NameService"));
	
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(this);
			
			CorbaForumModel server = CorbaForumModelHelper.narrow(ref);

			nameService.rebind(nameService.to_name("ModelReceiver"), server);
			
			orb.run();

		} catch (NotFound | CannotProceed | InvalidName | org.omg.CORBA.ORBPackage.InvalidName
					| AdapterInactive | ServantNotActive | WrongPolicy e) {
			System.err.println("Model Receiver crashed!");
		}
		
	}

	@Override
	public void registerView(String name, CorbaForumView view)
			throws AlreadyBoundException {
		
		try {
			
			model.registerView(name, new CorbaViewForwarder(view));

		} catch (java.rmi.AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deregisterView(String name) throws NotBoundException {
		
		try {
			this.model.deregisterView(name);
		} catch (java.rmi.NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void moveNorth(String name) throws NotBoundException {
		
		try {
			this.model.moveNorth(name);
		} catch (java.rmi.NotBoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void moveEast(String name) throws NotBoundException {
		
		try {
			this.model.moveEast(name);
		} catch (java.rmi.NotBoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void moveSouth(String name) throws NotBoundException {
		
		try {
			this.model.moveSouth(name);
		} catch (java.rmi.NotBoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void moveWest(String name) throws NotBoundException {
		
		try {
			this.model.moveWest(name);
		} catch (java.rmi.NotBoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
