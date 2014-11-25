package examSchedule.assignment;

import java.util.List;

public class SolutionPenaltyPair implements Comparable<SolutionPenaltyPair>{
	
	private Integer penalty;
	private List<String> solution;
	
	public SolutionPenaltyPair(List<String> solution, int penalty){
		this.solution = solution;
		this.penalty = penalty;
	}
	
	public Integer getPenalty(){
		return penalty;
	}

	public List<String> getSolution(){
		return solution;
	}

	@Override
	public int compareTo(SolutionPenaltyPair o){
		return this.penalty.compareTo(o.penalty);
	}
}


