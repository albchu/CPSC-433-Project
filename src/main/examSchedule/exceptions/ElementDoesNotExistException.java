package main.examSchedule.exceptions;

@SuppressWarnings("serial")
public class ElementDoesNotExistException extends RuntimeException
{
	public ElementDoesNotExistException(String message)
	{
		super(message);
	}
	public ElementDoesNotExistException()
	{
		super();
	}
}
