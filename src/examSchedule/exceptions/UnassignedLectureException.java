package examSchedule.exceptions;

@SuppressWarnings("serial")
public class UnassignedLectureException extends RuntimeException
{
	public UnassignedLectureException(String message)
	{
		super(message);
	}
	public UnassignedLectureException()
	{
		super();
	}
}
