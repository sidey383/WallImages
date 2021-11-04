package ru.sidey383.wallimages.map.render;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapView;

import java.awt.image.BufferedImage;
import java.awt.Image;

public class MapResizeFullImageRender extends OneTimeMapRenderer {

	private final BufferedImage image;
	
	public MapResizeFullImageRender(Image image) {
		this.image = MapPalette.resizeImage(image);
	}
	
	@Override
	public void firstRender(MapView mapv, MapCanvas mapc, Player p) {
		mapc.drawImage(0, 0, image);
		System.out.println("render map "+mapv.getId());
		
	}

}
