package ru.sidey383.wallimages.map.render;

import java.awt.Image;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapView;

public class MapImageRedner extends OneTimeMapRenderer {

	private final Image image;
	
	public  MapImageRedner(Image image) {
		this.image = image;
	}
	
	@Override
	public void firstRender(MapView mapv, MapCanvas mapc, Player p) {
		mapc.drawImage(0, 0, image);
	}
	
}
