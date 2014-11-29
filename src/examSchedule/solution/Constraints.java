package examSchedule.solution;

import java.util.ArrayList;
import java.util.List;

import examSchedule.assignment.Session;
import examSchedule.assignment.Student;
import examSchedule.course.Instructor;
import examSchedule.course.Lecture;
import examSchedule.date.Time;

public class Constraints {

	//Hard Constraints
	/**
	 * Calculate all hard constraints
	 * @param session
	 * @param lecture
	 * @return boolean true if all hard constraints are not violated
	 */
	public static boolean calcAllHardCons(Session session, Lecture lecture) {
		//System.out.println("Checking Hard Constaints");

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
	//	System.out.println("Remaining cap: " + session.getRemainingCapacity());
	//	System.out.println("Lecture Size: " + lecture.getClassSize());
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
	//	System.out.println("Exam Length: " + lecture.getExamLength());
		//System.out.println("Session Length: " + session.getLength());
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
		//System.out.println("Checking Soft Constaints");
		totalSoft += calcSoftOne(aSession, aLecture);
		totalSoft += calcSoftTwo(aSession, aLecture);
		//totalSoft += calcSoftThree(aSession, aLecture);
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
				Session session = lecture.getSession();
				if(!(session==null || session == aSession)){
					if(lecture.getSession().getDay().equals(aSession.getDay())){
					//	Time lecSessTime = lecture.getSession().getTime();
					//	Time inputSessTime = aSession.getTime();
						double timeDiff = session.getTime().getDifference(aSession.getTime());
						if(timeDiff == 0.0){
							penalty += 100;
						}
						else if(timeDiff > 0.0){
							if(timeDiff < (double)aSession.getLength()){
								penalty+=100;
							}
						}
						else{
							if(Math.abs(timeDiff)<(double)session.getLength()){
								penalty+=100;
							}
						}
					}
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
				if(session1==null || session1 == aSession){
					continue;
				}
				if(session1.getDay().equals(aSession.getDay())&& !(session1.getRoom().equals(aSession.getRoom()))){
					double timeDiff = session1.getTime().getDifference(aSession.getTime());
					//System.out.println(timeDiff);
					if(timeDiff == 0.0){
						penalty+=20;						
					}
					if(timeDiff > 0.0){
						if(timeDiff < (double)aSession.getLength()){
							penalty+=20;
						}
					}
					else{
						if(Math.abs(timeDiff) < (double)session1.getLength()){
							penalty+=20;
						}
					}
				}
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
	public static int calcSoftThree(List<Lecture> listOfLecs, Session aSession, Lecture aLecture) {
		int penalty = 0;
		for(Lecture lecture : listOfLecs){
			//if(lecture.getSession() !=null && !(lecture.getSession().getDay().equals(aSession.getDay())&&lecture.getSession().getTime().equals(aSession.getTime()))){
			if(lecture.getSession() != null && lecture.getSession() != aSession){
				penalty += 50;
			}
		}
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
				Session session = lecture.getSession();
				if(session != null){
					studentSessions.add(lecture.getSession());
				}
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
				Session session = lecture.getSession();
				if(session != null&&!(lecture.equals(aLecture))){
					studentSessions.add(lecture.getSession());
				}
			}
			for(Session session: studentSessions){
				if(session.getDay().equals(aSession.getDay())){
					double timeDiff = session.getTime().getDifference(aSession.getTime());
					if(timeDiff == 0.0){
						penalty+=50;
					}
					else if(timeDiff > 0.0){
						if(timeDiff == (double)aSession.getLength()){
							penalty+=50;
						}
					}
					else{
						if(Math.abs(timeDiff) == (double)session.getLength()){
							penalty+=50;
						}
					}
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
			if(lecture.getExamLength() != aLecture.getExamLength()){
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
