package examSchedule.Solution;
import java.util.*;

import examSchedule.assignment.Assignment;
import examSchedule.assignment.AssignmentMap;
import examSchedule.assignment.Session;
import examSchedule.assignment.SessionMap;
import examSchedule.assignment.SessionWorthPair;
import examSchedule.course.CourseMap;
import examSchedule.course.Lecture;
import examSchedule.parser.Environment;
 

public class Solve {
	private CourseMap courseMapCopy;
	private SessionMap sessionMapCopy;
	private AssignmentMap assignmentMapCopy;
	private List<SessionWorthPair> sortedSessions;

	
	public Solve(Environment env){
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
		sortedSessions = new ArrayList<SessionWorthPair>();
		//REFACTORABLE, SHOULDN'T NEED TO ITERATE THROUGH THE LIST
		for(Lecture currentLecture : allLectures){
			if(currentLecture.getSession()== null)
				unassignedLectures.add(currentLecture);
		}
		
		//COMMENTING OUT TO TRY RECURSION FML
		int backTrackIndex = 0;
		
		//Iterate through lectures until all are assigned
		while(!unassignedLectures.isEmpty()){
			Lecture currentLecture = unassignedLectures.get(0);
			
			//calculate hard constraints and save valids
			validSessions = getValidSessions(currentLecture, allSessions);
			
			//If no valid sessions we need to backtrack
			if(validSessions.isEmpty()){
				//B
				Assignment removedAssignment = assignmentMapCopy.removeAssignment();
				Lecture removedLecture = removedAssignment.getLecture();
				unassignedLectures.add(0, removedLecture);
				//index = 0;
				backTrackIndex = removedAssignment.getBacktrackIndex() + 1;
				continue;
			}
			//Backtrack up one more level
			//No more valid sessions to try, all have been tried
			if(backTrackIndex >=validSessions.size()){
				//A
				Assignment removedAssignment = assignmentMapCopy.removeAssignment();
				Lecture removedLecture = removedAssignment.getLecture();
				unassignedLectures.add(0, removedLecture);
				//index = 0;
				backTrackIndex = removedAssignment.getBacktrackIndex() + 1;
				sortedSessions.clear();
				continue;
			}
			//Calculate Soft Constraints
			if(backTrackIndex==0 || sortedSessions.size()==0){
				sortedSessions = getBestSessions(validSessions);
			}
			//Add assignment
			Session bestSession = sortedSessions.get(backTrackIndex).getSession();
			assignmentMapCopy.addAssignment(bestSession, currentLecture, backTrackIndex);
			unassignedLectures.remove(currentLecture);
			backTrackIndex = 0;
		}
		//Save solution
		return assignmentMapCopy.exportList();
	}		
	
/*	private Assignment generateSolutionRec(Lecture currentLecture, List<Session> allSessions, List<Lecture> unassignedLectures, int width){
		
			List<Session> validSessions = getValidSessions(currentLecture, allSessions);
			return null;
	}*/

	
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
	
	private List<SessionWorthPair> getBestSessions(List<Session> validSessions){
		List<SessionWorthPair> sessionWorthPairList = new ArrayList<SessionWorthPair>();
		for(Session validSession : validSessions){
			int curSoftConstraint = 0;
			//WAITING FOR ANTHONY/ARTHUR(?)
			//currentSoftConstraint = Constraints.calculateAllSoft(currentLecture, validSession, assignedSessions);
			sessionWorthPairList.add(new SessionWorthPair(validSession, curSoftConstraint));
		}
		
		//
		Collections.sort(sessionWorthPairList);
	return sessionWorthPairList;
	}
}

