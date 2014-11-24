package test;

import examSchedule.assignment.Assignment;
import examSchedule.assignment.Session;
import examSchedule.assignment.Student;
import examSchedule.course.Lecture;
import examSchedule.date.Time;
import examSchedule.Solution.Constraints;

import org.junit.*;

public class ConstraintTests {
	

	Lecture lecture1 = new Lecture("CPSC433", "L01");
	//Lecture lecture2 = new Lecture("CPSC433", "L02");
	Session session1 = new Session("S1");
	
	//Session session2 = new Session("S2");
	
	@Test
	public void SoftCon1failtest() {
		Student student = new Student("Bob");
		lecture1.enrollStudent(student);
		Time time = new Time.Builder("6:00").build();
		session1.setTime(time);
		session1.setDay("M");
		lecture1.setSession(session1);
		//Assignment assignment = new Assignment(session1, lecture1, 0, 0);
		student.enroll(lecture1);
		
		Lecture lecture2 = new Lecture("CPSC413", "L01");
		Session session2 = new Session("S2");
		lecture2.enrollStudent(student);
		session2.setTime(time);
		session2.setDay("M");
		
		int violation = Constraints.calcSoftOne(session2, lecture2);
		Assert.assertEquals("Soft constraint violation is correct", 100, violation);
	}
	
	@Test
	public void SoftCon1passtest() {
		Student student = new Student("Bob");
		lecture1.enrollStudent(student);
		Time time = new Time.Builder("9:00").build();
		session1.setTime(time);
		session1.setDay("M");
		lecture1.setSession(session1);
		//Assignment assignment = new Assignment(session1, lecture1, 0, 0);
		student.enroll(lecture1);
		
		Lecture lecture2 = new Lecture("CPSC413", "L01");
		Session session2 = new Session("S2");
		lecture2.enrollStudent(student);
		Time time2 = new Time.Builder("3:00").build();
		session2.setTime(time2);
		session2.setDay("M");
		
		int violation = Constraints.calcSoftOne(session2, lecture2);
		Assert.assertEquals("Soft constraint violation is correct", 0, violation);
	}
	
	@Test
	public void SoftCon7failtest() {
		session1.setLength(3);
		lecture1.setExamLength(2);	
		int violation = Constraints.calcSoftSeven(session1, lecture1);
		Assert.assertEquals("Soft constraint violation is correct", 5, violation);
	}
	
	@Test
	public void SoftCon7passtest() {
		session1.setLength(3);
		lecture1.setExamLength(3);	
		int violation = Constraints.calcSoftSeven(session1, lecture1);
		Assert.assertEquals("Soft constraint violation is correct", 0, violation);
	}
	
	//Assignment assignment2 = new Assignment();
}
