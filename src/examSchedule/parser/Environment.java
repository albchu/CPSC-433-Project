package examSchedule.parser;

import static examSchedule.common.Utilities.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import examSchedule.assignment.Assignment;
import examSchedule.assignment.AssignmentMap;
import examSchedule.assignment.Room;
import examSchedule.assignment.RoomMap;
import examSchedule.assignment.Session;
import examSchedule.assignment.SessionMap;
import examSchedule.assignment.StudentMap;
import examSchedule.course.CourseMap;
import examSchedule.course.Instructor;
import examSchedule.course.InstructorMap;
import examSchedule.course.Lecture;
import examSchedule.date.Time;
import examSchedule.exceptions.PredicateNotRecognizedException;
import examSchedule.exceptions.UnexpectedPredicateArgumentsException;
import examSchedule.solution.Constraints;

/**
 * Class holds all the information necessary to calculate a solution.
 * 
 * @author achu
 * 
 */
public class Environment
{
	private CourseMap courseMap;
	private InstructorMap instructorMap;
	private SessionMap sessionMap;
	private StudentMap studentMap;
	private RoomMap roomMap;
	private List<String> dayList;
	private AssignmentMap assignmentMap;

	public Environment()
	{
		courseMap = new CourseMap();
		sessionMap = new SessionMap();
		studentMap = new StudentMap();
		assignmentMap = new AssignmentMap();
		roomMap = new RoomMap();
		instructorMap = new InstructorMap();
		dayList = new ArrayList<String>();
	}
	
	private void argsLengthCheck(List<String> args, int expectedSize)
	{
		if(args.size() != expectedSize) throw new UnexpectedPredicateArgumentsException();
	}
	

	public void importPredicate(String predicateName, PredicateArgs predicateArgs)
	{
		String courseName, lectureName, studentID, sessionID, dayID, roomID, instructorID;
		int length;
		int capacity;
		Time timeStart;
		switch (predicateName)
		{
		case ("student"):
			//argsLengthCheck(predicateList, 1);
			studentID = predicateArgs.getNextString();
		studentMap.addStudent(studentID);
		break;
		
		case ("instructor"):	//Technically not needed
			//argsLengthCheck(predicateList, 1);
			instructorID = predicateArgs.getNextString();
		instructorMap.addInstructor(instructorID);
		break;
		
		case ("course"):	//Technically not needed
			//argsLengthCheck(predicateList, 1);
			courseName = predicateArgs.getNextString();
			courseMap.addCourse(courseName);
			break;
			
		case ("day"):
			//argsLengthCheck(predicateList, 1);
			dayID = predicateArgs.getNextString();
		dayList.add(dayID);
		break;
		
		case ("lecture"):
			//argsLengthCheck(predicateList, 2);
			courseName = predicateArgs.getNextString();
			lectureName = predicateArgs.getNextString();
			if(predicateArgs.size() == 2)
			{
				courseMap.addLecture(courseName, lectureName);
			}
			else if(predicateArgs.size() == 4)
			{
				instructorID = predicateArgs.getNextString();
				length = Integer.parseInt(predicateArgs.getNextString());
				courseMap.updateExamLength(courseName, lectureName, length);
			}
			else throw new PredicateNotRecognizedException("Did not anticipate argument input for '" + predicateName + "' " + predicateArgs);
			
			break;
			
		case ("session"):
			//argsLengthCheck(predicateList, 1);
			sessionID = predicateArgs.getNextString();
		if(predicateArgs.size() == 1)
		{
			sessionMap.addSession(sessionID);
		}
		else if(predicateArgs.size() == 5)
		{
			roomID = predicateArgs.getNextString();
			dayID = predicateArgs.getNextString();
			timeStart = new Time.Builder(predicateArgs.getNextString()).build();
			length = Integer.parseInt(predicateArgs.getNextString());
			if(!dayList.contains(dayID))
			{
				dayList.add(dayID);
//				System.out.println("added day: " + dayID);
			}
			sessionMap.updateSessionInfo(sessionID, dayID, timeStart, length);
		}
		else throw new PredicateNotRecognizedException("Did not anticipate argument input for '" + predicateName + "' " + predicateArgs);
		break;
			
		case ("room"):
			//argsLengthCheck(predicateList, 1);
			roomID = predicateArgs.getNextString();
		roomMap.addRoom(roomID);
		break;
		
		case ("capacity"):
			//argsLengthCheck(predicateList, 2);
			roomID = predicateArgs.getNextString();
		capacity = Integer.parseInt(predicateArgs.getNextString());
		roomMap.updateRoomInfo(roomID, capacity);
		break;
		
		case ("examlength"):
			//argsLengthCheck(predicateList, 3);
			courseName = predicateArgs.getNextString();
			lectureName = predicateArgs.getNextString();
			length = Integer.parseInt(predicateArgs.getNextString());
			courseMap.updateExamLength(courseName, lectureName, length);
			break;
		
		case ("enrolled"):
			//System.out.println("Enrolled: " + predicateName + " " + predicateList.toString());
			//argsLengthCheck(predicateList, 3);
			studentID = predicateArgs.getNextString();
			if(predicateArgs.size() == 3)
			{
				courseName = predicateArgs.getNextString();
				lectureName = predicateArgs.getNextString();
				if(!studentMap.contains(studentID)) //throw new ElementDoesNotExistException("The student id: '" + studentID + "' is not in the studentMap");
					studentMap.addStudent(studentID);
				studentMap.enroll(studentID, courseMap.getLecture(courseName, lectureName));	// Important to use the same object from courseMap to point to the same objects
			}
			else if(predicateArgs.size() == 2)
			{
				List<String> courseLecturePairs = predicateArgs.getNextList();
				if ((courseLecturePairs.size() % 2) != 0) throw new UnexpectedPredicateArgumentsException("Should not expect uneven input");
				for (int i = 0; i < courseLecturePairs.size(); i +=2)
				{
					courseName = courseLecturePairs.get(i);
					lectureName = courseLecturePairs.get(i+1);
					if(!studentMap.contains(studentID)) 
						studentMap.addStudent(studentID);
					studentMap.enroll(studentID, courseMap.getLecture(courseName, lectureName));	// Important to use the same object from courseMap to point to the same objects
				}
			}
			break;
		
		case ("at"):
			//argsLengthCheck(predicateList, 4);
			sessionID = predicateArgs.getNextString();
			dayID = predicateArgs.getNextString();
			timeStart = new Time.Builder(predicateArgs.getNextString()).build();
			length = Integer.parseInt(predicateArgs.getNextString());
			if(!dayList.contains(dayID))
			{
				dayList.add(dayID);
//				System.out.println("added day: " + dayID);
			}
			sessionMap.updateSessionInfo(sessionID, dayID, timeStart, length);
			break;
		
		case ("instructs"):
			//argsLengthCheck(predicateList, 3);
			instructorID = predicateArgs.getNextString();
			courseName = predicateArgs.getNextString();
			lectureName = predicateArgs.getNextString();
			courseMap.updateInstructor(courseName, lectureName, instructorMap.getInstructor(instructorID));
			break;
		
		case ("roomassign"):
			//argsLengthCheck(predicateList, 2);
			sessionID = predicateArgs.getNextString();
			roomID = predicateArgs.getNextString();
			sessionMap.updateSessionInfo(sessionID, roomMap.getRoom(roomID));
			break;
		
			
		case ("assign"):
			//argsLengthCheck(predicateList, 3);
			courseName = predicateArgs.getNextString();
			lectureName = predicateArgs.getNextString();
			sessionID = predicateArgs.getNextString();
			
			assignmentMap.addAssignment(sessionMap.getSession(sessionID), courseMap.getLecture(courseName, lectureName), 0, 0);
			/*
			int penalty = 0;
			List<Lecture> listOfCourseLectures = courseMap.getLectures(anAssignment.getLecture().getCourseName());
			penalty = Constraints.calcAllSoftCon(anAssignment.getSession(), anAssignment.getLecture());
			penalty += Constraints.calcSoftThree(listOfCourseLectures, anAssignment.getSession(), anAssignment.getLecture());
			anAssignment.setPenalty(penalty);*/
			
			break;
			
		case ("dayassign"):
			//argsLengthCheck(predicateList, 3);
		sessionID = predicateArgs.getNextString();
		dayID = predicateArgs.getNextString();
		sessionMap.updateSessionInfo(sessionID, dayID);
		break;
		
		case ("time"):
			//argsLengthCheck(predicateList, 3);
			sessionID = predicateArgs.getNextString();
		timeStart = new Time.Builder(predicateArgs.getNextString()).build();
		sessionMap.updateSessionInfo(sessionID, timeStart);
		break;
		
		case ("length"):
			//argsLengthCheck(predicateList, 3);
			sessionID = predicateArgs.getNextString();
		length = Integer.parseInt(predicateArgs.getNextString());
		sessionMap.updateSessionInfo(sessionID, length);
		break;
			
		default:
			throw new PredicateNotRecognizedException("Could not recognize predicate: '" + predicateName + "'");
		}
	}
	
	
	public List<String> exportList()
	{
		List<String> output = new ArrayList<String>();
		Set<String> courses = courseMap.getCourseMap().keySet();
		for(String course : courses)
		{
			// course
			output.add(predicateForm("course", course));

			for(Lecture lecture : courseMap.getLectures(course))
			{
				// lecture
				output.add(predicateForm("lecture", course, lecture.getLectureName()));
				
				// examLength
				output.add(predicateForm("examlength", course, lecture.getLectureName(), Integer.toString(lecture.getExamLength())));
				
			}
		}
		
		for(Instructor instructor : instructorMap.getInstructors())
		{
			for(Lecture lecture : instructor.getInstructedLectures())
				// instructs
				output.add(predicateForm("instructs", instructor.getInstructorID(), lecture.getCourseName(), lecture.getLectureName()));
			
		}
		
		for (Instructor instructor : instructorMap.getInstructors())
		{
			// instructor
			output.add(predicateForm("instructor", instructor.getInstructorID()));
		}
		
		for (String studentID : studentMap.getStudentMap().keySet())
		{
			// student
			output.add(predicateForm("student", studentID));
			
			for(Lecture lecture : studentMap.getEnrollments(studentID))
				// enrolled
				output.add(predicateForm("enrolled", studentID, lecture.getCourseName(), lecture.getLectureName()));
		}
		
		for (String sessionID : sessionMap.getSessionMap().keySet())
		{
			// session
			output.add(predicateForm("session", sessionID));
			
			Session session = sessionMap.getSession(sessionID);

			// at
//			System.out.println(sessionID);
//			System.out.println(session.getDay());
//			System.out.println(session.getTime().toString());
//			System.out.println(session.getLength());
			output.add(predicateForm("at", sessionID, session.getDay(), session.getTime().toString(), Integer.toString(session.getLength())));

			// dayassign
			output.add(predicateForm("dayassign", sessionID, session.getDay()));
			
			// time
			output.add(predicateForm("time", sessionID, session.getTime().toString()));
			
			//length
			output.add(predicateForm("length", sessionID, Integer.toString(session.getLength())));
			
			
			// roomAssign
			output.add(predicateForm("roomAssign", sessionID, session.getRoom().getRoomID()));
			
		}
		for(Assignment assignment : assignmentMap.getAssignments())
			// assign
			output.add(predicateForm("assign", assignment.getLecture().getCourseName(), assignment.getLecture().getLectureName(), assignment.getSession().getSessionID()));
		
		
		for (String roomID : roomMap.getRoomMap().keySet())
		{
			Room room = roomMap.getRoom(roomID);
			// room
			output.add(predicateForm("room", roomID));

			// capacity
			output.add(predicateForm("capacity", roomID, Integer.toString(room.getCapacity())));
		}
		
		for (String day : dayList)
			// day
			output.add(predicateForm("day", day));
		
		
		
		
		Collections.sort(output);
		return output;
	}

	public AssignmentMap getAssignmentMap()
	{
		return assignmentMap;
	}

	public CourseMap getCourseMap()
	{
		return courseMap;
	}

	public InstructorMap getInstructorMap()
	{
		return instructorMap;
	}

	public SessionMap getSessionMap()
	{
		return sessionMap;
	}

	public StudentMap getStudentMap()
	{
		return studentMap;
	}

	public RoomMap getRoomMap()
	{
		return roomMap;
	}

	public List<String> getDayList()
	{
		return dayList;
	}
}
