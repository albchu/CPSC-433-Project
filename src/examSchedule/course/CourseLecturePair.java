package examSchedule.course;

import static examSchedule.common.Utilities.*;

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

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseLecturePair other = (CourseLecturePair) obj;
		if (courseName == null)
		{
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (lecture == null)
		{
			if (other.lecture != null)
				return false;
		} else if (!lecture.equals(other.lecture))
			return false;
		return true;
	}
	
}
