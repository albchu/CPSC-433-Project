package examSchedule.course;

import java.util.*;

public class Instructor
{
	private String instructorID;
	private List<Lecture> instructedLectures;
	
	public Instructor(String instructorID)
	{
		this.instructorID = instructorID;
		instructedLectures = new ArrayList<Lecture>();
	}
	
	public void addInstructedLecture(Lecture lecture)
	{
		instructedLectures.add(lecture);
	}
	
	public String getInstructorID()
	{
		return instructorID;
	}

	public List<Lecture> getInstructedLectures()
	{
		return instructedLectures;
	}
}
