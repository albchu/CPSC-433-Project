package examSchedule.assignment;

import java.util.HashMap;

import examSchedule.course.CourseLecturePair;
import examSchedule.date.Time;
import examSchedule.exceptions.DuplicateSessionException;
import examSchedule.exceptions.ElementDoesNotExistException;


public class SessionMap
{
	private HashMap<String, Session> sessionMap;
	
	public SessionMap()
	{
		sessionMap = new HashMap<String, Session>();
	}
	
	public Session getSession(String sessionID)
	{
		if(!sessionMap.containsKey(sessionID)) throw new ElementDoesNotExistException("Could not find session in session map");
		return sessionMap.get(sessionID);
	}
	
	public void updateSessionInfo(String sessionID, String dayID, Time time, int length)
	{
		Session session = getSession(sessionID);
		session.setDay(dayID);
		session.setLength(length);
		session.setTime(time);
	}
	
	public void updateSessionInfo(String sessionID, Room room)
	{
		Session session = getSession(sessionID);
		session.setRoom(room);
	}
	public void updateSessionInfo(String sessionID, CourseLecturePair courseLecturePair)
	{
		Session session = getSession(sessionID);
		session.addSessionAssignment(courseLecturePair);;
	}
	
	public HashMap<String, Session> getSessionMap()
	{
		return sessionMap;
	}

	public void addSession(String sessionID)
	{
		if(sessionMap.containsKey(sessionID)) throw new DuplicateSessionException("Session has already been added in session map");
		sessionMap.put(sessionID, new Session(sessionID));
	}
}
