package examSchedule.Solution;

import java.util.List;

import examSchedule.assignment.AssignmentMap;
import examSchedule.assignment.Session;
import examSchedule.course.Lecture;

public class Constraints {

	//Hard Constraints
	/**
	 * Calculate all hard constraints
	 * @param session
	 * @param lecture
	 * @return boolean true if all hard constraints are not violated
	 */
	public static boolean calcAllHardCons(Session session, Lecture lecture) {
		if (capacityHardCon(session, lecture) && timeLengthHardCon(session, lecture))
			return true;
		else {
			return false;
		}
	}
	
	/**
	 * Hard Constraint: Lecture capacity check
	 * @param session
	 * @param lecture
	 * @return boolean true if hard constraint not violated
	 */
	private static boolean capacityHardCon(Session session, Lecture lecture) {
		if ((session.getRemainingCapacity()-lecture.getClassSize()) < 0) 
			return false;
		else {
			return true;
		}
	}
	
	/**
	 * Hard Constraint: Session length check
	 * @param session
	 * @param lecture
	 * @return boolean true if hard constraint not violated
	 */
	private static boolean timeLengthHardCon(Session session, Lecture lecture) {
		if (lecture.getExamLength() > session.getLength()) 
			return false;
		else {
			return true;
		}
	}
	
	
	//Soft Constaints 
	/**
	 * LIST OF SOFT CONSTRAINTS
	 * Soft Constraint One: penalty=100/incident. No student writes more than one exam in a timeslot (no direct conflict)
	 * Soft Constraint Two: penalty=20/incident. No instructor invigulates in more than one room at the same time (no direct conflict)
	 * Soft Constraint Three: penalty=50/incident. Every lecture for the same course should have the same exam timeslot 
	 * Soft Constraint Four: penalty=50/incident. No student writes for longer than 5 hours in a single day 
	 * Soft Constraint Five: penalty=50/incident. No student should write exams with no break between them 
	 * Soft Constraint Six: penalty=20/session. All the exams taking place in a particular session should have the same length 
	 * Soft Constraint Seven: penalty=5/session. Every exam in a session should take up the full time of the session 
	 */
	
	/**
	 * calCAllSoftCon: calculates all soft constraints given a map of already assigned lecture-session pairs and a to be assigned lecture-session pair
	 * @param aMap
	 * @param aSession
	 * @param aLecture
	 * @return total soft constraint penalty calculated
	 */
	public static int calcAllSoftCon(AssignmentMap aMap, Session aSession, Lecture aLecture){
		int totalSoft = 0;
		totalSoft += calcSoftOne(aMap, aSession, aLecture);
		totalSoft += calcSoftTwo(aMap, aSession, aLecture);
		totalSoft += calcSoftThree(aMap, aSession, aLecture);
		totalSoft += calcSoftFour(aMap, aSession, aLecture);
		totalSoft += calcSoftFive(aMap, aSession, aLecture);
		totalSoft += calcSoftSix(aMap, aSession, aLecture);
		totalSoft += calcSoftSeven(aMap, aSession, aLecture);
		return totalSoft;
	}

	/**
	 * Soft Constraint One: penalty=100/incident. No student writes more than one exam in a timeslot (no direct conflict)
	 * @param aMap
	 * @param aSession
	 * @param aLecture
	 * @return int penalty of constraints
	 */
	private static int calcSoftOne(AssignmentMap aMap, Session aSession, Lecture aLecture) {
		// TODO Auto-generated method stub
		int penalty = 0;
		return penalty;
	}
	
	/**
	 * Soft Constraint Two: penalty=20/incident. No instructor invigulates in more than one room at the same time (no direct conflict)
	 * @param aMap
	 * @param aSession
	 * @param aLecture
	 * @return int penalty of constraints
	 */
	private static int calcSoftTwo(AssignmentMap aMap, Session aSession, Lecture aLecture) {
		int penalty = 0;
		Instructor anInstructor = aLecture.getInstructor();
		List<Lecture> coursesTaughtByInstructor = anInstructor.getLectures();
		List<Session> sessionsbyInstructor;
		for(int i = 0; i < coursesTaughtByInstructor.size(); i++){
			Session session1;
			session1 = coursesTaughtByInstructor.get(i).getSession();
			if(session1==null){
				continue;
			}	
			for (int j = i+1; j < coursesTaughtByInstructor.size(); j++)
			{
				Session session2;
				session2 = coursesTaughtByInstructor.get(i).getSession();
				if(session2 ==null){
					continue; // Shane: check that this aint null
				}
				if(session1.getTime().equals(session2.getTime()) && session1.getDay().equals(session2.getDay()) && !session1.getRoom().equals(session2.getRoom()))
					penalty += 20;
			}
		}
		return penalty;
	}
	
	/**
	 * Soft Constraint Three: penalty=50/incident. Every lecture for the same course should have the same exam timeslot 
	 * @param aMap
	 * @param aSession
	 * @param aLecture
	 * @return int penalty of constraints
	 */
	private static int calcSoftThree(AssignmentMap aMap, Session aSession, Lecture aLecture) {
		// TODO Auto-generated method stub
		int penalty = 0;
		return penalty;
	}
	
	/**
	 * Soft Constraint Four: penalty=50/incident. No student writes for longer than 5 hours in a single day 
	 * @param aMap
	 * @param aSession
	 * @param aLecture
	 * @return int penalty of constraints
	 */
	private static int calcSoftFour(AssignmentMap aMap, Session aSession, Lecture aLecture) {
		// TODO Auto-generated method stub
		int penalty = 0;
		return penalty;
	}
	
	/**
	 * Soft Constraint Five: penalty=50/incident. No student should write exams with no break between them 
	 * @param aMap
	 * @param aSession
	 * @param aLecture
	 * @return int penalty of constraints
	 */
	private static int calcSoftFive(AssignmentMap aMap, Session aSession, Lecture aLecture) {
		// TODO Auto-generated method stub
		int penalty = 0;
		return penalty;
	}
	
	/**
	 * Soft Constraint Six: penalty=20/session. All the exams taking place in a particular session should have the same length 
	 * @param aMap
	 * @param aSession
	 * @param aLecture
	 * @return int penalty of constraints
	 */	
	private static int calcSoftSix(AssignmentMap aMap, Session aSession, Lecture aLecture) {
		// TODO Auto-generated method stub
		int penalty = 0;
		return penalty;
	}
	
	/**
	 * Soft Constraint Seven: penalty=5/session. Every exam in a session should take up the full time of the session 
	 * @param aMap
	 * @param aSession
	 * @param aLecture
	 * @return int penalty of constraints
	 */
	private static int calcSoftSeven(AssignmentMap aMap, Session aSession, Lecture aLecture) {
		int penalty = 0;
		if(aLecture.getExamLength()!=aSession.getLength()){
			penalty+=5;
		}
		return penalty;
	}
}
