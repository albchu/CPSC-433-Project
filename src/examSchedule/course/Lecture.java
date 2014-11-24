package examSchedule.course;

import static examSchedule.common.Utilities.*;

import java.util.ArrayList;
import java.util.List;

import examSchedule.assignment.Session;
import examSchedule.assignment.Student;

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
	private Session session;

	private int examLength;
	private List<Student> enrolledStudents;
	
	public Lecture(String courseName, String lectureName)
	{
		nullCheck(courseName, lectureName);
		this.courseName = courseName;
		this.lectureName = lectureName;
		this.enrolledStudents = new ArrayList<Student>();
	}
	
	public String getLectureName()
	{
		return lectureName;
	}
	
	public int getClassSize()
	{
		return enrolledStudents.size();
	}

	public int getExamLength()
	{
		return examLength;
	}
	
	public void enrollStudent(Student student)
	{
		enrolledStudents.add(student);
	}

	public void setExamLength(int examLength)
	{
		this.examLength = examLength;
	}

	public Session getSession()
	{
		return session;
	}
	
	public void setSession(Session session)
	{
		this.session = session;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + ((enrolledStudents == null) ? 0 : enrolledStudents.hashCode());
		result = prime * result + examLength;
		result = prime * result + ((instructor == null) ? 0 : instructor.hashCode());
		result = prime * result + ((lectureName == null) ? 0 : lectureName.hashCode());
		result = prime * result + ((session == null) ? 0 : session.hashCode());
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
		if (courseName == null)
		{
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (enrolledStudents == null)
		{
			if (other.enrolledStudents != null)
				return false;
		} else if (!enrolledStudents.equals(other.enrolledStudents))
			return false;
		if (examLength != other.examLength)
			return false;
		if (instructor == null)
		{
			if (other.instructor != null)
				return false;
		} else if (!instructor.equals(other.instructor))
			return false;
		if (lectureName == null)
		{
			if (other.lectureName != null)
				return false;
		} else if (!lectureName.equals(other.lectureName))
			return false;
		if (session == null)
		{
			if (other.session != null)
				return false;
		} else if (!session.equals(other.session))
			return false;
		return true;
	}

	public List<Student> getEnrolledStudents()
	{
		return enrolledStudents;
	}

}
