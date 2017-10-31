/**
 * 
 */
package comp6231.a2.campus.communication.corba;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import comp6231.a2.campus.CampusCommunication;
import comp6231.a2.common.corba.campus.CampusOperations;
import comp6231.a2.common.corba.campus.CampusOperationsHelper;
import comp6231.a2.common.corba.users.AdminOperations;
import comp6231.a2.common.corba.users.AdminOperationsHelper;
import comp6231.a2.common.corba.users.StudentOperations;
import comp6231.a2.common.corba.users.StudentOperationsHelper;

/**
 * @author saman
 *
 */
public class CorbaCampusCommunication extends CampusCommunication {

	/* (non-Javadoc)
	 * @see comp6231.a2.campus.CampusCommunication#getRemoteInfo(java.lang.String)
	 */
	@Override
	public RemoteInfo getRemoteInfo(String campus_name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see comp6231.a2.campus.CampusCommunication#getAllCampusNames()
	 */
	@Override
	public String[] getAllCampusNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see comp6231.a2.campus.CampusCommunication#startServer()
	 */
	@Override
	public void startServer(String[] args) {
        // create and initialize the ORB
        ORB orb = ORB.init(args, null);
        
        // get reference to rootpoa and activate the POAManager
        POA rootpoa;
		try {
			rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			AdminCampusServant admin_servant = new AdminCampusServant(campus);
			StudentCampusServant student_servant = new StudentCampusServant(campus);
			InterCampusServant intercampus_servant = new InterCampusServant(campus);
			
	        // get object reference from the servant
	        org.omg.CORBA.Object admin_ref = rootpoa.servant_to_reference(admin_servant);
	        AdminOperations admin = AdminOperationsHelper.narrow(admin_ref);
	        
	        org.omg.CORBA.Object student_ref = rootpoa.servant_to_reference(student_servant);
	        StudentOperations student = StudentOperationsHelper.narrow(student_ref);
	        
	        org.omg.CORBA.Object intercampus_ref = rootpoa.servant_to_reference(intercampus_servant);
	        CampusOperations intercampus = CampusOperationsHelper.narrow(intercampus_ref);
	        
	        // get the root naming context
	        org.omg.CORBA.Object objRef =
	            orb.resolve_initial_references("NameService");
	        // Use NamingContextExt which is part of the Interoperable
	        // Naming Service (INS) specification.
	        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	        
	        // bind the Object Reference in Naming
	        NameComponent path[] = ncRef.to_name(campus.getName() + "/admin");
	        ncRef.rebind(path, admin);
	        
	        path = ncRef.to_name(campus.getName() + "/student");
	        ncRef.rebind(path, student);
	        
	        path = ncRef.to_name(campus.getName() + "/intercampus");
	        ncRef.rebind(path, intercampus);	        
	        
	        orb.run();
		} catch (InvalidName | AdapterInactive | ServantNotActive | WrongPolicy | 
				org.omg.CosNaming.NamingContextPackage.InvalidName | NotFound | CannotProceed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
