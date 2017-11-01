/**
 * 
 */
package comp6231.a2.campus;

import java.io.IOException;
import java.util.logging.Logger;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.BindingIterator;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;

import comp6231.a2.campus.communication.RmiCampusCommunication;
import comp6231.a2.campus.communication.corba.CorbaCampusCommunication;
import comp6231.a2.common.LoggerHelper;

/**
 * @author saman
 *
 */
public class CorbaBootstrap {
	
	public static boolean init = false;
	
	public static synchronized void initServers(String[] args) throws SecurityException, IOException {
		if (init)
			return;
		init = true;
		String[] campus_names = {"DVL", "KKL", "WST"};
		int[] ports = {7777, 7778, 7779};
		initServers(campus_names, ports, args);		
	}
	
	public static synchronized void initServers(String[] campus_names, int[] ports, String[] args) throws SecurityException, IOException {
		for (int i = 0; i < campus_names.length; ++i)
		{
			String campus_name = campus_names[i];
			int port = ports[i];
			Logger logger = LoggerHelper.getCampusServerLogger(campus_name);
			Campus campus = new Campus(campus_name, "127.0.0.1", port, logger, new CorbaCampusCommunication());
			//campus.starServer(args);
		}
	}

	/**
	 * 
	 */
	public CorbaBootstrap() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws InvalidName 
	 */
	public static void main(String[] args) throws SecurityException, IOException, InvalidName {
		initServers(args);
	        
		CorbaCampusCommunication.run();

	}

}
