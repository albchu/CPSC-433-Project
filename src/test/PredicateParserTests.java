package test;

import java.util.List;

import org.junit.Test;

import examSchedule.parser.PredicateParser;
import junit.framework.Assert;

public class PredicateParserTests
{
	String comment = " // this is a comment";
	String invalidPredicate = "instructor(I00214328";
	String validPredicate = "instructor(I00214328)";
	String validPredicate2 = "enrolled(S01092916,AMAT460,L02)";
	
	@Test
	public void isCommentTest()
	{
		Assert.assertTrue("Is not expected result", PredicateParser.isComment(comment));
		Assert.assertFalse("Is not expected result", PredicateParser.isComment(invalidPredicate));
		Assert.assertFalse("Is not expected result", PredicateParser.isComment(validPredicate));
	}
	@Test
	public void isValidPredicateTest()
	{
		Assert.assertFalse("Is not expected result", PredicateParser.isValidPredicate(comment));
		Assert.assertFalse("Is not expected result", PredicateParser.isValidPredicate(invalidPredicate));
		Assert.assertTrue("Is not expected result", PredicateParser.isValidPredicate(validPredicate));
	}
	
	@Test
	public void extractArgumentsTest()
	{
		List<String> args = PredicateParser.extractArguments(validPredicate);
		Assert.assertEquals(1, args.size());
		
		args = PredicateParser.extractArguments(validPredicate2);
		Assert.assertEquals(3, args.size());
		Assert.assertEquals("S01092916", args.get(0));
		Assert.assertEquals("AMAT460", args.get(1));
		Assert.assertEquals("L02", args.get(2));
		
	}
}
