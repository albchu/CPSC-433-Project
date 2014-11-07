package main.examSchedule.assignment;

import java.util.*;

import main.examSchedule.course.CourseLecturePair;

public class Student
{
	private String studentID;
	private List<CourseLecturePair> enrolledLectures;
	
	public Student(String studentID)
	{
		this.studentID = studentID;
		enrolledLectures = new ArrayList<CourseLecturePair>();
	}
	
	public void enroll(CourseLecturePair courseLecturePair)
	{
		courseLecturePair.getLecture().incrementClassSize();
		enrolledLectures.add(courseLecturePair);
	}
}
