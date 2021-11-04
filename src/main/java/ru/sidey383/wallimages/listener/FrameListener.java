package ru.sidey383.wallimages.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class FrameListener implements Listener {
	
	@EventHandler
	public void onFrameEntityDamage(EntityDamageEvent e) {
		Entity ent = e.getEntity();
		if(ent != null &&	ent.getType().equals(EntityType.ITEM_FRAME)) {
			System.out.println("break item frame entity");
		}
	}
	
	@EventHandler
	public void onFrameClickEvent(PlayerInteractAtEntityEvent e) {
		Entity ent = e.getRightClicked();
		if(ent != null &&	ent.getType().equals(EntityType.ITEM_FRAME)) {
			System.out.println("plaeyer interact at frame by ");
		}
	}
	
	@EventHandler
	public void onFramePlaceEvent(HangingPlaceEvent e) {
		Entity ent = e.getEntity();
		if(ent != null &&	ent.getType().equals(EntityType.ITEM_FRAME)) {
			System.out.println("place item frame");
		}
	}
	
	@EventHandler
	public void onFramehangingEvent(HangingBreakEvent e) {
		Entity ent = e.getEntity();
		if(ent != null &&	ent.getType().equals(EntityType.ITEM_FRAME)) {
			System.out.println("death hanging item frame entity");
		}
	}
	
	@EventHandler
	public void onFramehangingEvent(HangingBreakByEntityEvent e) {
		Entity ent = e.getEntity();
		if(ent != null &&	ent.getType().equals(EntityType.ITEM_FRAME)) {
			System.out.println("death hanging item frame entity by entity");
		}
	}
	
}
