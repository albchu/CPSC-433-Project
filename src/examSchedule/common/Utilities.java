package examSchedule.common;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import examSchedule.exceptions.NullParameterException;

public class Utilities
{
	public static boolean isNull(Object obj)
	{
		return (obj == null);
	}
	
	/**
	 * Returns a string in the predicate form
	 * @param predicateName
	 * @param args
	 * @return
	 */
	public static String predicateForm(String predicateName, String... args)
	{
		return predicateName.toLowerCase() + "(" + join(args, ", ") + ")";
	}

	/**
	 * Verifies that element in parameter is not null
	 * 
	 * @param obj
	 */
	public static void nullCheck(Object obj)
	{
		if (isNull(obj))
			throw new NullParameterException();
	}

	/**
	 * Verifies that element in parameter is not null
	 * 
	 * @param obj
	 */
	public static void nullCheck(Object... objs)
	{
		for (Object obj : objs)
			nullCheck(obj);
	}

	/**
	 * Imports a file to a string list separated by newlines
	 * 
	 * @return
	 */
	public static List<String> readToList(String filename)
	{
		System.out.println("Reading file: " + filename);
		Scanner sc = null;
		try
		{
			sc = new Scanner(new File(filename));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		List<String> lines = new ArrayList<String>();
		while (sc.hasNextLine())
		{
			lines.add(sc.nextLine());
		}
		sc.close();

		return lines;
	}

	public static void writeToFile(List<String> fileContents, String filename)
	{
		System.out.println("Writing to file: " + filename);
		try
		{
			FileWriter writer = new FileWriter(filename);
			for (String str : fileContents)
				writer.write(str + "\n");
			writer.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static String join(String array[], String delimiter)
	{
		if (array.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		int i;
		for (i = 0; i < array.length - 1; i++)
			sb.append(array[i] + delimiter);
		return sb.toString() + array[i];
	}
}
