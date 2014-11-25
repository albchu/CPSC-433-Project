package examSchedule.assignment;

import static examSchedule.common.Utilities.*;

import java.util.ArrayList;
import java.util.List;

import examSchedule.course.Lecture;
import examSchedule.date.Time;
import examSchedule.exceptions.ElementDoesNotExistException;
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
	private List<Lecture> assignedLectures;
	
	public Session(String sessionID)
	{
		nullCheck(sessionID);
		this.sessionID = sessionID;
		assignedLectures = new ArrayList<Lecture>();
		System.out.println("Creating Session");

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
	
	/**
	 * Adds a lecture to the session and decrements the remaining capacity accordingly
	 * @param lecture
	 */
	public void addLecture(Lecture lecture)
	{
		assignedLectures.add(lecture);
		this.decrementRemainingCapacity(lecture.getClassSize());
	}
	
	public List<Lecture> getAssignedLectures() {
		return assignedLectures;
	}

	/**
	 * Removes a lecture from the assignments and reincrements the remaining capacity accordingly
	 * @param lecture
	 */
	public void removeLecture(Lecture lecture)
	{
		if(!assignedLectures.remove(lecture)) throw new ElementDoesNotExistException("The lecture " + lecture + " could not be removed from the session " + this + " because it never existed");
		this.incrementRemainingCapacity(lecture.getClassSize());
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
		System.out.println("adding new room");
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
