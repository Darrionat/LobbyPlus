package me.Darrionat.LobbyPlus.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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
		// Teleport
		if (plugin.getConfig().getBoolean("TeleportOnLogin") == true) {
			Location loc = new Location(Bukkit.getWorld(plugin.getConfig().getString("WorldOnLogin")),
					plugin.getConfig().getInt("X"), plugin.getConfig().getInt("Y"), plugin.getConfig().getInt("Z"),
					plugin.getConfig().getInt("Yaw"), plugin.getConfig().getInt("Pitch"));// 180 YAW 0 PITCH
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
		// Saturation
		if (plugin.getConfig().getBoolean("SaturationOnLogin") == true) {
			if (plugin.getConfig().getBoolean("SaturationPermissionNeeded") == false) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,
						plugin.getConfig().getInt("SaturationLength") * 20, 0), true);
			}
			if (p.hasPermission(plugin.getConfig().getString("SaturationLoginPermission"))) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,
						plugin.getConfig().getInt("SaturationLength") * 20, 0), true);
			}
		}
		// NightVision
		if (plugin.getConfig().getBoolean("NightVisionOnLogin") == true) {
			if (plugin.getConfig().getBoolean("NightVisionPermissionNeeded") == false) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,
						plugin.getConfig().getInt("NightVisionLength") * 20, 0), true);
			}
			if (p.hasPermission(plugin.getConfig().getString("NightVisionLoginPermission"))) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,
						plugin.getConfig().getInt("NightVisionLength") * 20, 0), true);
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