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
//	
//	@Test(expected = SessionDuplicateAssignmentException.class)
//	public void duplicateAssignmentTest()
//	{
//		Lecture assign1 = new Lecture(courseName1, lectureName1);
//		Lecture assign2 = new Lecture(courseName1, lectureName1);
//		session.addSessionAssignment(assign1);
//		session.addSessionAssignment(assign2);
//		System.out.println("hi");
//	}

	@Test(expected = SessionRoomNotAssignedException.class)
	public void roomNotAssignedTest()
	{
		Integer expectedSize = 5;
		session.getRoom().setCapacity(expectedSize);
	}
	
	@Test
	public void roomSetSizeTest()
	{
		Integer expectedSize = 5;
		Room room = new Room(roomID );
		room.setCapacity(expectedSize);
		session.setRoom(room );
		Assert.assertEquals("Room size is not expected", expectedSize, session.getRoom().getCapacity());
		
	}
//	
//	@Test(expected = SessionAssignmentExceedsSizeException.class)
//	public void exceedsSizeTest()
//	{
//		int lectureSize1 = 5;
//		int lectureSize2 = 10;
//		int examLength1 = 2;
//		int examLength2 = 2;
//		int sessionLength = 2;
//		Integer expectedSize = lectureSize1 + lectureSize2 - 1;
//
//		Room room = new Room(roomID);
//		room.setCapacity(expectedSize);
//		session.setRoom(room);
//		session.setLength(sessionLength);
//		
//		Lecture assign1 = new Lecture(courseName1, lectureName1);
//		Lecture assign2 = new Lecture(courseName1, lectureName2);
//		
//		assign1.setClassSize(lectureSize1);
//		assign2.setClassSize(lectureSize2);
//		
//		assign1.setExamLength(examLength1);
//		assign2.setExamLength(examLength2);
//		
//		session.addSessionAssignment(assign1);
//		session.addSessionAssignment(assign2);
//	}
//
//	@Test(expected = SessionAssignmentExceedsTimeException.class)
//	public void exceedsTimeTest()
//	{
//		int lectureSize1 = 5;
//		int lectureSize2 = 10;
//		int examLength1 = 3;
//		int sessionLength = 2;
//		Integer expectedSize = lectureSize1 + lectureSize2;
//		
//		Room room = new Room(roomID);
//		room.setCapacity(expectedSize);
//		session.setRoom(room);
//		session.setLength(sessionLength);
//		
//		Lecture assign1 = new Lecture(courseName1, lectureName1);
//		
//		assign1.setClassSize(lectureSize1);
//		
//		assign1.setExamLength(examLength1);
//		
//		session.addSessionAssignment(assign1);
//	}
//	
//	@Test
//	public void validAdditionTest()
//	{
//		int lectureSize1 = 5;
//		int lectureSize2 = 10;
//		int examLength1 = 2;
//		int examLength2 = 2;
//		int sessionLength = 2;
//		Integer expectedSize = lectureSize1 + lectureSize2;
//		
//		Room room = new Room(roomID);
//		room.setCapacity(expectedSize);
//		session.setRoom(room);
//		session.setLength(sessionLength);
//		
//		Lecture assign1 = new Lecture(courseName1, lectureName1);
//		Lecture assign2 = new Lecture(courseName1, lectureName2);
//		
//		assign1.setClassSize(lectureSize1);
//		assign2.setClassSize(lectureSize2);
//		
//		assign1.setExamLength(examLength1);
//		assign2.setExamLength(examLength2);
//		
//		session.addSessionAssignment(assign1);
//		Assert.assertEquals("Wrong remaining size", lectureSize2, session.getRemainingCapacity());
//		session.addSessionAssignment(assign2);
//		Assert.assertEquals("Wrong remaining size", 0, session.getRemainingCapacity());
//		Assert.assertEquals("Did not expect this number of assignments", 2, session.getSessionAssignments().size());
//		Assert.assertEquals("Did not expect this assignment", lectureName1, session.getSessionAssignments().get(0).getLectureName());
//		Assert.assertEquals("Did not expect this assignment", courseName1, session.getSessionAssignments().get(0).getCourseName());
//		Assert.assertEquals("Did not expect this assignment", lectureName2, session.getSessionAssignments().get(1).getLectureName());
//		Assert.assertEquals("Did not expect this assignment", courseName1, session.getSessionAssignments().get(1).getCourseName());
//	}
	
	@Test(expected = NullParameterException.class)
	public void nullIDTest()
	{
		session = new Session(null);
	}
}
