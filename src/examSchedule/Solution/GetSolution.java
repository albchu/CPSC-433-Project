package examSchedule.Solution;
import java.util.*;

import examSchedule.assignment.AssignmentMap;
import examSchedule.assignment.Session;
import examSchedule.assignment.SessionMap;
import examSchedule.course.CourseMap;
import examSchedule.course.Lecture;
import examSchedule.parser.Environment;
 

public class GetSolution {
	private CourseMap courseMapCopy;
	private SessionMap sessionMapCopy;
	private AssignmentMap assignmentMapCopy;
	private int index;
	
	public GetSolution(Environment env){
		courseMapCopy = new CourseMap();
		sessionMapCopy = new SessionMap();
		assignmentMapCopy = env.getAssignmentMap();
		
		//Create copy of lectures and sessions
		courseMapCopy = env.getCourseMap();
		sessionMapCopy = env.getSessionMap();
	}
	
	public List<String> generateSolution(){
		List<Session> validSessions = new ArrayList<Session>();
		List<Lecture> unassignedLectures = new ArrayList<Lecture>();
		// Get all lectures 
		List<Lecture> allLectures = courseMapCopy.getAllLectures();
		// Get all sessions
		List<Session> allSessions = sessionMapCopy.getAllSessions();
		//REFACTORABLE, SHOULDN'T NEED TO ITERATE THROUGH THE LIST
		for(Lecture currentLecture : allLectures){
			if(currentLecture.getSession()== null)
				unassignedLectures.add(currentLecture);
		}
		
		index = 0;
		int backTracks = 0;
		//Iterate through lectures until all are assigned
		while(!unassignedLectures.isEmpty()){
			Lecture currentLecture = allLectures.get(index);
			
			//calulate hard constraints and save valids
			validSessions = getValidSessions(currentLecture, allSessions);
			//If no valid sessions we need to backtrack
			if(validSessions.isEmpty()){
				assignmentMapCopy.removeAssignment();
			}
			backTracks = 0;
			//Calculate Soft Constraints
			Session bestSession = getBestSessions(validSessions);
			//Add assignment
			assignmentMapCopy.addAssignment(bestSession, currentLecture);
			unassignedLectures.remove(currentLecture);
		}
		//Save solution
		return assignmentMapCopy.exportList();
	}		
	
	private List<Session> getValidSessions(Lecture aLecture, List<Session> allSessions){
		Lecture currentLecture = aLecture;
		List<Session> validSessions = new ArrayList<Session>();
		for(Session currentSession : allSessions){
			//WAITING FOR ARTHUR
			//Calculate hardconstraints
			if(Constraints.calcAllHardCons(currentSession, currentLecture)){
				validSessions.add(currentSession);
			}
			//if validSessions.isEmpty(), Back track
		}
		return validSessions;
	}
	
	private Session getBestSessions(List<Session> validSessions){
		int bestSoftConstraint = 100000;
		Session bestSession = null;
		for(Session validSession : validSessions){
			int curSoftConstraint = 0;
			//WAITING FOR ANTHONY/ARTHUR(?)
			//currentSoftConstraint = softConstraint.calculateAllSoft(currentLecture, validSession, assignedSessions);
			if(curSoftConstraint < bestSoftConstraint){
				bestSoftConstraint = curSoftConstraint;
				bestSession = validSession;
			}
		}
	return bestSession;
	}
}

