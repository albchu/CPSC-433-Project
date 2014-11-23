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
	public void addAssignment(Session session, Lecture lecture, int backTrackIndex)
	{
		Assignment assignment = new Assignment(session, lecture, backTrackIndex);
		lecture.setSession(session);
		session.decrementRemainingCapacity(lecture.getClassSize());
		assignments.add(assignment);
	}

	/**
	 * Pops off the last assignment
	 * @return 
	 */
	public Assignment removeAssignment()
	{
		Assignment assignment = assignments.remove(assignments.size()-1);
		assignment.getSession().incrementRemainingCapacity(assignment.getLecture().getClassSize());
		assignment.getLecture().setSession(null);
		return assignment;
	}
	
	/**
	 * Removes the last n assignments
	 * @param n
	 */
	public List<Assignment> removeAssignment(int n)
	{
		List<Assignment> removed = new ArrayList<Assignment>();
		for (int i = 0; i < n; i++)
			removed.add(removeAssignment());
		return removed;
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
