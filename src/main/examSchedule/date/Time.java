package main.examSchedule.date;

import main.examSchedule.exceptions.InvalidTimeFormatException;
import static main.examSchedule.common.Utilities.*;
/**
 * Takes a single string in its constructor. Breaks apart to hour and minute. Implement comparable such that we can organize times.
 * @author achu
 *
 */
public class Time implements Comparable<Time>
{
	public static class Builder
	{
		private int hour;
		private int minute;

		public Builder(String timeString)
		{
			nullCheck(timeString);
			String[] tokenized = timeString.split(":");
			if(tokenized.length != 2) throw new InvalidTimeFormatException("Time is expected in 24 hours format: 'HH:MM'. Actual input was: '" + timeString + "'");
			try
			{
				hour = Integer.parseInt(tokenized[0]);
				minute = Integer.parseInt(tokenized[1]);
			}
			catch(NumberFormatException e)
			{
				throw new InvalidTimeFormatException("Time was given an unanticipated value: '" + timeString + "'");
			}
		}
		
		public Time build()
		{
			return new Time(this.hour, this.minute);
		}
	}
	
	private int hour;
	private int minute;
	
	/**
	 * Constructor for time, should never be directly called, proper time object instantiation should utilize builder: "time = new Time.builder(validTime).build();"
	 * Do NOT change to public. This is intentional.
	 * @param hour
	 * @param minute
	 */
	private Time(int hour, int minute)
	{
		if (hour > 23 || minute > 60) throw new InvalidTimeFormatException("Expected time in 24 hour format. Instead, got: " + hour + " : " + minute);
		this.hour = hour;
		this.minute = minute;
	}

	public int getHour()
	{
		return hour;
	}

	public int getMinute()
	{
		return minute;
	}

	public Integer toInteger()
	{
		return Integer.parseInt(Integer.toString(hour) + Integer.toString(minute));
	}

	@Override
	public int compareTo(Time otherTime)
	{
		return this.toInteger().compareTo(otherTime.toInteger());
	}

	@Override
	public String toString()
	{
		return String.format("%02d", hour) + ":" + String.format("%02d", minute);
	}
}
