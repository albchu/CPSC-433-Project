package examSchedule.parser;

import java.util.List;

public class PredicatePair
{
	private String predicateName;
	private List<String> args;
	
	public PredicatePair(String predicatePair, List<String> args)
	{
		this.predicateName = predicateName;
		this.args = args;
	}

	public String getPredicateName()
	{
		return predicateName;
	}

	public List<String> getArgs()
	{
		return args;
	}
}
