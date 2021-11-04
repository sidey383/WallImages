package ru.sidey383.wallimages.map.db;

public interface LocalImageDatabase {

	public LocalMapID getLocalMapByMapID(int mapID);
	
	public LocalMapID getLocalMapByServerMapID(int serverMapID);
	
	public boolean removeLocalMapByMapID(int mapID);
	
	public boolean removeLocalMapByServerMapID(int serverMapID);
	
	public boolean insertMapsImage(MapsImage image);
	
	public class LocalMapID {
		
		public int mapID; //primary key
		public int serverMapID; //not null
	
	}
	
}
