package ru.sidey383.wallimages.map.db;

import java.util.List;
import java.util.UUID;

import ru.sidey383.wallimages.map.db.MapsImage.MapsImageBuilder;

public interface SharedDatabase {
	
	public ImageData getImageData(int imageID);
	
	public List<ImageData> getImagesData(UUID owner);
	
	public List<ImageData> getImagesData(UUID owner, int pageSize, int pageNumber);
	
	public List<MapPosition> getMapPositions(int imageID);
	
	public MapPosition getMapPositionByMapID(int mapID);
	
	public MapData getMapData(int mapID);
		
	public boolean setImageData(MapsImageBuilder image) throws IllegalArgumentException;
	
	public boolean removeImage(int imageID) throws IllegalArgumentException;
	
	public class ImageData {
		
		public int imageID;  //primary key
		public UUID owner; //can be null
		public String name; // not null
	
	}
	
	public class MapPosition {
		
		public int mapID; //primary key
		public int imageID; //not null
		public int x; //not null
		public int y; //not null
		
	}
	
	public class MapData {
		
		public int mapID; //primary key
		public byte[] data; //not null
	
	}
	
	
	
}
