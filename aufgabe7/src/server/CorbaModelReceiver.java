package server;

import gen.AlreadyBoundException;
import gen.CorbaForumModelPOA;
import gen.CorbaForumView;
import gen.NotBoundException;

public class CorbaModelReceiver extends CorbaForumModelPOA implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerView(String name, CorbaForumView view)
			throws AlreadyBoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deregisterView(String name) throws NotBoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveNorth(String name) throws NotBoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveEast(String name) throws NotBoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveSouth(String name) throws NotBoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveWest(String name) throws NotBoundException {
		// TODO Auto-generated method stub
		
	}

}
