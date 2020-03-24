package me.Darrionat.LobbyPlus.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import me.Darrionat.LobbyPlus.Main;

public class MobSpawn implements Listener {

	private Main plugin;

	public MobSpawn(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}

	@EventHandler
	public void onFish(CreatureSpawnEvent e) {
		Location loc = e.getEntity().getLocation();
		LivingEntity type = e.getEntity();

		ConfigurationSection config = plugin.getConfig();
		if (config.getBoolean("BordersEnabled") == false) {
			return;
		}
		if (type instanceof ArmorStand) {
			return;
		}
		if (config.getBoolean("MobSpawningInBorders") == true) {
			return;
		}

		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		// If their x value is not less than their high and it's not greater than their
		// low.
		if (x > config.getInt("BorderHighX") || x < config.getInt("BorderLowX")) {
			return;
		}
		// Not in Y
		if (y > config.getInt("BorderHighY") || y < config.getInt("BorderLowY")) {
			return;
		}
		// Not in Z
		if (z > config.getInt("BorderHighZ") || z < config.getInt("BorderLowZ")) {
			return;
		}

		e.setCancelled(true);

	}
}
