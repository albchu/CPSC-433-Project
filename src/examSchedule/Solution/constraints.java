package examSchedule.Solution;

import sun.org.mozilla.javascript.internal.ast.Assignment;
import examSchedule.assignment.Session;
import examSchedule.course.Lecture;

public class constraints {

	// Hard constraint checks for each assignment
	public boolean hardConsEachAssign(Session session, Lecture lecture) {
		if (capacity(session, lecture) && length(session, lecture))
			return true;
		else {
			return false;
		}
	}
	
	// Lecture capacity check
	private boolean capacity(Session session, Lecture lecture) {
		if (session.getRemainingCapacity() < 1) 
			return false;
		else {
			return true;
		}
	}
	
	// Session length check
	private boolean length(Session session, Lecture lecture) {
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
