package main.examSchedule.exceptions;

@SuppressWarnings("serial")
public class DuplicateRoomException extends RuntimeException
{
	public DuplicateRoomException(String message)
	{
		super(message);
	}
	public DuplicateRoomException()
	{
		super();
	}
}
