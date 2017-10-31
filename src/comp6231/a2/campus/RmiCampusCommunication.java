package comp6231.a2.campus;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RmiCampusCommunication extends CampusCommunication {
	
	private Registry registry;
	CampusOperations campus_operations;
	
	public RmiCampusCommunication(String campus_name, Registry registry) {
		super(campus_name);
		this.registry = registry;		
	}

	@Override
	public RemoteInfo getRemoteInfo(String campus_name) {		
		RemoteInfo info = new RemoteInfo();
		try {
			campus_operations = (CampusOperations)registry.lookup(campus_name);
			info.address = campus_operations.getAddress();
			info.port = campus_operations.getPort();
			return info;
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
		
	}

	@Override
	public String[] getAllCampusNames() {
		try {
			return registry.list();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void startServer(Campus campus) {
		System.setProperty("java.security.policy", "file:./src/comp6231/security.policy");
//		System.setProperty("java.rmi.server.codebase", "file:///media/NixHddData/MyStuff/Programming/Projects/Java/workspace/A1RMI/bin/");
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());
		
		Remote stub = null;
		try {
			stub = UnicastRemoteObject.exportObject(campus, 0);
			registry.rebind(campus_name, stub);			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
