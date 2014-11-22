package examSchedule.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import examSchedule.course.Lecture;
import examSchedule.date.Time;
import examSchedule.exceptions.DuplicateSessionException;


public class SessionMap
{
	private HashMap<String, Session> sessionMap;
	
	public SessionMap()
	{
		sessionMap = new HashMap<String, Session>();
	}
	
	public Session getSession(String sessionID)
	{
		if(!sessionMap.containsKey(sessionID))
			addSession(sessionID);
		return sessionMap.get(sessionID);
	}
	
	public void updateSessionInfo(String sessionID, String dayID, Time time, int length)
	{
		Session session = getSession(sessionID);
		session.setDay(dayID);
		session.setLength(length);
		session.setTime(time);
	}
	
	public void updateSessionInfo(String sessionID, int length)
	{
		Session session = getSession(sessionID);
		session.setLength(length);
	}
	
	public void updateSessionInfo(String sessionID, String dayID)
	{
		Session session = getSession(sessionID);
		session.setDay(dayID);
	}
	
	public void updateSessionInfo(String sessionID, Time time)
	{
		Session session = getSession(sessionID);
		session.setTime(time);
	}
	
	public void updateSessionInfo(String sessionID, Room room)
	{
		Session session = getSession(sessionID);
		session.setRoom(room);
	}
//	public void updateSessionInfo(String sessionID, Lecture lecture)
//	{
//		Session session = getSession(sessionID);
//		session.addSessionAssignment(lecture);;
//	}
	
	public HashMap<String, Session> getSessionMap()
	{
		return sessionMap;
	}

	public void addSession(String sessionID)
	{
		//if(sessionMap.containsKey(sessionID)) throw new DuplicateSessionException("Session has already been added in session map");
		sessionMap.put(sessionID, new Session(sessionID));
	}
	
	/** 
	 * Returns all session in one massive list
	 * @return
	 */
	public List<Session> getAllSessions()
	{
		List<Session> sessions = new ArrayList<Session>();
		for(String sessionID : sessionMap.keySet())
			sessions.add(this.getSession(sessionID));
		return sessions;
	}
	
	/**
	 * Returns a set of all course keys in the map
	 * @return
	 */
	public Set<String> getSession()
	{
		return sessionMap.keySet();
	}
}
