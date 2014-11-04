package main.examSchedule.date;

/**
 * Enum that contains all days of the week. Useful for immutable data
 * @author achu
 *
 */
public enum Day
{
	MONDAY("monday"),
	TUESDAY("tuesday"),
	WEDNESDAY("wednesday"),
	THURSDAY("thursday"),
	FRIDAY("friday"),
	SATURDAY("saturday"),
	SUNDAY("sunday");
	
	private String day;
	private Day(String day)
	{
		this.day = day;
	}
	
	public String getString()
	{
		return this.day;
	}
	
}
