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
		List<Predicate> days = new ArrayList<Predicate>();
		List<Predicate> instructors = new ArrayList<Predicate>();
		List<Predicate> courses = new ArrayList<Predicate>();
		List<Predicate> lectures = new ArrayList<Predicate>();
		List<Predicate> students = new ArrayList<Predicate>();
		List<Predicate> sessions = new ArrayList<Predicate>();
		List<Predicate> rooms = new ArrayList<Predicate>();
		List<Predicate> remainingPredicates = new ArrayList<Predicate>();	// All the pairs that can all come after 
		
		for (String line : predicateList)
		{
			if (!isPredicate(line)) // Skip all lines that are comments or not valid predicates
				continue;
			
			String predicateName = getPredicateName(line).toLowerCase();
			List<String> predicateArgs = extractArguments(line);
			
			if(predicateName.equals("day"))
				days.add(new Predicate(predicateName, predicateArgs));
			else if(predicateName.equals("instructor"))
				instructors.add(new Predicate(predicateName, predicateArgs));
			else if(predicateName.equals("course"))
				courses.add(new Predicate(predicateName, predicateArgs));
			else if(predicateName.equals("lecture"))
				lectures.add(new Predicate(predicateName, predicateArgs));
			else if(predicateName.equals("student"))
				students.add(new Predicate(predicateName, predicateArgs));
			else if(predicateName.equals("session"))
				sessions.add(new Predicate(predicateName, predicateArgs));
			else if(predicateName.equals("room"))
				rooms.add(new Predicate(predicateName, predicateArgs));
			else
				remainingPredicates.add(new Predicate(predicateName, predicateArgs));
		}
		exportToEnv(env, days);
		exportToEnv(env, courses);
		exportToEnv(env, lectures);
		exportToEnv(env, instructors);
		exportToEnv(env, students);
		exportToEnv(env, sessions);
		exportToEnv(env, rooms);
		exportToEnv(env, remainingPredicates);
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
	
	public static List<String> extractArguments(String line)
	{
		Pattern pattern = Pattern.compile("\\((.*?)\\)");
		Matcher match = pattern.matcher(line);
		if(match.find())
		{
			String argsString = match.group().replaceAll("[(\\s)]", "");
			return Arrays.asList(argsString.split(","));
		}
		else throw new RuntimeException("Should have been a valid predicate line: " + line );
	}
}
