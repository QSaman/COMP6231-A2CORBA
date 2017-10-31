package comp6231.a2.campus;

import java.util.ArrayList;

public abstract class CampusCommunication {
	protected String campus_name;
	
	class RemoteInfo
	{
		public int port;
		public String address;
	}
	
	public CampusCommunication(String campus_name)
	{
		this.campus_name = campus_name;
	}
	
	public abstract RemoteInfo getRemoteInfo(String campus_name);
	public abstract String[] getAllCampusNames();
	public abstract void startServer(Campus campus);
}
