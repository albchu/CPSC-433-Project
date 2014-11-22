package examSchedule.assignment;

import java.util.ArrayList;
import java.util.List;
import static examSchedule.common.Utilities.*;
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
		lecture.setSession(session);
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
		assignment.getLecture().setSession(null);
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
	
	/**
	 * Exports information in predicate form strings
	 * @return
	 */
	public List<String> exportList()
	{
		List<String> output = new ArrayList<String>();
		for(Assignment assignment : assignments)
			// assign
			output.add(predicateForm("assign", assignment.getLecture().getCourseName(), assignment.getLecture().getLectureName(), assignment.getSession().getSessionID()));
		
		return output;
	}
}
