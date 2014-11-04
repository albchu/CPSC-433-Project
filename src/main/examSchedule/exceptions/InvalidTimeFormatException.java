package main.examSchedule.exceptions;

public class InvalidTimeFormatException extends RuntimeException
{
	public InvalidTimeFormatException(String message)
	{
		super(message);
	}
}
