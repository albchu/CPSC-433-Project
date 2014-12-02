package examSchedule.assignment;

import static examSchedule.common.Utilities.*;
import examSchedule.exceptions.RoomCapacityUnassignedException;
import examSchedule.exceptions.RoomNegativeCapacityAssignmentException;

public class Room
{
	private String roomID;
	private int capacity;
	private Session session;

	public Room(String roomID)
	{
		nullCheck(roomID);
		this.roomID = roomID;
		capacity = 0;
		session = null;
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
	public int getCapacity() throws RoomCapacityUnassignedException
	{
		return capacity;
	}
	
	public Session getSession()
	{
		return session;
	}

	public void setSession(Session session)
	{
		this.session = session;
	}

	public void setCapacity(int capacity)
	{
		if (capacity < 0) throw new RoomNegativeCapacityAssignmentException();
		if (session != null) session.setRemainingCapacity(capacity);
		this.capacity = capacity;
	}
}
