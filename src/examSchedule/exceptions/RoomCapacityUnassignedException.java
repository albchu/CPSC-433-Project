package examSchedule.exceptions;

@SuppressWarnings("serial")
public class RoomCapacityUnassignedException extends RuntimeException
{
	public RoomCapacityUnassignedException(String message)
	{
		super(message);
	}
	public RoomCapacityUnassignedException()
	{
		super();
	}
}
