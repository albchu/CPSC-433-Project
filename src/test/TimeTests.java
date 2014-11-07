package test;

import examSchedule.date.Time;

import org.junit.*;

import examSchedule.exceptions.InvalidTimeFormatException;
import examSchedule.exceptions.NullParameterException;

public class TimeTests
{
	private Time time;
	private String validTimeSmaller = "14:00";
	private String validTimeLarger = "16:00";
	private String validTimeLarger2 = "16:30";
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
	public void compareEqualTime()
	{
		time = new Time.Builder(validTimeSmaller).build();
		Time time2 = new Time.Builder(validTimeSmaller).build();
		Assert.assertTrue("Invalid output format", time.compareTo(time2) == 0);
	}
	
	@Test
	public void getDifferenceTest()
	{
		time = new Time.Builder(validTimeSmaller).build();
		Time time2 = new Time.Builder(validTimeLarger).build();
		Time time3 = new Time.Builder(validTimeLarger2).build();
		double difference = 2;
		double difference2 = -2;
		double difference3 = 0;
		double difference4 = 2.5;
		Assert.assertEquals("Invalid output format", difference, time2.getDifference(time), 0.01);
		Assert.assertEquals("Invalid output format", difference2, time.getDifference(time2), 0.01);
		Assert.assertEquals("Invalid output format", difference3, time.getDifference(time), 0.01);
		Assert.assertEquals("Invalid output format", difference4, time3.getDifference(time), 0.01);
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
	
	@Test(expected = NullParameterException.class)
	public void buildNullTime()
	{
		time = new Time.Builder(null).build();
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
