package ru.sidey383.wallimages.gui;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class GUIInventory implements InventoryHolder {
	
	protected Inventory inventory;
	protected InventoryHolder previousInventory;	
	
	@Override
	public Inventory getInventory() {
		return inventory;
	}
	
	public InventoryHolder getPreviousInventory() {
		return previousInventory;
	}
	
	public InventoryHolder toPreviousInventory() {
		if(inventory == null)
			return null;
		if(previousInventory == null) {
			inventory.getViewers().forEach(HumanEntity::closeInventory);
			return null;
		}
		
		inventory.getViewers().forEach((p) -> p.openInventory(previousInventory.getInventory()));
		
		return previousInventory;
	}
	
	public abstract void onClickEvent(InventoryClickEvent e);
	
}
