package main.examSchedule.exceptions;

@SuppressWarnings("serial")
public class UnexpectedPredicateArgumentsException extends RuntimeException
{
	public UnexpectedPredicateArgumentsException(String message)
	{
		super(message);
	}
	public UnexpectedPredicateArgumentsException()
	{
		super();
	}
}
