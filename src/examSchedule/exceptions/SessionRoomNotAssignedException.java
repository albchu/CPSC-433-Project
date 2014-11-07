package examSchedule.exceptions;

@SuppressWarnings("serial")
public class SessionRoomNotAssignedException extends RuntimeException
{
	public SessionRoomNotAssignedException(String message)
	{
		super(message);
	}
	public SessionRoomNotAssignedException()
	{
		super();
	}
}
