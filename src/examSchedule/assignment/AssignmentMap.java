package examSchedule.assignment;

import java.util.ArrayList;
import java.util.List;

import examSchedule.course.Lecture;
import examSchedule.parser.Environment;

public class AssignmentMap
{
	private List<Assignment> assignments;

	public List<Assignment> getAssignments()
	{
		return assignments;
	}

	public AssignmentMap()
	{
		this.assignments = new ArrayList<Assignment>();	// Holds all assignments to be printed at the end
	}
	
	/*
	 * Adds an assignment to the map
	 */
	public void addAssignment(Session session, Lecture lecture)
	{
		Assignment assignment = new Assignment(session, lecture);
		session.decrementRemainingCapacity(lecture.getClassSize());
		assignments.add(assignment);
	}

	/**
	 * Pops off the last assignment
	 */
	public void removeAssignment()
	{
		Assignment assignment = assignments.remove(assignments.size()-1);
		assignment.getSession().incrementRemainingCapacity(assignment.getLecture().getClassSize());
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
}
