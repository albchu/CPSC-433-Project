package examSchedule.assignment;

import static examSchedule.common.Utilities.*;

import java.util.ArrayList;
import java.util.List;

import examSchedule.course.Lecture;
import examSchedule.date.Time;
import examSchedule.exceptions.SessionAssignmentExceedsSizeException;
import examSchedule.exceptions.SessionAssignmentExceedsTimeException;
import examSchedule.exceptions.SessionDuplicateAssignmentException;
import examSchedule.exceptions.SessionRoomNotAssignedException;

public class Session
{
	private String sessionID;
	private Room room;
	private String dayID;
	private Time time;
	private int length;
	private int remainingCapacity;
	
	public Session(String sessionID)
	{
		nullCheck(sessionID);
		this.sessionID = sessionID;
	}

	public String getSessionID()
	{
		return sessionID;
	}

	public Room getRoom()
	{
		if (room == null) throw new SessionRoomNotAssignedException();
		return room;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public void setLength(int length)
	{
		this.length = length;
	}

	public String getDay()
	{
		return dayID;
	}

	/**
	 * Sets the session room
	 * Note: capacity for the room object must be set prior to setRoom() being called
	 * @param room
	 */
	public void setRoom(Room room)
	{
		this.room = room;
		remainingCapacity = room.getCapacity();
	}

	public int getRemainingCapacity()
	{
		return remainingCapacity;
	}

	public void setDay(String dayID)
	{
		this.dayID = dayID;
	}

	public Time getTime()
	{
		return time;
	}

	public void setTime(Time time)
	{
		this.time = time;
	}
	
	public void incrementRemainingCapacity(int n)
	{
		remainingCapacity += n;
	}
	
	public void decrementRemainingCapacity(int n)
	{
		remainingCapacity -= n;
	}
}
