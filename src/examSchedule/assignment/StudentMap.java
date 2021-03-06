package examSchedule.assignment;

import java.util.HashMap;
import java.util.List;

import examSchedule.course.Lecture;
import examSchedule.exceptions.DuplicateSessionException;
import examSchedule.exceptions.ElementDoesNotExistException;

public class StudentMap
{
	private HashMap<String, Student> studentMap;
	
	public StudentMap()
	{
		studentMap = new HashMap<String, Student>();
	}
	
	public Student getStudent(String studentID)
	{
		if(!studentMap.containsKey(studentID)) throw new ElementDoesNotExistException("Could not find student in student map");
		return studentMap.get(studentID);
	}
	
	public boolean contains(String studentID)
	{
		return studentMap.containsKey(studentID);
	}
	
	public void addStudent(String studentID)
	{
		if(studentMap.containsKey(studentID)) throw new DuplicateSessionException("student has already been added in student map");
		studentMap.put(studentID, new Student(studentID));
	}
	
	public void enroll(String studentID, Lecture lecture)
	{
		getStudent(studentID).enroll(lecture);
	}

	public List<Lecture> getEnrollments(String studentID)
	{
		return getStudent(studentID).getEnrolledLectures();
	}
	
	public HashMap<String, Student> getStudentMap()
	{
		return studentMap;
	}
}
