package ru.sidey383.wallimages.image;

import java.awt.Image;

public interface ImagePreparator {

	static final int height = 128;
	
	static final int width = 128;
	
	public ImagePreparator setImage(Image image);
	
	public Image[][] getImages();
	
}
