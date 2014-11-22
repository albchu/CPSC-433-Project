package examSchedule.Solution;
import java.util.*;

import examSchedule.assignment.AssignmentMap;
import examSchedule.assignment.Session;
import examSchedule.assignment.SessionMap;
import examSchedule.course.CourseMap;
import examSchedule.course.Lecture;
import examSchedule.parser.Environment;
 

public class Solution {
	private CourseMap courseMapCopy;
	private SessionMap sessionMapCopy;
	private AssignmentMap assignmentMapCopy;
	private int index;
	
	public Solution(Environment env){
		courseMapCopy = new CourseMap();
		sessionMapCopy = new SessionMap();
		List<Session> validSessions = new ArrayList<Session>();
		List<Lecture> unassignedLectures = new ArrayList<Lecture>();

		assignmentMapCopy = env.getAssignmentMap();
		
		//Create copy of lectures and sessions
		courseMapCopy = env.getCourseMap();
		sessionMapCopy = env.getSessionMap();
		unassignedLectures = assignmentMapCopy.getUnassignedLectures();
		
		
		// Get all lectures 
		List<Lecture> allLectures = courseMapCopy.getAllLectures();
		// Get all sessions
		List<Session> allSessions = sessionMapCopy.getAllSessions();
		
		//testing
		for(int i = 0; i < allLectures.size(); i++)
		{
			System.out.println("Lecture: " + allLectures.get(i).getCourseName()+ allLectures.get(i).getLectureName());
		}
		
		index = 0;
		//Iterate through lectures until all are assigned
		while(!unassignedLectures.isEmpty()){
			allLectures.get(index);
			
			//calulate hard constraints and save valids
			for(Session currentSession : allSessions){
				//WAITING FOR ARTHUR
				//Calculate hardconstraints
				if(hardConstraint.calculateAllHard(currentLecture, currentSession)){
					validSessions.add(currentSession);
				}
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
		
		// grab next lecture and repeat until all assigned
	}
	//return final assignment list
}
