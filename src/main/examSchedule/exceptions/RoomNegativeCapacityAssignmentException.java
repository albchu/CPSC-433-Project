package main.examSchedule.exceptions;

@SuppressWarnings("serial")
public class RoomNegativeCapacityAssignmentException extends RuntimeException
{
	public RoomNegativeCapacityAssignmentException(String message)
	{
		super(message);
	}
	public RoomNegativeCapacityAssignmentException()
	{
		super();
	}
}
