package ru.sidey383.wallimages.map.db;

import java.util.UUID;


public class MapsImage {

	private UUID owner;
	
	private Integer imageID;
	
	private String name;
	
	private byte[][][] data;
	
	private Integer[][] mapIDs;
	
	private Integer[][] localMapIDs;
	
	private int width;

	private int height;
	
	private MapsImage() {}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public byte[][][] getData() {
		return data;
	}

	public Integer[][] getMapIDs() {
		return mapIDs;
	}

	public Integer[][] getLocalMapIDs() {
		return localMapIDs;
	}

	public UUID getOwner() {
		return owner;
	}

	public Integer getImageID() {
		return imageID;
	}

	public String getName() {
		return name;
	}

	public class MapsImageBuilder {
		
		private MapsImage image = new MapsImage();
		
		private Integer x;
		private Integer y;
		
		public MapsImageBuilder setName(String name) {
			image.name = name;
			return this;
		}
		
		public MapsImageBuilder setImageID(int imageID) {
			image.imageID = imageID;
			return this;
		}
		
		public MapsImageBuilder setOwner(UUID owner) {
			image.owner = owner;
			return this;
		}
		
		public MapsImageBuilder setData(byte[][][] data) throws IllegalArgumentException {
			if(data == null) 
				throw new IllegalArgumentException("data cant be null");
			int x = data.length;
			if(x == 0) 
				throw new IllegalArgumentException("data cant be empty");
			int y = data[0].length;
			for(int i = 1; i < data.length; i++) 
				if(y != data[i].length) 
					throw new IllegalArgumentException("incorrect data sizes");
			size(x, y);
			return this;
		}
		
		public MapsImageBuilder setMapIDs(int[][][] mapIDs) throws IllegalArgumentException {
			if(mapIDs == null) 
				throw new IllegalArgumentException("data cant be null");
			int x = mapIDs.length;
			if(x == 0) 
				throw new IllegalArgumentException("data cant be empty");
			int y = mapIDs[0].length;
			for(int i = 1; i < mapIDs.length; i++) 
				if(y != mapIDs[i].length) 
					throw new IllegalArgumentException("incorrect data sizes");
			size(x, y);
			return this;
		}
		
		public MapsImageBuilder setLocalMapIDs(int[][][] locaMapIDs) throws IllegalArgumentException {
			if(locaMapIDs == null) 
				throw new IllegalArgumentException("data cant be null");
			int x = locaMapIDs.length;
			if(x == 0) 
				throw new IllegalArgumentException("data cant be empty");
			int y = locaMapIDs[0].length;
			for(int i = 1; i < locaMapIDs.length; i++) 
				if(y != locaMapIDs[i].length) 
					throw new IllegalArgumentException("incorrect data sizes");
			size(x, y);
			return this;
		}
		
		private void size(int x, int y) throws IllegalArgumentException{
			if(this.x == null && this.y == null) {
				this.x = x;
				this.y = y;
			}
			if(this.x != x)
				throw new IllegalArgumentException("mismatched size");
			if(this.y != y)
				throw new IllegalArgumentException("mismatched size");
		}
		
		public MapsImage build() throws IllegalStateException {
			if(image.imageID == null)
				throw new IllegalStateException("imageID can be null");
			if(image.data == null && image.localMapIDs == null && image.mapIDs == null)
				throw new IllegalStateException("must cotain some information about maps");
			if(x == null || y == null)
				throw new IllegalStateException("builder error");
			
			image.height = y;
			image.width = x;
			
			if(image.data == null)
				image.data = new byte[x][y][];
			if(image.localMapIDs == null)
				image.localMapIDs = new Integer[x][y];
			if(image.mapIDs == null)
				image.mapIDs= new Integer[x][y];
			return image;
		}
		
		public byte[][][] getData() {
			return image.data;
		}

		public Integer[][] getMapIDs() {
			return image.mapIDs;
		}

		public Integer[][] getLocalMapIDs() {
			return image.localMapIDs;
		}

		public UUID getOwner() {
			return image.owner;
		}

		public Integer getImageID() {
			return image.imageID;
		}

		public String getName() {
			return image.name;
		}
		
	}
	
}
