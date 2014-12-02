package examSchedule.parser;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PredicateParser
{
	/**
	 * Loads list of predicates from a list into an environment
	 * @param env
	 * @param predicateList
	 */
	public static void importList (Environment env, List<String> predicateList)
	{
		// Requires reordering input in order to ensure data objects are loaded correctly
		for (String line : predicateList)
		{
			if (!isPredicate(line)) // Skip all lines that are comments or not valid predicates
				continue;
			
			String predicateName = getPredicateName(line).toLowerCase();
			String predicateArgs = extractArguments(line);
			
			env.importPredicate(predicateName, new PredicateArgs(predicateArgs));
		}
	}

	public static void exportToEnv(Environment env, List<Predicate> list)
	{
		for(Predicate predicate : list)
			env.importPredicate(predicate.getPredicate(), predicate.getArguments());
	}
	
	public static String getPredicateName(String line)
	{
		return line.split("\\(")[0];
	}
	
	/**
	 * Determines if a line is a predicate
	 * 
	 * @param line
	 * @return
	 */
	public static boolean isPredicate(String line)
	{
		if (isComment(line) || !isValidPredicate(line))
			return false;
		else
			return true;
	}
	
	/**
	 * Determines if a line is a comment
	 * 
	 * @param line
	 * @return
	 */
	public static boolean isComment(String line)
	{
		Pattern commentPattern = Pattern.compile("^\\s*//");
		Matcher commentMatcher = commentPattern.matcher(line);
		
		if (commentMatcher.find())
			return true;
		else
			return false;
	}
	
	/**
	 * Determines if a line is a valid predicate
	 * 
	 * @param line
	 * @return
	 */
	public static boolean isValidPredicate(String line)
	{
		Pattern commentPattern = Pattern.compile("[^\\(]*(\\(.*\\))[^\\)]*");
		Matcher commentMatcher = commentPattern.matcher(line);
		
		if (commentMatcher.find())
			return true;
		else
			return false;
	}
	
	public static String extractArguments(String line)
	{
		Pattern pattern = Pattern.compile("\\((.*?)\\)");
		Matcher match = pattern.matcher(line);
		if(match.find())
		{
			String argsString = match.group().replaceAll("[(\\s)]", "");
			return argsString;
		}
		else throw new RuntimeException("Should have been a valid predicate line: " + line );
	}
}
