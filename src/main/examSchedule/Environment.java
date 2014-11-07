package main.examSchedule;

import java.util.ArrayList;
import java.util.List;

import main.examSchedule.assignment.SessionMap;
import main.examSchedule.assignment.StudentMap;
import main.examSchedule.course.CourseMap;
import main.examSchedule.date.Time;
import main.examSchedule.exceptions.UnexpectedPredicateArgumentsException;

/**
 * Class holds all the information necessary to calculate a solution.
 * 
 * @author achu
 * 
 */
public class Environment
{
	private CourseMap courseMap;
	private List<String> instructors;
	private SessionMap sessionMap;
	private StudentMap studentMap;

	public Environment()
	{
		courseMap = new CourseMap();
		sessionMap = new SessionMap();
		studentMap = new StudentMap();
		instructors = new ArrayList<String>();
	}
	
	private void argsLengthCheck(List<String> args, int expectedSize)
	{
		if(args.size() != expectedSize) throw new UnexpectedPredicateArgumentsException();
	}
	
	public void importPredicate(String predicateName, List<String> predicateList)
	{
		String courseName, lectureName, instructor, studentID, sessionID, dayID;
		double length;
		Time timeStart;
		switch (predicateName)
		{
		case ("course"):	//Technically not needed
			argsLengthCheck(predicateList, 1);
			courseName = predicateList.get(0);
			courseMap.addCourse(courseName);
			break;
			
		case ("lecture"):
			argsLengthCheck(predicateList, 2);
			courseName = predicateList.get(0);
			lectureName = predicateList.get(1);
			courseMap.addLecture(courseName, lectureName);
			break;
			
		case ("examLength"):
			argsLengthCheck(predicateList, 3);
			courseName = predicateList.get(0);
			lectureName = predicateList.get(1);
			length = Double.parseDouble(predicateList.get(2));
			courseMap.updateExamLength(courseName, lectureName, length);
			break;
			
		case ("instructor"):	//Technically not needed
			argsLengthCheck(predicateList, 1);
			instructor = predicateList.get(0);
			instructors.add(instructor);
			break;
			
		case ("student"):
			argsLengthCheck(predicateList, 1);
			studentID = predicateList.get(0);
			studentMap.addStudent(studentID);
			break;
	
		case ("session"):
			argsLengthCheck(predicateList, 1);
			sessionID = predicateList.get(0);
			sessionMap.addSession(sessionID);
			break;
		
		case ("enrolled"):
			argsLengthCheck(predicateList, 3);
			studentID = predicateList.get(0);
			courseName = predicateList.get(1);
			lectureName = predicateList.get(2);
			studentMap.enroll(studentID, courseMap.getCourseLecturePair(courseName, lectureName));	// Important to use the same object from courseMap to point to the same objects
			break;
		
		case ("at"):
			argsLengthCheck(predicateList, 4);
			sessionID = predicateList.get(0);
			dayID = predicateList.get(1);
			timeStart = new Time.Builder(predicateList.get(2)).build();
			length = Double.parseDouble(predicateList.get(3));
			sessionMap.updateSessionInfo(sessionID, dayID, timeStart, length);
			break;
		
		case ("capacity"):
			argsLengthCheck(predicateList, 2);
			break;
		
		case ("instructs"):
			argsLengthCheck(predicateList, 3);
			break;
		
		case ("day"):
			argsLengthCheck(predicateList, 1);
			break;
		
		case ("roomAssign"):
			argsLengthCheck(predicateList, 2);
			break;
		
		case ("room"):
			argsLengthCheck(predicateList, 1);
			break;

		}
	}

	public List<String> exportList()
	{
		return new ArrayList<String>();
	}
}
