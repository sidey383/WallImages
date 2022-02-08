package ru.sidey383.wallimages.gui;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ru.sidey383.wallimages.WallImages;
import ru.sidey383.wallimages.map.db.MapImageInfo;


public class MapGui extends GUIInventory {
	
	private static final int inventorySize = 3*5;
	
	private static final int backSlot = inventorySize-5;
	private static final int paintingSlot = inventorySize/2;
	
	private static final Material backMaterial = Material.BARRIER;
	private static final Material paintingMaterial = Material.MAP;
	
	public static String backItemName = "back";
	
	public static String paintingName = "Image: %name%";
	public static List<String> paintingLore = List.of("Size %width%x%height%", "Map total %mapCount%", "Owner %username%", "id: %id%");
	public static String noOwner = "no owner";
	
	public static String inventoryName = "mapList";
	
	private final MapImageInfo imageInfo;

	public MapGui(MapImageInfo image) {
		this.imageInfo = image;
		inventory = Bukkit.createInventory(this, inventorySize);
		updateInventory();
	}
	
	@Override
	public void onClickEvent(InventoryClickEvent e) {
		e.setCancelled(true);
		int slot = e.getSlot();
		
		if(slot == backSlot)
			toPreviousInventory();
		
		if(slot == paintingSlot)
			paintingClickAction(e);
	}
	
	public void updateInventory() {
		ItemStack item  = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		for(int i = 0; i < inventorySize; i++) {
			inventory.setItem(i, item);
		}
		
		ItemStack backItem = new ItemStack(backMaterial);
		ItemMeta meta = backItem.getItemMeta();
		if(meta == null)
			return;
		meta.setDisplayName(backItemName);
		backItem.setItemMeta(meta);
		inventory.setItem(backSlot, backItem);
		ItemStack painting = new ItemStack(paintingMaterial);
		meta = painting.getItemMeta();
		if(meta == null)
			return;
		meta.setDisplayName(formatString(paintingName, imageInfo, noOwner));
		meta.setLore(paintingLore == null ?
				null :
				paintingLore.stream()
						.map(
								e-> formatString(e, imageInfo, noOwner)
						)
						.collect(Collectors.toList()));
		painting.setItemMeta(meta);
		inventory.setItem(paintingSlot, painting);
	}
	
	private void paintingClickAction(InventoryClickEvent e) {
		HumanEntity pl = e.getWhoClicked();
		WallImages.getMapManager().giveMapToPlayer((Player) pl, imageInfo.getImageID());
	}
	
	
	public static String formatString(String str, MapImageInfo entry, String noOwner) {
		if(entry == null || str == null)
			return str == null? "": null;
		OfflinePlayer player;
		if(entry.getOwner() == null) {
			player = null;
		} else {
			player = Bukkit.getOfflinePlayer(entry.getOwner());
		}
		if(player != null && player.getName() != null)
			str = str.replace("%username%", player.getName());
		else
			str = str.replace("%username%", noOwner);
		return str.replace("%name%", entry.getName()).replace("%width%", Integer.toString(entry.getWidth())).replace("%height%", Integer.toString(entry.getHeight())).replace("%mapCount%", Integer.toString(entry.getHeight()*entry.getWidth()));
	}
}
