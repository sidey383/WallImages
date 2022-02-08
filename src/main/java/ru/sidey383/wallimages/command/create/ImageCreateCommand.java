package ru.sidey383.wallimages.command.create;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ImageCreateCommand implements CommandExecutor {

	public static String onSuccess;
	public static String onFail;
	private final JavaPlugin plugin;
	
	public ImageCreateCommand(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		if(args.length == 0)
			return false;
		ImageCreateResponce iresp = new ImageCreateResponce(args[0]);
		for(int i = 1; i < args.length; i++) {
			switch(args[i]) {
			case "-size":
				if(i+2 < args.length) 
					return false;
				try {
					int heigth = Integer.parseInt(args[++i]);
					int width = Integer.parseInt(args[++i]);
					iresp.setSize(width, heigth);
				}catch (Exception e) {
					return false;
				}
				break;
			case "-fullsize":
				iresp.setFullSize();
				break;
			case "-conversion":
				if(++i < args.length){
					ConversionMode mode = ConversionMode.getMode(args[i]);
					if(mode == null) return false;
					iresp.setConversionMode(mode);
				}else {
					return false;
				}
				break;
			default:
				return false;
			}
		}
		Player owner = null;
		if(sender instanceof Player)
			owner = (Player) sender;
		MapCreateResponce mresp = new MapCreateResponce(owner, iresp.buildPreparator(),
				() -> sender.sendMessage(onSuccess),
				() -> sender.sendMessage(onFail));
		Bukkit.getScheduler().runTaskAsynchronously(plugin, mresp );
		return true;
	}
	
	
	
}
