package examSchedule.Solution;

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
	
}
