package main.examSchedule.assignment;

import java.util.HashMap;

import main.examSchedule.exceptions.DuplicateRoomException;
import main.examSchedule.exceptions.ElementDoesNotExistException;


public class RoomMap
{
	private HashMap<String, Room> roomMap;
	
	public RoomMap()
	{
		roomMap = new HashMap<String, Room>();
	}
	
	public Room getRoom(String roomID)
	{
		if(!roomMap.containsKey(roomID)) throw new ElementDoesNotExistException("Could not find room in room map");
		return roomMap.get(roomID);
	}
	
	public void updateRoomInfo(String roomID, Integer capacity)
	{
		getRoom(roomID).setCapacity(capacity);
	}
	
	public void addRoom(String roomID)
	{
		if(roomMap.containsKey(roomID)) throw new DuplicateRoomException("Room has already been added in room map");
		roomMap.put(roomID, new Room(roomID));
	}
	
	public HashMap<String, Room> getRoomMap()
	{
		return roomMap;
	}
}
