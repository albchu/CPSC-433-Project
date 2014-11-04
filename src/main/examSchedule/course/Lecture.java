package main.examSchedule.course;

import static main.examSchedule.common.Utilities.*;

/**
 * Simple class that holds lecture level information: class size, exam length, and the name of the lecture
 * @author achu
 *
 */
public class Lecture
{
	private String lectureName;
	private double examLength;
	private int classSize;
	
	public Lecture(String lectureName)
	{
		nullCheck(lectureName);
		this.lectureName = lectureName;
		classSize = 0;
	}
	
	public String getLectureName()
	{
		return lectureName;
	}
	
	public int getClassSize()
	{
		return classSize;
	}

	public void incrementClassSize()
	{
		this.classSize++;
	}

	public double getExamLength()
	{
		return examLength;
	}
	
	public void setExamLength(double examLength)
	{
		this.examLength = examLength;
	}
}
