package examSchedule.assignment;

import examSchedule.course.Lecture;

public class Assignment
{
	private Lecture lecture;
	private Session session;
	private int backtrackIndex;
	
	public Assignment(Session session, Lecture lecture, int backTrackIndex)
	{
		this.lecture = lecture;
		this.session = session;
		this.backtrackIndex = backTrackIndex;
	}
	
	public int getBacktrackIndex()
	{
		return backtrackIndex;
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
