package ru.sidey383.wallimages.map.render;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public abstract class OneTimeMapRenderer extends MapRenderer {

	private boolean isRendered = false;
	
	public OneTimeMapRenderer() {}

	@Override
	public void render(MapView mapv, MapCanvas mapc, Player p) {
		
		if(!isRendered)
			new Thread(()->{
				isRendered = true;
				firstRender(mapv, mapc, p);
			}).start();
	}
	public abstract void firstRender(MapView mapv, MapCanvas mapc, Player p);
		
	
}
