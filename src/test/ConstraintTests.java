package test;

import examSchedule.assignment.Assignment;
import examSchedule.assignment.Room;
import examSchedule.assignment.Session;
import examSchedule.assignment.Student;
import examSchedule.course.Instructor;
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
		Assert.assertEquals("Soft constraint violation is incorrect", 100, violation);
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
		Assert.assertEquals("Soft constraint violation is incorrect", 0, violation);
	}
	
	@Test
	public void SoftCon2failtest() {
		Instructor instructor = new Instructor("John");
		instructor.addInstructedLecture(lecture1);
		lecture1.setInstructor(instructor);
		Time time = new Time.Builder("9:00").build();
		session1.setTime(time);
		session1.setDay("M");
		Room room = new Room("ST148");
		room.setCapacity(250);
		session1.setRoom(room);
		lecture1.setSession(session1);
		
		
		Lecture lecture2 = new Lecture("CPSC413", "L01");
		lecture2.setInstructor(instructor);
		instructor.addInstructedLecture(lecture2);
		Session session2 = new Session("S2");
		session2.setTime(time);
		session2.setDay("M");
		Room room2 = new Room("ST140");
		room2.setCapacity(250);
		session2.setRoom(room2);
		
		int violation = Constraints.calcSoftOne(session2, lecture2);
		Assert.assertEquals("Soft constraint violation is incorrect", 20, violation);
	}
	
	@Test
	public void SoftCon4failtest() {
		Student student = new Student("Bob");
		lecture1.setExamLength(3);
		session1.setLength(3);
		session1.setDay("M");
		lecture1.setSession(session1);
		lecture1.enrollStudent(student);
		student.enroll(lecture1);
		
		Lecture lecture2 = new Lecture("CPSC413", "L01");
		Session session2 = new Session("S2");
		session2.setLength(3);
		session2.setDay("M");
		lecture2.enrollStudent(student);
		
		int violation = Constraints.calcSoftFour(session2, lecture2);
		Assert.assertEquals("Soft constraint violation is incorrect", 50, violation);
	}
	
	@Test
	public void SoftCon4passtest() {
		Student student = new Student("Bob");
		lecture1.setExamLength(3);
		session1.setLength(3);
		session1.setDay("M");
		lecture1.setSession(session1);
		lecture1.enrollStudent(student);
		student.enroll(lecture1);
		
		Lecture lecture2 = new Lecture("CPSC413", "L01");
		Session session2 = new Session("S2");
		session2.setLength(2);
		session2.setDay("M");
		lecture2.enrollStudent(student);
		
		int violation = Constraints.calcSoftFour(session2, lecture2);
		Assert.assertEquals("Soft constraint violation is incorrect", 0, violation); 
	}
	
	@Test
	public void SoftCon7failtest() {
		session1.setLength(3);
		lecture1.setExamLength(2);	
		int violation = Constraints.calcSoftSeven(session1, lecture1);
		Assert.assertEquals("Soft constraint violation is incorrect", 5, violation);
	}
	
	@Test
	public void SoftCon7passtest() {
		session1.setLength(3);
		lecture1.setExamLength(3);	
		int violation = Constraints.calcSoftSeven(session1, lecture1);
		Assert.assertEquals("Soft constraint violation is incorrect", 0, violation);
	}
	
	//Assignment assignment2 = new Assignment();
}
