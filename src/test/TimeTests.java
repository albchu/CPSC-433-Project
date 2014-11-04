package test;

import main.examSchedule.date.Time;

import org.junit.*;

import main.examSchedule.exceptions.InvalidTimeFormatException;

public class TimeTests
{
	private Time time;
	private String validTimeSmaller = "14:00";
	private String validTimeLarger = "16:00";
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
	public void compareSmallerTime()
	{
		time = new Time.Builder(validTimeSmaller).build();
		Time time2 = new Time.Builder(validTimeLarger).build();
		Assert.assertTrue("Invalid output format", time.compareTo(time2) < 0);
	}
	
	@Test
	public void compareLargerTime()
	{
		time = new Time.Builder(validTimeSmaller).build();
		Time time2 = new Time.Builder(validTimeLarger).build();
		Assert.assertTrue("Invalid output format", time2.compareTo(time) > 0);
	}
	
	@Test
	public void compareequalTime()
	{
		time = new Time.Builder(validTimeSmaller).build();
		Time time2 = new Time.Builder(validTimeSmaller).build();
		Assert.assertTrue("Invalid output format", time.compareTo(time2) == 0);
	}
	
	@Test
	public void buildValidTime()
	{
		time = new Time.Builder(validTimeSmaller).build();
		Assert.assertNotNull("Time object instantiates null", time);
		Assert.assertEquals("Invalid hour", validHour, time.getHour());
		Assert.assertEquals("Invalid minute", validMinute, time.getMinute());
		Assert.assertEquals("Invalid output format", validTimeSmaller, time.toString());
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
