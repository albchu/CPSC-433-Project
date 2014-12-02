package examSchedule.parser;

import static examSchedule.common.Utilities.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import examSchedule.assignment.Session;
import examSchedule.solution.Solve;

public class ExamSchedule
{
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();
		
		// There has to be at least two arguments according to assignment spec page
		if (args.length != 2)
		{
			System.out.println("Synopsis: examSchedule <env-file> [<solution-file>|<time-in-ms>]");
			System.exit(0);
		}
		
		String filename = args[0];						// Problem specification input file of predicates as documented in the assignment spec 
		int maxTime = Integer.parseInt(args[1]);		// Time in milliseconds specifying the time limit for which the program is not allowed to exceed
		
		List<String> inputList = readToList(filename);
		Environment env = new Environment();
		PredicateParser.importList(env, inputList);
		Session session = env.getSessionMap().getSession("M11-2-SS171");
		long importEndTime = System.currentTimeMillis();
		System.out.println("Took " + (importEndTime - startTime) + " ms to import"); 
//		Solve sol = new Solve(env);
//		List<String> outputList = sol.generateSolution(maxTime, startTime);
//		
//		//List<String> outputList = env.exportList();
//		writeToFile(outputList, filename + ".out");
//		
//		long endTime = System.currentTimeMillis();
//		System.out.println("Took " + (endTime - startTime) + " ms"); 
	}
}
