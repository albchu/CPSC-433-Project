package examSchedule.assignment;

import examSchedule.course.Lecture;

public class Assignment
{
	private Lecture lecture;
	private Session session;
	private int backtrackIndex;
	private int penalty;
	
	public Assignment(Session session, Lecture lecture, int backTrackIndex, int penalty)
	{
		this.lecture = lecture;
		this.session = session;
		this.backtrackIndex = backTrackIndex;
		this.penalty = penalty;
	}
	
	public int getPenalty()
	{
		return penalty;
	}

	public void setPenalty(int penalty)
	{
		this.penalty = penalty;
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
