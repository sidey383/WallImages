package ru.sidey383.wallimages.command.create;

import org.bukkit.entity.Player;

import ru.sidey383.wallimages.image.ImagePreparator;

public class MapCreateResponce implements Runnable {

	private Player owner;
	private ImagePreparator prepator;
	
	private Runnable onCreate;
	private Runnable onFail;
	
	public MapCreateResponce(Player owner, ImagePreparator preparator, Runnable onCreate, Runnable onFail) {
		this.owner = owner;
		this.prepator = preparator;
		this.onCreate = onCreate;
		this.onFail	 = onFail;
	}
	
	public void run() {
		
	}
}
