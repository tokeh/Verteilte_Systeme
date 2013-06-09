package server;

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

import gen.AlreadyBoundException;
import gen.CorbaForumModel;
import gen.CorbaForumModelHelper;
import gen.CorbaForumModelPOA;
import gen.CorbaForumView;
import gen.NotBoundException;

public class CorbaModelReceiver extends CorbaForumModelPOA implements Runnable {
	
	private CorbaForumModel model;

	@Override
	public void run() {
		String[] args = {"-ORBInitialPort", "1050"};
		ORB orb = ORB.init(args, null);

		try {

			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate(); 
	
			NamingContextExt nameService = NamingContextExtHelper.narrow(
					orb.resolve_initial_references("NameService"));
	
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(this);
			
			CorbaForumModel server = CorbaForumModelHelper.narrow(ref);

			nameService.rebind(nameService.to_name("HelloServer"), server);

		} catch (NotFound | CannotProceed | InvalidName e) {
			System.err.println("Model Receiver crashed!");
		} catch (org.omg.CORBA.ORBPackage.InvalidName e) {
			System.err.println("Model Receiver crashed!");
		} catch (AdapterInactive e) {
			System.err.println("Model Receiver crashed!");
		} catch (ServantNotActive e) {
			System.err.println("Model Receiver crashed!");
		} catch (WrongPolicy e) {
			System.err.println("Model Receiver crashed!");
		}
		orb.run();
	}

	@Override
	public void registerView(String name, CorbaForumView view)
			throws AlreadyBoundException {
		model.registerView(name, view);
	}

	@Override
	public void deregisterView(String name) throws NotBoundException {
		this.model.deregisterView(name);
	}

	@Override
	public void moveNorth(String name) throws NotBoundException {
		this.model.moveNorth(name);
	}

	@Override
	public void moveEast(String name) throws NotBoundException {
		this.model.moveEast(name);
	}

	@Override
	public void moveSouth(String name) throws NotBoundException {
		this.model.moveSouth(name);
	}

	@Override
	public void moveWest(String name) throws NotBoundException {
		this.model.moveWest(name);
	}

}
