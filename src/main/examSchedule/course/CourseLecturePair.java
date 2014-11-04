package main.examSchedule.course;

import static main.examSchedule.common.Utilities.*;

public class CourseLecturePair
{
	private String courseName;
	private Lecture lecture;

	public CourseLecturePair(String courseName, String lectureName)
	{
		nullCheck(courseName, lectureName);
		this.courseName = courseName;
		this.lecture = new Lecture(lectureName);
	}

	public String getCourseName()
	{
		return courseName;
	}

	public Lecture getLecture()
	{
		return lecture;
	}
	
}
