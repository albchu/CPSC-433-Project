package main.examSchedule.exceptions;

@SuppressWarnings("serial")
public class SessionAssignmentExceedsTimeException extends RuntimeException
{
	public SessionAssignmentExceedsTimeException(String message)
	{
		super(message);
	}
	public SessionAssignmentExceedsTimeException()
	{
		super();
	}
}
