package test;

import examSchedule.assignment.Room;
import examSchedule.assignment.Session;
import examSchedule.course.Lecture;
import examSchedule.exceptions.NullParameterException;
import examSchedule.exceptions.SessionAssignmentExceedsSizeException;
import examSchedule.exceptions.SessionAssignmentExceedsTimeException;
import examSchedule.exceptions.SessionDuplicateAssignmentException;
import examSchedule.exceptions.SessionRoomNotAssignedException;

import org.junit.*;

public class SessionTests
{
	private String sessionID = "aSessionID";
	private Session session;

	private String courseName1 = "course1";

	private String lectureName1 = "lecture1";
	private String lectureName2 = "lecture2";
	private String roomID = "roomID";
	
	@Before
	public void setup()
	{
		session = new Session(sessionID);
	}

	@Test(expected = SessionRoomNotAssignedException.class)
	public void roomNotAssignedTest()
	{
		Integer expectedSize = 5;
		session.getRoom().setCapacity(expectedSize);
	}
	
	@Test
	public void roomSetSizeTest()
	{
		int expectedSize = 5;
		Room room = new Room(roomID );
		room.setCapacity(expectedSize);
		session.setRoom(room );
		Assert.assertEquals("Room size is not expected", expectedSize, session.getRoom().getCapacity());
		
	}

	
	@Test(expected = NullParameterException.class)
	public void nullIDTest()
	{
		session = new Session(null);
	}
}
