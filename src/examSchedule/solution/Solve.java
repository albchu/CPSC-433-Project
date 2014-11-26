package examSchedule.solution;
import java.util.*;

import examSchedule.assignment.Assignment;
import examSchedule.assignment.AssignmentMap;
import examSchedule.assignment.Session;
import examSchedule.assignment.SessionMap;
import examSchedule.assignment.SessionWorthPair;
import examSchedule.assignment.SolutionPenaltyPair;
import examSchedule.course.CourseMap;
import examSchedule.course.Lecture;
import examSchedule.parser.Environment;
 

public class Solve {
	private CourseMap courseMapCopy;
	private SessionMap sessionMapCopy;
	private AssignmentMap assignmentMapCopy;
	private List<SessionWorthPair> sortedSessions;
	private List<SolutionPenaltyPair> listOfSolutions;

	

	
	public Solve(Environment env){
		courseMapCopy = new CourseMap();
		sessionMapCopy = new SessionMap();
		assignmentMapCopy = env.getAssignmentMap();
		//Create copy of lectures and sessions
		courseMapCopy = env.getCourseMap();
		sessionMapCopy = env.getSessionMap();
		
	}
	
	/**
	 * generateSolution: Generates a list of Lecture-Sessions pair
	 * @return List<String> List of assignments for the final solution
	 */
	public List<String> generateSolution(){
		List<Session> validSessions = new ArrayList<Session>();
		List<Lecture> unassignedLectures = new ArrayList<Lecture>();
		// Get all lectures 
		List<Lecture> allLectures = courseMapCopy.getAllLectures();
		// Get all sessions
		List<Session> allSessions = sessionMapCopy.getAllSessions();
		sortedSessions = new ArrayList<SessionWorthPair>();
		listOfSolutions = new ArrayList<SolutionPenaltyPair>();
		
		//REFACTORABLE, SHOULDN'T NEED TO ITERATE THROUGH THE LIST
		System.out.println("Total Number of lectures" + allLectures.size());
		for(Lecture currentLecture : allLectures){
			if(currentLecture.getSession()== null)
				unassignedLectures.add(currentLecture);
		}
		
		System.out.println("Assigned Lectures: " + unassignedLectures.size());

		int backTrackIndex = 0;
		int solutions = 0;
		boolean solutionsExist = true;
		while(solutionsExist&&solutions < 20000){
			//Iterate through lectures until all are assigned
			while(!unassignedLectures.isEmpty()){
				Lecture currentLecture = unassignedLectures.get(0);
			//System.out.println("Attempting to assign lecture:" + currentLecture.getCourseName()+", "+currentLecture.getLectureName());

				//calculate hard constraints and save valid sessions
				validSessions = getValidSessions(currentLecture, allSessions);
				
				//If no valid sessions we need to backtrack
				if(validSessions.isEmpty()){
					//B
					System.out.println("No valid sessions");
					backTrackIndex = backtrack(assignmentMapCopy, unassignedLectures);
					//Assignment removedAssignment = assignmentMapCopy.removeAssignment();
					if(backTrackIndex ==-1){
						solutionsExist = false;
						unassignedLectures.clear();
					}
				}
				//Backtrack up one more level
				//No more valid sessions to try, all have been tried
				else if(backTrackIndex >= validSessions.size()){
					//A
				//	System.out.println("Backtrack up a level");
				//	System.out.println("backtrackIndex " + backTrackIndex);
					backTrackIndex = backtrack(assignmentMapCopy, unassignedLectures);

					if(backTrackIndex == -1){
						solutionsExist = false;
						unassignedLectures.clear();
					}
					sortedSessions.clear();
				}
				else{
					//Calculate Soft Constraints
					if(backTrackIndex==0 || sortedSessions.size()==0){
						sortedSessions = getBestSessions(validSessions, currentLecture);
					}
					//Add assignment
					Session bestSession = sortedSessions.get(backTrackIndex).getSession();
					int penalty = sortedSessions.get(backTrackIndex).getWorth();
					assignmentMapCopy.addAssignment(bestSession, currentLecture, backTrackIndex, penalty);
					unassignedLectures.remove(currentLecture);
					backTrackIndex = 0;
				}
			}
			
			//Save solution
			if(solutionsExist){
				//return assignmentMapCopy.exportList();
				System.out.println("SOLUTION REACHED");
				solutions++;
				listOfSolutions.add(new SolutionPenaltyPair(assignmentMapCopy.exportList(), assignmentMapCopy.getPenalties()));;
				Random randomNum = new Random();
				for(int i = 0; i < randomNum.nextInt(116); i++){
					backTrackIndex = backtrack(assignmentMapCopy, unassignedLectures);
					if(backTrackIndex == -1){
						solutionsExist = false;
						continue;
					}
				}
				/*for(int i = 0; i < 25; i++){
					Assignment removedAssignment = assignmentMapCopy.removeAssignment();
					if(removedAssignment == null){
						solutionsExist = false;
						continue;
					}
					Lecture removedLecture = removedAssignment.getLecture();
					unassignedLectures.add(0, removedLecture);
					backTrackIndex = removedAssignment.getBacktrackIndex() + 1;
				}*/
				sortedSessions.clear();
			}
			
		}
		Collections.sort(listOfSolutions);
		System.out.println("Solution:");
		int solutionCount = 0;
		System.out.println("Number of Solutions: " + listOfSolutions.size());
		List<String> finalSolution = listOfSolutions.get(0).getSolution();
		for(String solutionLine : finalSolution){
			solutionCount++;
			System.out.println(solutionLine);
		}
		System.out.println("Assigned: " + solutionCount + "Lectures");
		System.out.println("Penalty For Solution: -"+listOfSolutions.get(0).getPenalty());
		return(listOfSolutions.get(0).getSolution());
	}		

	/**
	 * backtrack: Removes an assignment, adds lecture to unassigned lecture list saves new backtrack index
	 * @param aMap
	 * @param unassignedLectures
	 * @return -1 if no assignment to remove, otherwise remove backTrackIndex
	 */
	private int backtrack(AssignmentMap aMap, List<Lecture> unassignedLectures){
		Assignment removedAssignment = assignmentMapCopy.removeAssignment();

		if(removedAssignment == null){
			//System.out.println("backtrackIndex " + backTrackIndex);
			return -1;
		}
		
		Lecture removedLecture = removedAssignment.getLecture();
		//System.out.println("Removed lecture" + removedLecture.getCourseName()+removedLecture.getLectureName());
		unassignedLectures.add(0, removedLecture);
		//index = 0;
		return (removedAssignment.getBacktrackIndex() + 1);
	}
	
	/**
	 * getValidSessions: Generates a list of valid sessions (No hard constraints violated)
	 * @param aLecture
	 * @param allSessions
	 * @return returns a list of sessions that are valid sessions 
	 */
	private List<Session> getValidSessions(Lecture aLecture, List<Session> allSessions){
		Lecture currentLecture = aLecture;
		List<Session> validSessions = new ArrayList<Session>();
		for(Session currentSession : allSessions){
			//Calculate Hard Constraints
			if(Constraints.calcAllHardCons(currentSession, currentLecture)){
				validSessions.add(currentSession);
			}
		}
		return validSessions;
	}
	
	/**
	 * getBestSessions: Calculate best sessions using soft constraint penalties
	 * @param validSessions
	 * @return List of SessionWorthPairs 
	 */
	private List<SessionWorthPair> getBestSessions(List<Session> validSessions, Lecture currentLecture){
		List<SessionWorthPair> sessionWorthPairList = new ArrayList<SessionWorthPair>();
		List<Lecture> listOfCourseLectures = courseMapCopy.getLectures(currentLecture.getCourseName());
		for(Session validSession : validSessions){
			//Calculate Soft constraints
			int curSoftConstraint = 0;
			curSoftConstraint = Constraints.calcAllSoftCon(validSession, currentLecture);
			curSoftConstraint += Constraints.calcSoftThree(listOfCourseLectures, validSession, currentLecture);
			sessionWorthPairList.add(new SessionWorthPair(validSession, curSoftConstraint));
		}
		//Sort the list in descending order of penalty
		Collections.sort(sessionWorthPairList);
	return sessionWorthPairList;
	}
}

