package main.examSchedule;

import static main.examSchedule.common.Utilities.*;

import java.util.List;

public class ExamSchedule
{
	public static void main(String[] args)
	{
		// There has to be at least two arguments according to assignment spec page
		if (args.length != 2)
		{
			System.out.println("Synopsis: main.examSchedule <env-file> [<solution-file>|<time-in-ms>]");
			System.exit(0);
		}
		
		String filename = args[0];						// Problem specification input file of predicates as documented in the assignment spec 
		int maxTime = Integer.parseInt(args[1]);		// Time in milliseconds specifying the time limit for which the program is not allowed to exceed
		
		List<String> inputList = readToList(filename);
		
		Environment env = new Environment();
		PredicateParser.importList(env, inputList);
		
		List<String> outputList = env.exportList();
		writeToFile(outputList, filename + ".out");
	}
}
