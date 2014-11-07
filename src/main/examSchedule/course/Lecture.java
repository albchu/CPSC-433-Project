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
	private String instructor;
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
	
	public void setClassSize(int classSize)
	{
		this.classSize = classSize;
	}

	public void setExamLength(double examLength)
	{
		this.examLength = examLength;
	}

	public String getInstructor()
	{
		return instructor;
	}

	public void setInstructor(String instructor)
	{
		this.instructor = instructor;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lecture other = (Lecture) obj;
		if (classSize != other.classSize)
			return false;
		if (Double.doubleToLongBits(examLength) != Double.doubleToLongBits(other.examLength))
			return false;
		if (lectureName == null)
		{
			if (other.lectureName != null)
				return false;
		} else if (!lectureName.equals(other.lectureName))
			return false;
		return true;
	}
	
}
