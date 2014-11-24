package examSchedule.course;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class InstructorMap
{
	private HashMap<String, Instructor> instructorMap;
	
	public InstructorMap()
	{
		instructorMap = new HashMap<String, Instructor>();
	}
	
	public Instructor getInstructor(String instructorID)
	{
		addInstructor(instructorID);
		return instructorMap.get(instructorID);
	}
	
	public void addInstructor(String instructorID)
	{
		if(!instructorMap.containsKey(instructorID))
			instructorMap.put(instructorID, new Instructor(instructorID));
	}
	
	public Collection<Instructor> getInstructors()
	{
		return instructorMap.values();
	}
}
