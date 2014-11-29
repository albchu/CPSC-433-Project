package examSchedule.parser;

import java.util.*;

public class Predicate
{
	private String predicate;
	private PredicateArgs arguments;
	
	/**
	 * Assumptions: line input is a valid predicate
	 * @param line
	 */
	public Predicate(String predicate, String args)
	{
		this.predicate = predicate;
		this.arguments = new PredicateArgs(args);
	}

	public String getPredicate()
	{
		return predicate;
	}

	public PredicateArgs getArguments()
	{
		return arguments;
	}
}
