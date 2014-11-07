package examSchedule.exceptions;

@SuppressWarnings("serial")
public class SessionAssignmentExceedsSizeException extends RuntimeException
{
	public SessionAssignmentExceedsSizeException(String message)
	{
		super(message);
	}
	public SessionAssignmentExceedsSizeException()
	{
		super();
	}
}
