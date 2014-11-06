package main.examSchedule.assignment;

import static main.examSchedule.common.Utilities.*;
import main.examSchedule.exceptions.RoomCapacityUnassignedException;
import main.examSchedule.exceptions.RoomNegativeCapacityAssignmentException;

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
	
	/**
	 * Will throw exception if capacity is not set
	 * @return
	 * @throws RoomCapacityUnassignedException
	 */
	public Integer getCapacity() throws RoomCapacityUnassignedException
	{
		if (capacity == null) throw new RoomCapacityUnassignedException();
		return capacity;
	}
	
	public void setCapacity(int capacity)
	{
		if (capacity < 0) throw new RoomNegativeCapacityAssignmentException();
		this.capacity = capacity;
	}
}
