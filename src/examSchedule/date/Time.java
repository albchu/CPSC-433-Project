package examSchedule.date;

import examSchedule.exceptions.InvalidTimeFormatException;
import static examSchedule.common.Utilities.*;
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
			if(tokenized.length > 2 || tokenized.length < 1) throw new InvalidTimeFormatException("Time is expected in 24 hours format: 'HH:MM'. Actual input was: '" + timeString + "'");
			minute = 0; // It is not neccessary to give this value on input. This is the default value
			try
			{
				hour = Integer.parseInt(tokenized[0]);
				if(tokenized.length == 2)
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
	
	public String getHourStr()
	{
		return String.format("%02d", hour);
	}
	
	public String getMinuteStr()
	{
		return String.format("%02d", minute);
	}

	public double getDifference(Time secondTime)
	{
		return (double)(((this.getHour() - secondTime.getHour())*100 + ((this.getMinute()/60 - secondTime.getMinute()/0.6)))/100);
	}
	
	public Integer toInteger()
	{
		return Integer.parseInt(getHourStr() + getMinuteStr());
	}

	@Override
	public int compareTo(Time otherTime)
	{
		return this.toInteger().compareTo(otherTime.toInteger());
	}

	@Override
	public String toString()
	{
		return getHourStr() + ":" + getMinuteStr();
	}
}
