package client;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

import forum.framework.IForumModel;
import forum.framework.IForumView;

public class CorbaModelForwarder implements IForumModel {

	@Override
	public void deregisterView(String arg0) throws NotBoundException,
			IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveEast(String arg0) throws NotBoundException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveNorth(String arg0) throws NotBoundException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveSouth(String arg0) throws NotBoundException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveWest(String arg0) throws NotBoundException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerView(String arg0, IForumView arg1)
			throws AlreadyBoundException, IOException {
		// TODO Auto-generated method stub

	}

}
