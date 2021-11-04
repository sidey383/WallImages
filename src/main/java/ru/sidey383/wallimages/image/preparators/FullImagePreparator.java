package ru.sidey383.wallimages.image.preparators;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import ru.sidey383.wallimages.image.ImagePreparator;

public class FullImagePreparator implements ImagePreparator{
	
	Image image;

	@Override
	public ImagePreparator setImage(Image image) {
		this.image = image;
		return this;
	}

	@Override
	public BufferedImage[][] getImages() {
		if(image == null) return null;
		
		int iwidth = image.getWidth(null);
		int iheight = image.getHeight(null);
		
		int ximage = iwidth / width;
		int yimage = iheight/ height;
		
		int xoffset = (iwidth%width) / 2;
		int yoffset = (iheight%width) / 2;
		System.out.println(xoffset);
		System.out.println(yoffset);
		if(xoffset > 0) ximage++;
		if(yoffset > 0) yimage++;
		
		BufferedImage[][] images = new BufferedImage[ximage][yimage];
		
		for(int x = 0; x < ximage; x++)
			for(int y = 0; y < yimage; y++) {
				images[x][y] = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
				Graphics2D gr = images[x][y].createGraphics();
				gr.drawImage(image, xoffset - x*width, yoffset - y*height, iwidth, iheight, new Color(0, 0, 0, 0), null);
			}
		return images;
	}
	
	
	
}
