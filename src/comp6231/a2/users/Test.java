package comp6231.a2.users;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.ArrayList;

import comp6231.a2.campus.Bootstrap;
import comp6231.a2.common.DateReservation;
import comp6231.a2.common.TimeSlot;
import comp6231.a2.common.TimeSlotResult;
import comp6231.a2.common.users.CampusUser;

public class Test {

	public static void main(String[] args) throws SecurityException, IOException, NotBoundException, InterruptedException {
		Bootstrap.initRmiServers();
		AdminClient admin1 = ClientUserFactory.createAdminClient(new CampusUser("DVLA1111"));
		DateReservation date = new DateReservation("17-09-2017");
		ArrayList<TimeSlot> time_slots = new ArrayList<>();
		time_slots.add(new TimeSlot("7:00 - 8:00"));
		time_slots.add(new TimeSlot("9:00 - 10:00"));
		time_slots.add(new TimeSlot("11:00 - 12:00"));
		time_slots.add(new TimeSlot("13:00 - 14:00"));
		time_slots.add(new TimeSlot("15:00 - 16:00"));
		time_slots.add(new TimeSlot("20:00 - 22:00"));
		admin1.createRoom(777, date, time_slots);
				
		
		StudentClient student1 = ClientUserFactory.createStudentClient(new CampusUser("DVLS0001"));
		StudentClient student2 = ClientUserFactory.createStudentClient(new CampusUser("DVLS0002"));
		
		
		ArrayList<TimeSlotResult> res = student1.getAvailableTimeSlot(date);
		for (TimeSlotResult r : res)
			System.out.println(r.getCampusName() + ": " + r.getTotalAvailableSlots());
		System.out.println(student1.bookRoom("DVL", 777, date, new TimeSlot("7:00 - 8:00")));
		System.out.println(student2.bookRoom("DVL", 777, date, new TimeSlot("7:00 - 8:00")));
		System.out.println(student1.bookRoom("DVL", 777, date, new TimeSlot("9:00 - 10:00")));
		System.out.println(student1.bookRoom("DVL", 777, date, new TimeSlot("11:00 - 12:00")));
	
		
		System.out.println(student1.bookRoom("DVL", 777, date, new TimeSlot("15:00 - 16:00")));
		
		res = student1.getAvailableTimeSlot(date);
		for (TimeSlotResult r : res)
			System.out.println(r.getCampusName() + ": " + r.getTotalAvailableSlots());
		System.out.println(student2.cancelBooking("DVL@17-9-2017@777#3"));
		System.out.println(student1.cancelBooking("DVL@17-9-2017@777#3"));
		System.out.println(student1.bookRoom("DVL", 777, date, new TimeSlot("15:00 - 16:00")));
		
		StudentClient student3 = ClientUserFactory.createStudentClient(new CampusUser("WSTS0001"));
		System.out.println(student3.bookRoom("DVL", 777, date, new TimeSlot("20:00 - 22:00")));
		
		res = student1.getAvailableTimeSlot(date);
		for (TimeSlotResult r : res)
			System.out.println(r.getCampusName() + ": " + r.getTotalAvailableSlots());
				
		
//		AdminClient admin2 = ClientUserFactory.createAdminClient(new CampusUser("KKLA1111"));
//		time_slots = new ArrayList<>();
//		time_slots.add(new TimeSlot("7:00 - 8:00"));
//		time_slots.add(new TimeSlot("9:00 - 10:00"));
//		time_slots.add(new TimeSlot("11:00 - 12:00"));
//		time_slots.add(new TimeSlot("13:00 - 14:00"));
//		time_slots.add(new TimeSlot("15:00 - 16:00"));
//		admin2.createRoom(778, date, time_slots);
//		
//		res = student1.getAvailableTimeSlot(date);
//		for (TimeSlotResult r : res)
//			System.out.println(r.getCampusName() + ": " + r.getTotalAvailableSlots());
//		
//		System.out.println(student1.cancelBooking("DVL@17-9-2017@777#4"));
//		System.out.println(student1.cancelBooking("DVL@17-9-2017@777#4"));
//		System.out.println(student1.bookRoom("KKL", 778, date, new TimeSlot("15:00 - 16:00")));
//		
//		res = student1.getAvailableTimeSlot(date);
//		for (TimeSlotResult r : res)
//			System.out.println(r.getCampusName() + ": " + r.getTotalAvailableSlots());
//		
//		System.out.println(student1.cancelBooking("KKL@17-9-2017@778#5"));
//		
//		res = student2.getAvailableTimeSlot(date);
//		for (TimeSlotResult r : res)
//			System.out.println(r.getCampusName() + ": " + r.getTotalAvailableSlots());
	}

}
