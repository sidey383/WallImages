package ru.sidey383.wallimages.image;

import java.awt.Image;

public interface ImagePreparator {

	public static final int height = 128;
	
	public static final int width = 128;
	
	public ImagePreparator setImage(Image image);
	
	public byte[][][] getImages();
	
}
