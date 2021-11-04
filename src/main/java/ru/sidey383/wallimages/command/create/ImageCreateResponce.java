package ru.sidey383.wallimages.command.create;


import ru.sidey383.wallimages.image.ImagePreparator;

public class ImageCreateResponce {

	private String url;
	
	public ImageCreateResponce(String url) {
		this.url = url;
	}
	
	public ImageCreateResponce setSize(int width, int height) {
		return this;
	}
	
	public ImageCreateResponce setFullSize() {
		return this;
	}
	
	public ImageCreateResponce setConversionMode(ConversionMode mode) {
		return this;
	}
	
	public ImagePreparator buildPreparator() {
		return null;
	}
	
}
