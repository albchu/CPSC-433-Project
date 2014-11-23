package examSchedule.assignment;

public class SessionWorthPair implements Comparable<SessionWorthPair>
{
	private Integer worth;
	private Session session;
	
	public SessionWorthPair(Session session, int worth)
	{
		this.session = session;
		this.worth = worth;
	}
	
	public Integer getWorth()
	{
		return worth;
	}

	public Session getSession()
	{
		return session;
	}

	@Override
	public int compareTo(SessionWorthPair o)
	{
		return this.worth.compareTo(o.worth);
	}

}
