package examSchedule;

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
