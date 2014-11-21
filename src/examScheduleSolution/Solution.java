package examScheduleSolution;
import java.util.*;

import examSchedule.assignment.Session;
import examSchedule.assignment.SessionMap;
import examSchedule.course.CourseMap;
import examSchedule.course.Lecture;
import examSchedule.parser.Environment;
 

public class Solution {
	private CourseMap courseMapCopy;
	private SessionMap sessionMapCopy;
	private boolean existsLectureNotAssigned;
	private int index;
	private Lecture currentLecture;
	
	public Solution(Environment env){
		courseMapCopy = new CourseMap();
		sessionMapCopy = new SessionMap();
		List<Session> validSessions = new ArrayList<Session>();
		
		//Create copy of lectures and sessions
		courseMapCopy = env.getCourseMap();
		sessionMapCopy = env.getSessionMap();
			
		// Get all lectures 
		List<Lecture> allLectures = courseMapCopy.getAllLectures();
		// Get all sessions
		//List<Session> allSessions = sessionMapCopy.getAllSessions();
		
		//testing
		for(int i = 0; i < allLectures.size(); i++)
		{
			System.out.println("Lecture: " + allLectures.get(i).getCourseName()+ allLectures.get(i).getLectureName());
		}
		
		index = 0;
		existsLectureNotAssigned = true;
		//Iterate through lectures until all are assigned
		while(existsLectureNotAssigned){
			//get a single lecture
			currentLecture = allLectures.get(index);
			//Make sure lecture is not assigned yet
			if(!currentLecture.isAssigned()){
				index ++;
				continue;
			}
			//calulate hard constraints and save valids
			for(Session currentSession : allSessions){
				//WAITING FOR ARTHUR
				//Calculate hardconstraints
				//if(hardConstraint.calculateAllHard(currentLecture, currentSession)){
					validSessions.add(currentSession);
				//}
			}
			//Calculate Soft Constraints
			int bestSoftConstraint = 100000;
			Session bestSession;
			for(Session validSession : validSessions){
				int curSoftConstraint;
				//WAITING FOR ANTHONY/ARTHUR(?)
				//currentSoftConstraint = softConstraint.calculateAllSoft(currentLecture, validSession, assignedSessions);
				if(curSoftConstraint < bestSoftConstraint){
					bestSoftConstraint = curSoftConstraint;
					bestSession = validSession;
				}
				//ALBERT CHANGES
				//assignmentMap.assign(currentLecture, bestSession);
				//decrement bestSession remaining capacity
				//set currentLecture flag to assigned
			}
		}
		

		// if false don't save it and continue
		// Take new list of validPair and calculate soft constraints on them all
		// Find the best
		// Save the best in List of bests
		// flag lecture as assigned and decrement session capacity
		// grab next lecture and repeat
	}
}
