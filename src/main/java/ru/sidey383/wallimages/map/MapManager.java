package ru.sidey383.wallimages.map;

import java.util.List;
import java.util.UUID;

import ru.sidey383.wallimages.Callback;
import ru.sidey383.wallimages.image.ImagePreparator;
import ru.sidey383.wallimages.map.db.MapsImage;

public interface MapManager {

	public MapsImage createMap(ImagePreparator preparator, UUID owner, String Name);

	public void createMapAsynch(Callback<MapsImage> callback ,ImagePreparator preparator, UUID owner, String Name);
	
	public MapsImage loadImageByLocalMapID(int localMapID);

	public MapsImage loadImageByImageID(int imageID);

	public MapsImage loadImageByLocalMapIDAcynch(Callback<MapsImage> callback, int localMapID);

	public MapsImage loadImageByImageIDAsynch(Callback<MapsImage> callback, int imageID);
	
	public List<MapsImage> loadImagesByOwner(UUID owner);
	
	public void loadImagesByOwnerAsynch(Callback<List<MapsImage>> callback, UUID owner);
	
	public List<MapsImage> loadImagesByOwner(UUID owner, int pageSize, int pageNumber);
	
	public void loadImagesByOwnerAsynch(Callback<List<MapsImage>> callback, UUID owner, int pageSize, int pageNumber);
	
}
