package ru.sidey383.wallimages.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {

	@EventHandler
	public void onClickEvent(InventoryClickEvent e) {
		Inventory inv = e.getWhoClicked().getOpenInventory().getTopInventory();
		if(inv.getHolder() instanceof GUIInventory) {
			GUIInventory gui = (GUIInventory) inv.getHolder();
			gui.onClickEvent(e);
		}
	}
	
}
