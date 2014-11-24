package examSchedule.Solution;

import examSchedule.assignment.AssignmentMap;
import examSchedule.assignment.Session;
import examSchedule.course.Lecture;

public class Constraints {

	// Hard constraint checks for each assignment
	public static boolean calcAllHardCons(Session session, Lecture lecture) {
		if (capacityHardCon(session, lecture) && timeLengthHardCon(session, lecture))
			return true;
		else {
			return false;
		}
	}
	
	// Lecture capacity check
	private static boolean capacityHardCon(Session session, Lecture lecture) {
		if ((session.getRemainingCapacity()-lecture.getClassSize()) < 0) 
			return false;
		else {
			return true;
		}
	}
	
	// Session length check
	private static boolean timeLengthHardCon(Session session, Lecture lecture) {
		if (lecture.getExamLength() > session.getLength()) 
			return false;
		else {
			return true;
		}
		
	}
	
	// Completeness check
/*	public boolean completeness(Lecture[] lectures, Assignment[] assignments) {
		for (int i = 0; i < lectures.length; i++) {
			if (lectures[i]) {
				
			}
			for (int j = 0; j < assignments.length j++) {
				if 
			}
		}
	}*/
	
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
	private static int calcSoftOne(AssignmentMap aMap, Session aSession,
			Lecture aLecture) {
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
	private static int calcSoftTwo(AssignmentMap aMap, Session aSession,
			Lecture aLecture) {
		// TODO Auto-generated method stub
		int penalty = 0;
		return penalty;
	}
	
	/**
	 * Soft Constraint Three: penalty=50/incident. Every lecture for the same course should have the same exam timeslot 
	 * @param aMap
	 * @param aSession
	 * @param aLecture
	 * @return int penalty of constraints
	 */
	private static int calcSoftThree(AssignmentMap aMap, Session aSession,
			Lecture aLecture) {
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
	private static int calcSoftFour(AssignmentMap aMap, Session aSession,
			Lecture aLecture) {
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
	private static int calcSoftFive(AssignmentMap aMap, Session aSession,
			Lecture aLecture) {
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
	private static int calcSoftSix(AssignmentMap aMap, Session aSession,
			Lecture aLecture) {
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
	private static int calcSoftSeven(AssignmentMap aMap, Session aSession,
			Lecture aLecture) {
		// TODO Auto-generated method stub
		int penalty = 0;
		return penalty;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
