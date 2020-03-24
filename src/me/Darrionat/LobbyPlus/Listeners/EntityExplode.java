package me.Darrionat.LobbyPlus.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import me.Darrionat.LobbyPlus.Main;

public class EntityExplode implements Listener{
	
	private Main plugin;

	public EntityExplode(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {
		if (plugin.getConfig().getBoolean("BordersEnabled") == false) {
			return;
		}
		if (plugin.getConfig().getBoolean("ExplosionsInBorders") == true) {
			return;
		}
        event.setCancelled(true);
        }
}
