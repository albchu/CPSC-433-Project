package main.examSchedule.common;

import main.examSchedule.exceptions.NullParameterException;

public class Utilities
{
	public static boolean isNull(Object obj)
	{
		return (obj == null);
	}
	
	/**
	 * Verifies that element in parameter is not null
	 * @param obj
	 */
	public static void nullCheck(Object obj)
	{
		if (isNull(obj)) throw new NullParameterException();
	}
	/**
	 * Verifies that element in parameter is not null
	 * @param obj
	 */
	public static void nullCheck(Object... objs)
	{
		for (Object obj : objs)
			nullCheck(obj);
	}
}
