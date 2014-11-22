package examSchedule.assignment;

import examSchedule.course.Lecture;

public class Assignment
{
	private Lecture lecture;
	private Session session;
	
	public Assignment(Session session, Lecture lecture)
	{
		this.lecture = lecture;
		this.session = session;
	}
	
	public Lecture getLecture()
	{
		return lecture;
	}
	
	public Session getSession()
	{
		return session;
	}
}
