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
		getRoom(roomID).setCapacity(capacity);
	}
	
	public void addRoom(String roomID)
	{
		//if(roomMap.containsKey(roomID)) throw new DuplicateRoomException("Room has already been added in room map");
		roomMap.put(roomID, new Room(roomID));
	}
	
	public HashMap<String, Room> getRoomMap()
	{
		return roomMap;
	}
}
