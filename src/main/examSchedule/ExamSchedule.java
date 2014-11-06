package main.examSchedule;

public class ExamSchedule
{
	public void main(String[] args)
	{
		if (args.length == 0)
		{
			System.out.println("Synopsis: main.examSchedule <env-file> [<solution-file>|<time-in-ms>]");
			System.exit(0);
		}
	}
}
