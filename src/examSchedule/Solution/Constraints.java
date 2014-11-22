package examSchedule.Solution;

import examSchedule.assignment.Session;
import examSchedule.course.Lecture;

public class Constraints {

	// Hard constraint checks for each assignment
	public static boolean hardConsEachAssign(Session session, Lecture lecture) {
		if (capacity(session, lecture) && length(session, lecture))
			return true;
		else {
			return false;
		}
	}
	
	// Lecture capacity check
	private static boolean capacity(Session session, Lecture lecture) {
		if (session.getRemainingCapacity() < 1) 
			return false;
		else {
			return true;
		}
	}
	
	// Session length check
	private static boolean length(Session session, Lecture lecture) {
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
