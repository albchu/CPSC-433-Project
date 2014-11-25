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
	public void addAssignment(Session session, Lecture lecture, int backTrackIndex, int penalty)
	{
		Assignment assignment = new Assignment(session, lecture, backTrackIndex, penalty);
		lecture.setSession(session);
		session.addLecture(lecture);
		assignments.add(assignment);
	}
	
	/**
	 * Pops off the last assignment, if no assignments to remove, will return null
	 * @return 
	 */
	public Assignment removeAssignment()
	{
		Assignment assignment = null;
		if(assignments.size() != 0)
		{
			assignment = assignments.remove(assignments.size()-1);
			Session session =  assignment.getSession();
			Lecture lecture = assignment.getLecture();
			lecture.setSession(null);
			session.removeLecture(lecture);
		}
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
	
	//FUNCTION FOR TESTING PURPOSES RIGHT NOW
	public int getPenalties(){
		int penalty = 0;
		for(Assignment assignment : assignments){
			// assign
			penalty += assignment.getPenalty();
		}
		return penalty;
	}
	
	
	/**
	 * Exports information in predicate form strings
	 * @return
	 */
	public List<String> exportList()
	{
		List<String> output = new ArrayList<String>();
		int penalty = 0;
		for(Assignment assignment : assignments){
			// assign
			penalty += assignment.getPenalty();
			System.out.println(predicateForm("assign", assignment.getLecture().getCourseName(), assignment.getLecture().getLectureName(), assignment.getSession().getSessionID()));
			output.add(predicateForm("assign", assignment.getLecture().getCourseName(), assignment.getLecture().getLectureName(), assignment.getSession().getSessionID()));
		}
		System.out.println("Penalty: " + penalty);
		return output;
	}
}
