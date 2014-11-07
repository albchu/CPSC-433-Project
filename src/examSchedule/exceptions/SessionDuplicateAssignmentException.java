package examSchedule.exceptions;

@SuppressWarnings("serial")
public class SessionDuplicateAssignmentException extends RuntimeException
{
	public SessionDuplicateAssignmentException(String message)
	{
		super(message);
	}
	public SessionDuplicateAssignmentException()
	{
		super();
	}
}
