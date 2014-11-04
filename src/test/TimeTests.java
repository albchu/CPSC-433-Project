package test;

import main.examSchedule.date.Time;

import org.junit.*;

import main.examSchedule.exceptions.InvalidTimeFormatException;

public class TimeTests
{
	private Time time;
	private String validTime = "14:00";
	private int validHour = 14;
	private int validMinute = 0;
	private String invalidTime = "24:00";
	private String invalidString = "I dont like big butts";
	
	
	@After
	public void tearDown()
	{
		time = null;
	}
	
	@Test
	public void buildValidTime()
	{
		time = new Time.Builder(validTime).build();
		Assert.assertNotNull("Time object instantiates null", time);
		Assert.assertEquals("Invalid hour", validHour, time.getHour());
		Assert.assertEquals("Invalid minute", validMinute, time.getMinute());
		Assert.assertEquals("Invalid output format", validTime, time.toString());
	}
	
	@Test(expected = InvalidTimeFormatException.class)
	public void buildInvalidTime()
	{
		time = new Time.Builder(invalidTime).build();
	}
	
	@Test(expected = InvalidTimeFormatException.class)
	public void buildInvalidString()
	{
		time = new Time.Builder(invalidString).build();
	}
	
	
	@Test(expected = InvalidTimeFormatException.class)
	public void buildEmptyString()
	{
		time = new Time.Builder("").build();
	}
}
