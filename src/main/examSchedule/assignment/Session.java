package main.examSchedule.assignment;

import static main.examSchedule.common.Utilities.*;

import java.util.ArrayList;
import java.util.List;

import main.examSchedule.course.CourseLecturePair;
import main.examSchedule.date.Day;
import main.examSchedule.date.Time;
import main.examSchedule.exceptions.SessionAssignmentExceedsSizeException;
import main.examSchedule.exceptions.SessionAssignmentExceedsTimeException;
import main.examSchedule.exceptions.SessionDuplicateAssignmentException;
import main.examSchedule.exceptions.SessionRoomNotAssignedException;

public class Session
{
	private String sessionID;
	private Room room;
	private Day day;
	private Time timeStart;
	private double length;
	private int remainingCapacity;
	
	private List<CourseLecturePair> sessionAssignments;
	
	public Session(String sessionID)
	{
		nullCheck(sessionID);
		this.sessionID = sessionID;
		sessionAssignments = new ArrayList<CourseLecturePair>();
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
	
	public double getLength()
	{
		return length;
	}
	
	public void setLength(double length)
	{
		this.length = length;
	}

	public Day getDay()
	{
		return day;
	}

	public List<CourseLecturePair> getSessionAssignments()
	{
		return sessionAssignments;
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

	public void setDay(Day day)
	{
		this.day = day;
	}

	public Time getTimeStart()
	{
		return timeStart;
	}

	public void setTimeStart(Time timeStart)
	{
		this.timeStart = timeStart;
	}

	/**
	 * Adds a courseLecturePair to the session's list of assignments if it obeys constraints
	 * @param sessionAssignment
	 */
	public void addSessionAssignment(CourseLecturePair sessionAssignment)
	{
		
		if(sessionAssignments.contains(sessionAssignment)) throw new SessionDuplicateAssignmentException();
		if(remainingCapacity < sessionAssignment.getLecture().getClassSize()) throw new SessionAssignmentExceedsSizeException();
		if(sessionAssignment.getLecture().getExamLength() > length) throw new SessionAssignmentExceedsTimeException();
		remainingCapacity -= sessionAssignment.getLecture().getClassSize();
		sessionAssignments.add(sessionAssignment);
		
	}
}
