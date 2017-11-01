package comp6231.a2.users;

import java.util.ArrayList;

import comp6231.a2.common.DateReservation;
import comp6231.a2.common.TimeSlot;
import comp6231.a2.common.TimeSlotResult;

public interface StudentInterface {
	public String bookRoom(String user_id, String campus_name, int room_number, DateReservation date, TimeSlot time_slot);
	public ArrayList<TimeSlotResult> getAvailableTimeSlot(DateReservation date);
	public boolean cancelBooking(String user_id, String bookingID);

}
