package main.examSchedule.exceptions;

@SuppressWarnings("serial")
public class NullParameterException extends RuntimeException
{
	public NullParameterException(String message)
	{
		super(message);
	}
	public NullParameterException()
	{
		super();
	}
}
