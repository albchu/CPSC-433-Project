package examSchedule.exceptions;

@SuppressWarnings("serial")
public class DuplicateSessionException extends RuntimeException
{
	public DuplicateSessionException(String message)
	{
		super(message);
	}
	public DuplicateSessionException()
	{
		super();
	}
}
