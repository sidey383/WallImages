package ru.sidey383.wallimages.map.render;

import java.awt.Image;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapView;

public class MapImageRender extends OneTimeMapRenderer {

	private final byte[] data;
	private static final int dataSize = 128*128;

	public MapImageRender(byte[] data)  throws IllegalArgumentException {
		if(data.length != dataSize)
			throw new IllegalArgumentException("illegal array size");
		this.data = data;
	}
	
	@Override
	public void firstRender(MapView mapv, MapCanvas mapc, Player p) {
		for(int i = 0; i < 128; i++)
			for(int j = 0; j < 128; j++)
				mapc.setPixel(i, j, data[i+j*128]);
	}
	
}
