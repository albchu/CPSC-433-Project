package examSchedule.assignment;

import java.util.HashMap;

import examSchedule.exceptions.DuplicateRoomException;
import examSchedule.exceptions.ElementDoesNotExistException;


public class RoomMap
{
	private HashMap<String, Room> roomMap;
	
	public RoomMap()
	{
		roomMap = new HashMap<String, Room>();
	}
	
	public Room getRoom(String roomID)
	{
		if(!roomMap.containsKey(roomID)) //throw new ElementDoesNotExistException("Could not find room in room map");
			addRoom(roomID);
		return roomMap.get(roomID);
	}
	
	public void updateRoomInfo(String roomID, Integer capacity)
	{
		addRoom(roomID);
		getRoom(roomID).setCapacity(capacity);
	}
	
	public void addRoom(String roomID)
	{
		if(!roomMap.containsKey(roomID)) // Will only add a new room if none exists
			roomMap.put(roomID, new Room(roomID));
	}
	
	public HashMap<String, Room> getRoomMap()
	{
		return roomMap;
	}
}
