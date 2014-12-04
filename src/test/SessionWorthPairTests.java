package test;

import java.util.*;

import junit.framework.Assert;

import org.junit.Test;

import examSchedule.assignment.Session;
import examSchedule.assignment.SessionWorthPair;

public class SessionWorthPairTests
{
	private String sessionID1 = "SessionID1";
	private String sessionID2 = "SessionID2";
	private String sessionID3 = "SessionID3";
	private String sessionID4 = "SessionID4";
	private String sessionID5 = "SessionID5";
	private int worth1 = 6;
	private int worth2 = 2;
	private int worth3 = 3;
	private int worth4 = 6;
	private int worth5 = 5;

	@Test
	public void testOrder()
	{
		SessionWorthPair pair1 = new SessionWorthPair(new Session(sessionID1 ), worth1 );
		SessionWorthPair pair2 = new SessionWorthPair(new Session(sessionID2), worth2);
		SessionWorthPair pair3 = new SessionWorthPair(new Session(sessionID3), worth3);
		SessionWorthPair pair4 = new SessionWorthPair(new Session(sessionID4), worth4);
		SessionWorthPair pair5 = new SessionWorthPair(new Session(sessionID5), worth5);
		
		List<SessionWorthPair> list = new ArrayList<SessionWorthPair>();
		list.add(pair1);
		list.add(pair2);
		list.add(pair3);
		list.add(pair4);
		list.add(pair5);
		Collections.sort(list);
		int prevWorth = -1;
		for (SessionWorthPair pair : list)
		{
			System.out.println("Session ID: " + pair.getSession().getSessionID() + " worth: " + pair.getWorth());
			prevWorth = pair.getWorth();
		}
	}
}
