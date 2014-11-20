package examSchedule.assignment;

import java.util.*;

import examSchedule.course.CourseLecturePair;

public class Student
{
	private String studentID;
	private List<CourseLecturePair> enrolledLecturePairs;
	
	public Student(String studentID)
	{
		this.studentID = studentID;
		enrolledLecturePairs = new ArrayList<CourseLecturePair>();
	}
	
	public String getStudentID()
	{
		return studentID;
	}

	public void enroll(CourseLecturePair courseLecturePair)
	{
		courseLecturePair.getLecture().incrementClassSize();
		enrolledLecturePairs.add(courseLecturePair);
	}

	public List<CourseLecturePair> getEnrolledLecturePairs()
	{
		return enrolledLecturePairs;
	}
}
