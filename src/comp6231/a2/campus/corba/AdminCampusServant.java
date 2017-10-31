/**
 * 
 */
package comp6231.a2.campus.corba;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import comp6231.a2.common.DateReservation;
import comp6231.a2.common.TimeSlot;
import comp6231.a2.common.corba.data_structure.CorbaDateReservation;
import comp6231.a2.common.corba.data_structure.CorbaTimeSlot;
import comp6231.a2.common.corba.users.AdminOperationsPOA;
import comp6231.a2.common.users.AdminOperations;

/**
 * @author saman
 *
 */
public class AdminCampusServant extends AdminOperationsPOA {
	
	AdminOperations admin_operations;
	
	public AdminCampusServant(AdminOperations admin_operations)
	{
		this.admin_operations = admin_operations;
	}

	/* (non-Javadoc)
	 * @see comp6231.a2.common.corba.users.AdminOperationsOperations#createRoom(java.lang.String, int, comp6231.a2.common.corba.data_structure.CorbaDateReservation, comp6231.a2.common.corba.data_structure.CorbaTimeSlot[])
	 */
	@Override
	public boolean createRoom(String user_id, int room_number, CorbaDateReservation date, CorbaTimeSlot[] time_slots) {
		ArrayList<TimeSlot> ts = new ArrayList<TimeSlot>();
		for (CorbaTimeSlot cts : time_slots)
			ts.add(new TimeSlot(cts));
		try {
			return admin_operations.createRoom(user_id, room_number, new DateReservation(date), ts);
		} catch (RemoteException e) {
			// Since it's local, this exception never happens
			e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see comp6231.a2.common.corba.users.AdminOperationsOperations#deleteRoom(java.lang.String, int, comp6231.a2.common.corba.data_structure.CorbaDateReservation, comp6231.a2.common.corba.data_structure.CorbaTimeSlot[])
	 */
	@Override
	public boolean deleteRoom(String user_id, int room_number, CorbaDateReservation date, CorbaTimeSlot[] time_slots) {
		ArrayList<TimeSlot> ts = new ArrayList<TimeSlot>();
		for (CorbaTimeSlot cts : time_slots)
			ts.add(new TimeSlot(cts));
		try {
			admin_operations.deleteRoom(user_id, room_number, new DateReservation(date), ts);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see comp6231.a2.common.corba.users.AdminOperationsOperations#startWeek(java.lang.String)
	 */
	@Override
	public boolean startWeek(String user_id) {
		try {
			return admin_operations.startWeek(user_id);
		} catch (NotBoundException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
