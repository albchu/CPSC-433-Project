package examSchedule.assignment;

import java.util.*;

import examSchedule.course.Lecture;

public class Student
{
	private String studentID;
	private List<Lecture> enrolledLectures;
	
	public Student(String studentID)
	{
		this.studentID = studentID;
		enrolledLectures = new ArrayList<Lecture>();
	}
	
	public String getStudentID()
	{
		return studentID;
	}

	public void enroll(Lecture lecture)
	{
		lecture.incrementClassSize();
		enrolledLectures.add(lecture);
	}

	public List<Lecture> getEnrolledLectures()
	{
		return enrolledLectures;
	}
}
