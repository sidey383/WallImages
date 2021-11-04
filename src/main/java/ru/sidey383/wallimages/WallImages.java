package ru.sidey383.wallimages;

import java.net.URL;

import javax.imageio.ImageIO;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;

import ru.sidey383.wallimages.map.MapManager;
import ru.sidey383.wallimages.map.render.MapResizeFullImageRender;

import java.awt.image.BufferedImage;

public class WallImages extends JavaPlugin{
	
	private static MapManager mapManager;
	
	private static WallImages instance;
	
	@Override
	public void onEnable() {
			World w = Bukkit.getWorlds().get(0);
			ItemFrame frame = null;
			Location loc = new Location(w, 0, 100, 0);
			try {
				frame = (ItemFrame) w.spawnEntity(loc, EntityType.ITEM_FRAME);
			}catch (Exception e) {
				for (Entity ent : loc.getChunk().getEntities()) {
					if (ent.getType().equals(EntityType.ITEM_FRAME) && ent.getLocation().getBlock().equals(loc.getBlock())) {
						frame = (ItemFrame) ent;
					}
				}
			}
			if(frame != null) {
				@SuppressWarnings("deprecation")
				MapView map = Bukkit.getServer().getMap(383);
				if (map == null)
					map = Bukkit.createMap(w);
				map.setTrackingPosition(false);
				try {
					MapRenderer render = new MapResizeFullImageRender(getImage());
					map.addRenderer(render);
					ItemStack mapItem = new ItemStack(Material.FILLED_MAP);
					MapMeta meta = (MapMeta) mapItem.getItemMeta();
					if (meta != null) {
						meta.setMapView(map);
						mapItem.setItemMeta(meta);
					}
					frame.setItem(mapItem, false);
					frame.setInvulnerable(true);
					frame.setVisible(false);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			instance = this;
	}
	
	public BufferedImage[] getImages() {
		BufferedImage[] images = new BufferedImage[6];
		try {
			images[0] = ImageIO.read(new URL("https://sun9-12.userapi.com/impg/_D453eaybjLTsKPR7ZqP98PncvsCykeB-nhofg/5CsUAi7pcV0.jpg?size=1536x2048&quality=96&sign=77623e224a9a46742b7b67890a3478b1&type=album"));
			images[1] = ImageIO.read(new URL("https://sun9-76.userapi.com/impg/xFJgl7QnmcuEGAttYxdjgEeVbgpCkBm0RG6XYg/PsOAISIiyz0.jpg?size=1330x2048&quality=96&sign=d4aac7299b9bf590d796b6addd0cfb46&type=album"));
			images[2] = ImageIO.read(new URL("https://sun9-22.userapi.com/impg/btOQenLqzJ80tXS7yrSyVcx4PCUX0Zvz-X75Pg/Q68uaP9vHMo.jpg?size=1536x2048&quality=96&sign=cbad46f42c3284b14115d4d70bad3429&type=album"));
			images[3] = ImageIO.read(new URL("https://sun9-72.userapi.com/impg/hZCt2-Z_iD-BNEwAl1cv2tRz_rsVCUUHe4hc1Q/t4jaxHJNRMM.jpg?size=1364x2048&quality=96&sign=993f9f9640354217c7a5fb2192926fd4&type=album"));
			images[4] = ImageIO.read(new URL("https://sun9-85.userapi.com/impg/lmUsQuTJTrH0BupwOheOB5Kj9hPqH182R9T4UQ/BqRABpNQSKc.jpg?size=1404x2048&quality=96&sign=abb3af7985238242b7db88859add3bfa&type=album"));
			images[5] = ImageIO.read(new URL("https://sun9-25.userapi.com/impg/9xb7gRSzCqcmjprqtGGPJEhC0baa_mMa1TvjiA/5-SqOujv1Wc.jpg?size=1322x2048&quality=96&sign=f91a0b0e0813e58799618198c994c06c&type=album"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return images;
	}
	
	
	public BufferedImage getImage() {
		try {
			return ImageIO.read(new URL("https://sun9-12.userapi.com/impg/_D453eaybjLTsKPR7ZqP98PncvsCykeB-nhofg/5CsUAi7pcV0.jpg?size=1536x2048&quality=96&sign=77623e224a9a46742b7b67890a3478b1&type=album"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new BufferedImage(128, 128, 0);
	}
	
	public static MapManager getMapManager() {
		return WallImages.mapManager;
	}
	
	public static WallImages getInstance() {
		return instance;
	}
	
}
