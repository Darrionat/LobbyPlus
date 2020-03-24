package me.Darrionat.LobbyPlus.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.Darrionat.LobbyPlus.Main;
import me.Darrionat.LobbyPlus.Utils;

public class PlayerDeath implements Listener {

	private Main plugin;

	public PlayerDeath(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		String rainbowarmor = Utils.chat("&cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r");
		if (p.getInventory().getHelmet() != null) {
			if (p.getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase(rainbowarmor)) {
				p.getInventory().setHelmet(null);
			}
		}
		if (p.getInventory().getChestplate() != null) {
			if (p.getInventory().getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase(rainbowarmor)) {
				p.getInventory().setChestplate(null);
			}
		}
		if (p.getInventory().getLeggings() != null) {
			if (p.getInventory().getLeggings().getItemMeta().getDisplayName().equalsIgnoreCase(rainbowarmor)) {
				p.getInventory().setLeggings(null);
			}
		}
		if (p.getInventory().getBoots() != null) {
			if (p.getInventory().getBoots().getItemMeta().getDisplayName().equalsIgnoreCase(rainbowarmor)) {
				p.getInventory().setBoots(null);
			}
		}

	}
}
