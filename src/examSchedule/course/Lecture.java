package examSchedule.course;

import static examSchedule.common.Utilities.*;

/**
 * Simple class that holds lecture level information: class size, exam length, and the name of the lecture
 * @author achu
 *
 */
public class Lecture
{
	private String lectureName;
	private String instructor;
	private String courseName;
	private int examLength;
	private int classSize;
	private boolean isAssigned;
	
	public Lecture(String courseName, String lectureName)
	{
		nullCheck(courseName, lectureName);
		this.courseName = courseName;
		this.lectureName = lectureName;
		this.isAssigned = false;
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

	public int getExamLength()
	{
		return examLength;
	}
	
	public void setClassSize(int classSize)
	{
		this.classSize = classSize;
	}

	public void setExamLength(int examLength)
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

	public String getCourseName()
	{
		return courseName;
	}

	public boolean isAssigned()
	{
		return isAssigned;
	}

	public void setAssigned(boolean isAssigned)
	{
		this.isAssigned = isAssigned;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + classSize;
		result = prime * result + examLength;
		result = prime * result + ((instructor == null) ? 0 : instructor.hashCode());
		result = prime * result + ((lectureName == null) ? 0 : lectureName.hashCode());
		return result;
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
