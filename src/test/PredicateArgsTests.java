package test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import examSchedule.exceptions.UnexpectedPredicateArgumentsException;
import examSchedule.parser.PredicateArgs;

public class PredicateArgsTests
{
	private String validArgsWithSub = "Bob,[CPSC433,L01,CPSC599.68,L01]";
	private String invalidArgsWithSub1 = "Bob,[CPSC433,L01,CPSC599.68,L01";
	private String invalidArgsWithSub2 = "Bob,CPSC433,L01,CPSC599.68,L01]";
	private String validArgs = "M1-08-G,GoldGym ,M1,8,3";
	private PredicateArgs predicateArgs;
	
	@Test (expected = UnexpectedPredicateArgumentsException.class)
	public void notSubListTest()
	{
		String[] tokenized = invalidArgsWithSub2.split(",");
		int index = 1;
		PredicateArgs.getSubList(tokenized, index);
	}
	
	@Test
	public void importArgsSubListTest()
	{
		PredicateArgs predicateArgs = new PredicateArgs(validArgsWithSub);
		Assert.assertEquals(2, predicateArgs.getArgs().size());
		Assert.assertEquals(String.class, predicateArgs.getNext().getClass());
		Assert.assertEquals(ArrayList.class, predicateArgs.getNext().getClass());
	}
	
	@Test
	public void importArgsSimpleTest()
	{
		PredicateArgs predicateArgs = new PredicateArgs(validArgs);
		Assert.assertEquals(5, predicateArgs.getArgs().size());
		Assert.assertEquals(String.class, predicateArgs.getNext().getClass());
		Assert.assertEquals(String.class, predicateArgs.getNext().getClass());
		Assert.assertEquals(String.class, predicateArgs.getNext().getClass());
		Assert.assertEquals(String.class, predicateArgs.getNext().getClass());
		Assert.assertEquals(String.class, predicateArgs.getNext().getClass());
	}
	
	@Test(expected = RuntimeException.class)
	public void importArgsSimpleGetTooManyTest()
	{
		PredicateArgs predicateArgs = new PredicateArgs(validArgs);
		Assert.assertEquals(5, predicateArgs.getArgs().size());
		Assert.assertEquals(String.class, predicateArgs.getNext().getClass());
		Assert.assertEquals(String.class, predicateArgs.getNext().getClass());
		Assert.assertEquals(String.class, predicateArgs.getNext().getClass());
		Assert.assertEquals(String.class, predicateArgs.getNext().getClass());
		Assert.assertEquals(String.class, predicateArgs.getNext().getClass());
		Assert.assertEquals(String.class, predicateArgs.getNext().getClass());
	}
	
	@Test
	public void subListTest()
	{
		String[] tokenized = validArgsWithSub.split(",");
		int index = 1;
		List<String> subList = PredicateArgs.getSubList(tokenized, index);
		Assert.assertEquals(4, subList.size());
	}
	
	@Test (expected = UnexpectedPredicateArgumentsException.class)
	public void unClosedubListTest()
	{
		String[] tokenized = invalidArgsWithSub1.split(",");
		int index = 1;
		List<String> subList = PredicateArgs.getSubList(tokenized, index);
	}
}
