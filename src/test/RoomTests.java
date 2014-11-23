package test;

import org.junit.Test;

import examSchedule.assignment.Room;
import examSchedule.exceptions.RoomCapacityUnassignedException;
import examSchedule.exceptions.RoomNegativeCapacityAssignmentException;

public class RoomTests
{
	@Test (expected = RoomCapacityUnassignedException.class)
	public void unassignedCapacity()
	{
		Room room = new Room("RoomID");
		room.getCapacity();
	}

	@Test (expected = RoomNegativeCapacityAssignmentException.class)
	public void negativeCapacity()
	{
		Room room = new Room("RoomID");
		room.setCapacity(-1);
	}
}
