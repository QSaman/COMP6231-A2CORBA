/**
 * 
 */
package comp6231.a2.users;

import java.util.ArrayList;

import comp6231.a2.common.DateReservation;
import comp6231.a2.common.TimeSlot;
import comp6231.a2.common.TimeSlotResult;
import comp6231.a2.common.corba.users.StudentOperations;

/**
 * @author saman
 *
 */
public class StudentCorba implements StudentInterface {
	StudentOperations remote_stub;

	/**
	 * 
	 */
	public StudentCorba(StudentOperations remote_stub) {
		this.remote_stub = remote_stub;
	}

	/* (non-Javadoc)
	 * @see comp6231.a2.users.StudentInterface#bookRoom(java.lang.String, java.lang.String, int, comp6231.a2.common.DateReservation, comp6231.a2.common.TimeSlot)
	 */
	@Override
	public String bookRoom(String user_id, String campus_name, int room_number, DateReservation date,
			TimeSlot time_slot) {
		return remote_stub.bookRoom(user_id, campus_name, room_number, date.toCorba(), time_slot.toCorba());
	}

	/* (non-Javadoc)
	 * @see comp6231.a2.users.StudentInterface#getAvailableTimeSlot(comp6231.a2.common.DateReservation)
	 */
	@Override
	public ArrayList<TimeSlotResult> getAvailableTimeSlot(DateReservation date) {
		return TimeSlotResult.toArrayList(remote_stub.getAvailableTimeSlot(date.toCorba()));
	}

	/* (non-Javadoc)
	 * @see comp6231.a2.users.StudentInterface#cancelBooking(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean cancelBooking(String user_id, String bookingID) {
		return remote_stub.cancelBooking(user_id, bookingID);
	}

}
