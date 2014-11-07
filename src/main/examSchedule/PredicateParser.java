package main.examSchedule;

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
		for (String line : predicateList)
		{
			if (!isPredicate(line)) // Skip all lines that are comments or not valid predicates
				continue;
			
			String predicateName = getPredicateName(line);
			List<String> predicateArgs = extractArguments(line);
			env.importPredicate(predicateName, predicateArgs);
			
		}
		System.out.println();
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
	
	public static List<String> extractArguments(String line)
	{
		Pattern pattern = Pattern.compile("\\((.*?)\\)");
		Matcher match = pattern.matcher(line);
		if(match.find())
		{
			String argsString = match.group().replaceAll("[()]", "");
			return Arrays.asList(argsString.split(","));
		}
		else throw new RuntimeException("Should have been a valid predicate line: " + line );
	}
}
