package examSchedule.exceptions;

@SuppressWarnings("serial")
public class PredicateNotRecognizedException extends RuntimeException
{
	public PredicateNotRecognizedException(String message)
	{
		super(message);
	}
	public PredicateNotRecognizedException()
	{
		super();
	}
}
