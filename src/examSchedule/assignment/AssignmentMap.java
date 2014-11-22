package examSchedule.assignment;

import java.util.ArrayList;
import java.util.List;

import examSchedule.course.Lecture;
import examSchedule.parser.Environment;

public class AssignmentMap
{
	private List<Lecture> unassignedLectures;
	private List<Assignment> assignments;
	
	public AssignmentMap()
	{
		this(new ArrayList<Lecture>());
	}

	public List<Assignment> getAssignments()
	{
		return assignments;
	}

	public AssignmentMap(List<Lecture> unassignedLectures)
	{
		// TODO: This needs to be a deep level clone eventually
		this.unassignedLectures = unassignedLectures;
		this.assignments = new ArrayList<Assignment>();	// Holds all assignments to be printed at the end
	}
	
	/*
	 * Adds an assignment to the map
	 */
	public void addAssignment(Session session, Lecture lecture)
	{
		unassignedLectures.remove(lecture);
		Assignment assignment = new Assignment(session, lecture);
		session.decrementRemainingCapacity(lecture.getClassSize());
		assignments.add(assignment);
	}

	/**
	 * Pops off the last assignment
	 */
	public void removeAssignment()
	{
		assignments.remove(assignments.size()-1);
	}
	
	/**
	 * Removes the last n assignments
	 * @param n
	 */
	public void removeAssignment(int n)
	{
		for (int i = 0; i < n; i++)
			removeAssignment();
	}
	
	public List<String> exportList()
	{
		// TODO: This needs to be done still.
		List<String> output = new ArrayList<String>();
		output.add("EXPORT FROM ASSIGNMENT MAP HAS NOT BEEN WRITTEN. SORRY GUYS");
		return output;
	}

	public List<Lecture> getUnassignedLectures()
	{
		return unassignedLectures;
	}
}
