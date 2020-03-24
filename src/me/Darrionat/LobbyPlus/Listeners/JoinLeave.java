package me.Darrionat.LobbyPlus.Listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Darrionat.LobbyPlus.Main;

public class JoinLeave implements Listener {

	private Main plugin;

	public JoinLeave(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		ConfigurationSection config = plugin.getConfig();

		// Teleport
		if (plugin.getConfig().getBoolean("TeleportOnLogin") == true) {
			double x = config.getDouble("X");
			double y = config.getDouble("Y");
			double z = config.getDouble("Z");
			int pitch = config.getInt("Pitch");
			int yaw = config.getInt("Yaw");
			Location loc = new Location(Bukkit.getWorld(config.getString("WorldOnLogin")), x, y, z, yaw, pitch);
			
			if (plugin.getConfig().getBoolean("TeleportPermissionNeeded") == false) {
				p.teleport(loc);
			}
			if (p.hasPermission(plugin.getConfig().getString("TeleportOnLoginPermission"))) {
				p.teleport(loc);
			}
		}
		// Speed
		if (plugin.getConfig().getBoolean("SpeedOnLogin") == true) {
			if (plugin.getConfig().getBoolean("SpeedPermissionNeeded") == false) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
						plugin.getConfig().getInt("SpeedLength") * 20, plugin.getConfig().getInt("SpeedLevel") - 1),
						true);
			}
			if (p.hasPermission(plugin.getConfig().getString("SpeedOnLoginPermission"))) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
						plugin.getConfig().getInt("SpeedLength") * 20, plugin.getConfig().getInt("SpeedLevel") - 1),
						true);
			}
		}
		// Saturation and Nightvision
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "Saturation");
		map.put(2, "NightVision");
		for (int i = 1; i <= 2; i = i + 1) {
			if (plugin.getConfig().getBoolean(map.get(i) + "OnLogin") == true) {
				if (plugin.getConfig().getBoolean(map.get(i) + "PermissionNeeded") == false) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,
							plugin.getConfig().getInt(map.get(i) + "Length") * 20, 0), true);
				}
				if (p.hasPermission(plugin.getConfig().getString(map.get(i) + "LoginPermission"))) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,
							plugin.getConfig().getInt(map.get(i) + "Length") * 20, 0), true);
				}
			}
		}
		// Fly
		if (plugin.getConfig().getBoolean("FlyOnLogin") == true) {
			if (plugin.getConfig().getBoolean("FlyPermissionNeeded") == false) {
				p.setAllowFlight(true);
			}
			if (p.hasPermission(plugin.getConfig().getString("FlyOnLoginPermission"))) {
				p.setAllowFlight(true);
			}
		}
		// Commands
		if (plugin.getConfig().getBoolean("RunCommandsOnLogin") == true) {
			if (plugin.getConfig().getBoolean("RunCommandsPermissionNeeded") == false) {
				for (String remove : plugin.getConfig().getStringList("commandsonjoin")) {
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
							remove.replace("%player%", p.getName()));
				}
			} else {
				if (p.hasPermission(plugin.getConfig().getString("RunCommandsOnLoginPermission"))) {
					for (String commands : plugin.getConfig().getStringList("commandsonjoin")) {
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
								commands.replace("%player%", p.getName()));
					}
				}
			}
		}

	}
}