package main.examSchedule;

import java.util.*;

public class Predicate
{
	private String predicate;
	private List<String> arguments;
	
	/**
	 * Assumptions: line input is a valid predicate
	 * @param line
	 */
	public Predicate(String predicate, List<String> args)
	{
		this.predicate = predicate;
		this.arguments = args;
	}

	public String getPredicate()
	{
		return predicate;
	}

	public List<String> getArguments()
	{
		return arguments;
	}
}
