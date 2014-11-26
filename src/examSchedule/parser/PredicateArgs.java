package examSchedule.parser;

import java.util.*;

import examSchedule.exceptions.UnexpectedPredicateArgumentsException;

/**
 * A wrapper class to house the imported predicate arguments. Allows a central place to do integer parsing, or house lists within the args
 * @author achu
 *
 */
public class PredicateArgs
{
	private List<Object> args;
	private int index;
	
	public PredicateArgs(String argsStr)
	{
		args = new ArrayList<Object>();
		importArgs(argsStr);
		index = 0;
	}
	
	/**
	 * Imports the string args into a parsable list of input
	 */
	public void importArgs(String argsStr)
	{
		String[] tokenized = argsStr.split(",");
//		boolean isVector = false;
		for(int i = 0; i < tokenized.length ; i++)
		{
			String curArg = tokenized[i];
			
			// Check that input is the start of a sublist
			if(curArg.contains("[") )
			{
				// If so assemble a new list of args up until the end of the vector array
				List<String> sublist = getSubList(tokenized, i);
				args.add(sublist);
				i += sublist.size();
			}
			else
				args.add(curArg);
			
//			//If end of vector, disable boolean and continue importing values
//			if (isVector && curArg.contains("]"))
		}
	}
	
	public List<Object> getArgs()
	{
		return args;
	}

	/**
	 * Takes in a tokenized string array and an index to start searching for a sub list 
	 * @param tokenized
	 * @param index
	 * @return
	 */
	public static List<String> getSubList(String[] tokenized, int index)
	{
		if(!tokenized[index].contains("[")) throw new UnexpectedPredicateArgumentsException("getSubList: index was not the start of a sublist");
		tokenized[index] = tokenized[index].replaceAll("\\[", "");
		List<String> subList = new ArrayList<String>();
		boolean subListEnd = false;
		while (!subListEnd )
		{
			if(!subListEnd && index >= tokenized.length) throw new UnexpectedPredicateArgumentsException("getSubList: Could not find closing bracket for sublist");
				
			if (tokenized[index].contains("]"))
			{
				subListEnd = true;
				tokenized[index] = tokenized[index].replaceAll("\\]", "");
			}
			subList.add(tokenized[index++]);			
		}
		return subList;
	}
	
	public Object getNext()
	{
		if (index >= args.size()) throw new RuntimeException("Next index out of bounds");
		return args.get(index++);
	}
	
	public String getNextString()
	{
		return (String) this.getNext();
	}
	
	public int size()
	{
		return args.size();
	}
	
	public List<String> getNextList()
	{
		return (List<String>) this.getNext();
	}
}
