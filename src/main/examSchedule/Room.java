package main.examSchedule;

import static main.examSchedule.common.Utilities.*;

public class Room
{
	private String roomID;
	private Integer capacity;

	public Room(String roomID)
	{
		nullCheck(roomID);
		this.roomID = roomID;
		capacity = null;
	}
	
	public String getRoomID()
	{
		return roomID;
	}
	
	public Integer getCapacity()
	{
		return capacity;
	}
	
	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}
}
