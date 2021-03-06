package examSchedule.solution;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
	private int bestPenalty;
	private int solutionCount;
	SolutionPenaltyPair bestSolution;

	
	public Solve(Environment env){
		courseMapCopy = new CourseMap();
		sessionMapCopy = new SessionMap();
		assignmentMapCopy = env.getAssignmentMap();
		//Create copy of lectures and sessions
		courseMapCopy = env.getCourseMap();
		sessionMapCopy = env.getSessionMap();
		bestPenalty = 0;
		solutionCount = 0;
	}
	
	/**
	 * generateSolution: Generates a list of Lecture-Sessions pair
	 * @return List<String> List of assignments for the final solution
	 */
	public List<String> generateSolution(int maxTime, long startTime){
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
		int numPreAssign = assignmentMapCopy.size();

		long currentRunTime = 0;
		int backTrackIndex = 0;
		boolean solutionsExist = true;

		while(solutionsExist && currentRunTime < (maxTime-10)){
			int noValidCount = 0;

			//Iterate through lectures until all are assigned
			while(!unassignedLectures.isEmpty()){
				Lecture currentLecture = unassignedLectures.get(0);
				//calculate hard constraints and save valid sessions
				validSessions = getValidSessions(currentLecture, allSessions);
			
				//If no valid sessions we need to backtrack
				if(validSessions.isEmpty()){
					noValidCount++;
					if(noValidCount > 1000){
						Random randomNum = new Random();
						for(int i = 0; i < randomNum.nextInt(allLectures.size()-unassignedLectures.size()); i++){
							backTrackIndex = backtrack(numPreAssign, assignmentMapCopy, unassignedLectures);
							if(backTrackIndex == -1){
								solutionsExist = false;
								unassignedLectures.clear();
							}
						}
						sortedSessions.clear();
						continue;
					}
					System.out.println("No valid sessions");
					backTrackIndex = backtrack(numPreAssign, assignmentMapCopy, unassignedLectures);
					if(backTrackIndex ==-1){
						solutionsExist = false;
						unassignedLectures.clear();
					}
				}
				//Backtrack up one more level
				//No more valid sessions to try, all have been tried
				else if(backTrackIndex >= validSessions.size()){
					backTrackIndex = backtrack(numPreAssign, assignmentMapCopy, unassignedLectures);

					if(backTrackIndex == -1){
						solutionsExist = false;
						unassignedLectures.clear();
					}
					sortedSessions.clear();
				}
				else{
					//Calculate Soft Constraints
					List<Session> validSessionResize = new ArrayList<Session>();
					for(int i = 0; i < 2000 && i < validSessions.size(); i++){
						validSessionResize.add(validSessions.get(i));
					}
					if(backTrackIndex==0 || sortedSessions.size()==0){
						sortedSessions = getBestSessions(validSessionResize, currentLecture);
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
				System.out.println("SOLUTION REACHED: " + (solutionCount+1));
				if(bestPenalty > assignmentMapCopy.getPenalties() || solutionCount == 0){
					bestSolution  = (new SolutionPenaltyPair(assignmentMapCopy.exportList(), assignmentMapCopy.getPenalties()));
					bestPenalty = bestSolution.getPenalty();
				}
				solutionCount++;
				long curTime = System.currentTimeMillis();
				currentRunTime = curTime - startTime;
				Random randomNum = new Random();

				
				for(int i = 0; i < randomNum.nextInt(allLectures.size()); i++){
					backTrackIndex = backtrack(numPreAssign, assignmentMapCopy, unassignedLectures);
					if(backTrackIndex == -1){
						solutionsExist = false;
						continue;
					}
				}
				sortedSessions.clear();
			}
		}
		
		System.out.println("Final Penalty: -" + bestSolution.getPenalty());
		List<String> finalSolution = bestSolution.getSolution();
		return(finalSolution);
	}		

	/**
	 * backtrack: Removes an assignment, adds lecture to unassigned lecture list saves new backtrack index
	 * @param aMap
	 * @param unassignedLectures
	 * @return -1 if no assignment to remove, otherwise remove backTrackIndex
	 */
	private int backtrack(int preAssignNum, AssignmentMap aMap, List<Lecture> unassignedLectures){
		if(aMap.size() == preAssignNum){
			//System.out.println("backtrackIndex " + backTrackIndex);
			return -1;
		}
		Assignment removedAssignment = assignmentMapCopy.removeAssignment();
		Lecture removedLecture = removedAssignment.getLecture();
		unassignedLectures.add(0, removedLecture);
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

