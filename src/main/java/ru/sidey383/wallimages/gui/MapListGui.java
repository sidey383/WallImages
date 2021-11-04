package ru.sidey383.wallimages.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ru.sidey383.wallimages.Callback;
import ru.sidey383.wallimages.WallImages;
import ru.sidey383.wallimages.map.MapManager;
import ru.sidey383.wallimages.map.db.MapsImage;

public class MapListGui extends GUIInventory {

	private static final int pageSize = 5*9; 
	private static final int inventorySize = 6*9;
	
	private static final int backPageItemSlot = inventorySize - 5;
	private static final int prevPageItemSlot = inventorySize - 9;
	private static final int nextPageItemSlot = inventorySize - 1;

	private static final Material backPageItemMaterial = Material.BARRIER;
	private static final Material prevPageItemMaterial = Material.ARROW;
	private static final Material nextPageItemMaterial = Material.ARROW;
	
	public static String backPageItemName = "back";
	public static String nextPageItemName = "next page";
	public static String prevPageItemName = "prev page";
	
	
	public static String paintingName = "Image: %name%";
	public static List<String> paintingLore = List.of("������ %width%x%height%", "����� ���� %mapCount%", "��������: %username%", "id: %id%");
	public static String noOwner = "no owner";
	
	public static String inventoryName = "mapList";
	
	private UUID mapOwner;
	private int page = 0;
	

	private final ArrayList<List<MapsImage>> maps = new ArrayList<>();
	
	public MapListGui(UUID mapOwner, InventoryHolder previousInventory) {
		inventory = Bukkit.createInventory(this, inventorySize, inventoryName);
		this.mapOwner = mapOwner;
		this.previousInventory = previousInventory;
	}
	
	public void nextPage() {
		page++;
		updateInventory();
	}
	
	public void prevPage() {
			page--;
			updateInventory();
	}
	
	public void setPage(int page) {
		this.page = page;
		updateInventory();
	}
	
	public void clickOnItem(MapsImage entry) {
	}
	
	/**
	 * @return false if cant create this page
	 * **/
	public void updateInventory() {
		MapManager mapManager = WallImages.getMapManager();
		if(mapManager != null) {
			ItemStack prevPage = new ItemStack(prevPageItemMaterial);
			ItemStack nextPage = new ItemStack(nextPageItemMaterial);
			ItemStack back = new ItemStack(backPageItemMaterial);
			ItemMeta meta = prevPage.getItemMeta();
			meta.setDisplayName(prevPageItemName);
			prevPage.setItemMeta(meta);
			meta = nextPage.getItemMeta();
			meta.setDisplayName(nextPageItemName);
			nextPage.setItemMeta(meta);
			meta = back.getItemMeta();
			meta.setDisplayName(backPageItemName);
			back.setItemMeta(meta);
			inventory.setItem(nextPageItemSlot, nextPage);
			inventory.setItem(prevPageItemSlot, prevPage);
			inventory.setItem(backPageItemSlot, back);
			int callbackPage = page;
			Callback<List<MapsImage>> callback = (e) -> {
				Bukkit.getScheduler().runTask(WallImages.getInstance(), ()->{
					maps.set(callbackPage, e);
					if(callbackPage != page) return;
					for(int i = 0; i < e.size(); i++) {
						inventory.setItem(i, buildMapItem(e.get(i)));
					}
				});

			};
			mapManager.loadImagesByOwnerAsynch(callback, mapOwner, pageSize, page);
		}
	}
	
	private ItemStack buildMapItem(MapsImage image) {
		ItemStack item = new ItemStack(Material.PAINTING);
		ItemMeta meta =	item.getItemMeta();
		meta.setDisplayName(formatString(paintingName, image, noOwner));
		meta.setLore(paintingLore.stream().map((e) -> {return formatString(e, image, noOwner);}).collect(Collectors.toList()));
		item.setItemMeta(meta);
		return item;
	}
	
	


	@Override
	public void onClickEvent(InventoryClickEvent e) {
		e.setCancelled(true);
		int slot = e.getSlot();
		
		if(slot == prevPageItemSlot) {
			prevPage();
			return;
		}
		
		if(slot == nextPageItemSlot) {
			nextPage();
			return;
		}
		
		if(slot == backPageItemSlot) {
			toPreviousInventory();
			return;
		}
		
		if(maps != null && page < maps.size() && slot < maps.get(page).size()) {
			clickOnItem(maps.get(page).get(slot));
			return;
		}
	}
	
	public static String formatString(String str, MapsImage entry, String noOwner) {
		if(entry == null || str == null)
			return str == null? "": null;
		OfflinePlayer player = Bukkit.getOfflinePlayer(entry.getOwner());
		if(player != null && player.getName() != null)
			str = str.replace("%username%", player.getName());
		else
			str = str.replace("%username%", noOwner);
		return str.replace("%name%", entry.getName()).replace("%width%", Integer.toString(entry.getWidth())).replace("%height%", Integer.toString(entry.getHeight())).replace("%mapCount%", Integer.toString(entry.getHeight()*entry.getWidth()));
	}

}
