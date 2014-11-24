package examSchedule.Solution;

import java.util.ArrayList;
import java.util.List;

import examSchedule.assignment.Assignment;
import examSchedule.assignment.AssignmentMap;
import examSchedule.assignment.Session;
import examSchedule.assignment.Student;
import examSchedule.course.Instructor;
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
	public static int calcAllSoftCon(Session aSession, Lecture aLecture){
		int totalSoft = 0;
		totalSoft += calcSoftOne(aSession, aLecture);
		totalSoft += calcSoftTwo(aSession, aLecture);
		totalSoft += calcSoftThree(aSession, aLecture);
		totalSoft += calcSoftFour(aSession, aLecture);
		totalSoft += calcSoftFive(aSession, aLecture);
		totalSoft += calcSoftSix(aSession, aLecture);
		totalSoft += calcSoftSeven(aSession, aLecture);
		return totalSoft;
	}

	/**
	 * Soft Constraint One: penalty=100/incident. No student writes more than one exam in a timeslot (no direct conflict)
	 * @param aMap
	 * @param aSession
	 * @param aLecture
	 * @return int penalty of constraints
	 */
	public static int calcSoftOne(Session aSession, Lecture aLecture) {
		int penalty = 0;
		List<Student>enrolledStudents = aLecture.getEnrolledStudents();
		for(Student student : enrolledStudents){
			List<Lecture> enrolledLectures = student.getEnrolledLectures();
			for(Lecture lecture : enrolledLectures){
				if(lecture.getSession().getDay().equals(aSession.getDay()) && lecture.getSession().getTime().equals(aSession.getTime())){
					//NEED TO EVENTUALLY CHECK OVERLAP NOT NECESSARILY SAME START
					penalty+=100;
				}
			}
		}
		return penalty;
	}
	
	/**
	 * Soft Constraint Two: penalty=20/incident. No instructor invigulates in more than one room at the same time (no direct conflict)
	 * @param aMap
	 * @param aSession
	 * @param aLecture
	 * @return int penalty of constraints
	 */
	public static int calcSoftTwo(Session aSession, Lecture aLecture) {
		int penalty = 0;
		Instructor anInstructor = aLecture.getInstructor();
		if(anInstructor != null){
			List<Lecture> coursesTaughtByInstructor = anInstructor.getInstructedLectures();
			for(int i = 0; i < coursesTaughtByInstructor.size(); i++){
				Session session1;
				session1 = coursesTaughtByInstructor.get(i).getSession();
				if(session1==null){
					continue;
				}	
				if(session1.getTime().equals(aSession.getTime()) && session1.getDay().equals(aSession.getDay()) && !session1.getRoom().equals(aSession.getRoom()))
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
	public static int calcSoftThree(Session aSession, Lecture aLecture) {
		int penalty = 0;
		//DO THIS SOMETIME AT THE END
		return penalty;
	}
	
	/**
	 * Soft Constraint Four: penalty=50/incident. No student writes for longer than 5 hours in a single day 
	 * @param aMap
	 * @param aSession
	 * @param aLecture
	 * @return int penalty of constraints
	 */
	public static int calcSoftFour(Session aSession, Lecture aLecture) {
		int penalty = 0;
		List<Student> studentsEnrolledInLec = aLecture.getEnrolledStudents();
		for(Student student : studentsEnrolledInLec){
			List<Lecture> studentsLectures = student.getEnrolledLectures();
			List<Session> studentSessions = new ArrayList<Session>();
			for(Lecture lecture: studentsLectures){
				studentSessions.add(lecture.getSession());
			}
			int totalTimeOnDay = 0;
			for(Session session: studentSessions){
				if(session.getDay().equals(aSession.getDay())){
					totalTimeOnDay += (session.getLength() + aSession.getLength());
				}
			}
			if(totalTimeOnDay > 5){
				penalty+=50;
			}
		}
		
		return penalty;
	}
	
	/**
	 * Soft Constraint Five: penalty=50/incident. No student should write exams with no break between them 
	 * @param aMap
	 * @param aSession
	 * @param aLecture
	 * @return int penalty of constraints
	 */
	public static int calcSoftFive(Session aSession, Lecture aLecture) {
		int penalty = 0;
		List<Student> studentsEnrolledInLec = aLecture.getEnrolledStudents();
		for(Student student : studentsEnrolledInLec){
			List<Lecture> studentsLectures = student.getEnrolledLectures();
			List<Session> studentSessions = new ArrayList<Session>();
			for(Lecture lecture: studentsLectures){
				studentSessions.add(lecture.getSession());
			}
			int totalTimeOnDay = 0;
			for(Session session: studentSessions){
				if(session.getDay().equals(aSession.getDay())){
				//	if(session.getTime().getHour()+session.getLength() == aSession.getTime()){
						//STILL NEED TO DO
					//}
				}
			}

		}
		return penalty;
	}
	
	/**
	 * Soft Constraint Six: penalty=20/session. All the exams taking place in a particular session should have the same length 
	 * @param aMap
	 * @param aSession
	 * @param aLecture
	 * @return int penalty of constraints
	 */	
	public static int calcSoftSix(Session aSession, Lecture aLecture) {
		int penalty = 0;
		List<Lecture> sessionLectures= aSession.getAssignedLectures();
		for(Lecture lecture : sessionLectures){
			if(lecture.getExamLength() < aLecture.getExamLength()){
				penalty += 20;
			}
		}
		return penalty;	
	}
	
	/**
	 * Soft Constraint Seven: penalty=5/session. Every exam in a session should take up the full time of the session 
	 * @param aMap
	 * @param aSession
	 * @param aLecture
	 * @return int penalty of constraints
	 */
	public static int calcSoftSeven(Session aSession, Lecture aLecture) {
		int penalty = 0;
		if(aLecture.getExamLength()!=aSession.getLength()){
			penalty+=5;
		}
		return penalty;
	}
}
