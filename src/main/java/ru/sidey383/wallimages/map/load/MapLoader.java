package ru.sidey383.wallimages.map.load;

import java.util.HashMap;


public class MapLoader {
	
	private final static int dataSize = 128*128;
	
	private HashMap<Integer, Boolean> isLoaded = new HashMap<>();
	
	public boolean isLoaded(int mid) {
		return isLoaded.get(mid);
	}

	public byte[] loadMap(int id) {
		byte[] b = getMapBytes(id);
		if(b != null)
			setLoaded(id);
		return b;
	}

	private byte[] getMapBytes(int id) {
		//make reader
		return new byte[dataSize];
	}
	
	public boolean setMapBytes(int id, byte[] data) throws IllegalArgumentException {
		if(data == null)
			throw new IllegalArgumentException("data cant be null");
		if(data.length != dataSize)
			throw new IllegalArgumentException(String.format("the data should be of length %d, but have length %d", dataSize, data.length));
		//some realization
		setLoaded(id);
		return true;
	}
	
	private void setLoaded(int id) {
		isLoaded.put(id, true);
	}
	
}
