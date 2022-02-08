package ru.sidey383.wallimages;

import java.net.URL;

import javax.imageio.ImageIO;

import org.bukkit.plugin.java.JavaPlugin;

import ru.sidey383.imageweb.ImageWebServer;
import ru.sidey383.wallimages.map.MapManager;

import java.awt.image.BufferedImage;

public class WallImages extends JavaPlugin{
	
	private static MapManager mapManager;
	
	private static WallImages instance;

	private static ImageWebServer webServer;

	@Override
	public void onEnable() {
		try {
			webServer = new ImageWebServer(80, 0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		instance = this;
	}

	@Override
	public void onDisable() {
		try{
			if(webServer != null)
				webServer.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage[] getImages() {
		BufferedImage[] images = new BufferedImage[6];
		try {
			images[0] = ImageIO.read(new URL("https://sun9-73.userapi.com/impg/bSGujshdUzXv7z2f8fadVRtCXpxLKYQnF8cTyA/kTNOeOSEWkk.jpg?size=1280x720&quality=96&sign=b6216211c1fe3cd91e0bb1b5f7086875&type=album"));
			images[1] = ImageIO.read(new URL("https://sun9-72.userapi.com/impg/PYVxWU8udSsXUIqo7ZQlpLwmmmPZ-fZKiTFgWA/WQ4wTfbbmaY.jpg?size=954x955&quality=95&sign=ec6f026fd705d75627c387e8e2d30dfd&type=album"));
			images[2] = ImageIO.read(new URL("https://sun9-16.userapi.com/impg/BVk1JPkvxiPhixM09rWEFLrbLlonXdnoxOXa8g/Y0JO_CqAQ0g.jpg?size=1280x720&quality=96&sign=19ba588d4de8e3edb29afc3fdfb2aee7&type=album"));
			images[3] = ImageIO.read(new URL("https://sun9-24.userapi.com/impg/goJ2-cHjVLklJdBr7XdWIs6ay71X88ey8kk0ww/TMUekR2iCaQ.jpg?size=300x275&quality=96&sign=d74b6eacf1e1f5f0606782dcadbef80d&type=album"));
			images[4] = ImageIO.read(new URL("https://sun9-7.userapi.com/impg/5JO-5kYLktBsDfCyHmWKG-CQXuXQFHxEt5jA3w/v3Rz61ZhX2s.jpg?size=498x398&quality=96&sign=fff26f574dbe50f10a45fa8209c9004a&type=album"));
			images[5] = ImageIO.read(new URL("https://sun9-44.userapi.com/impg/D_zfNIpwUm8ANeEgbwUvvRc2z236c08Ld-u3ug/jifI0lHCbPo.jpg?size=1080x1080&quality=95&sign=f484149a6194b2e5a62a6ea5c4d37970&type=album"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return images;
	}
	
	
	public BufferedImage getImage() {
		try {
			return ImageIO.read(new URL("https://sun9-44.userapi.com/impg/D_zfNIpwUm8ANeEgbwUvvRc2z236c08Ld-u3ug/jifI0lHCbPo.jpg?size=1080x1080&quality=95&sign=f484149a6194b2e5a62a6ea5c4d37970&type=album"));
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

	public static ImageWebServer getWebServer() {
		return webServer;
	}
	
}
