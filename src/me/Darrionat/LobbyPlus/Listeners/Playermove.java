package me.Darrionat.LobbyPlus.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.Darrionat.LobbyPlus.Main;
import me.Darrionat.LobbyPlus.Utils;

public class Playermove implements Listener {

	private Main plugin;

	public Playermove(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		World world = p.getWorld();
		ConfigurationSection config = plugin.getConfig();
		double BorderTpX = config.getDouble("BorderTpX");
		double BorderTpY = config.getDouble("BorderTpY");
		double BorderTpZ = config.getDouble("BorderTpZ");
		int BorderTpYaw = config.getInt("BorderTpYaw");
		int BorderTpPitch = config.getInt("BorderTpPitch");
		if (config.getBoolean("BorderTpEnabled") == false) {
			return;
		}
		Location borderleave = new Location(world, BorderTpX, BorderTpY, BorderTpZ, BorderTpYaw, BorderTpPitch);
		int x = e.getTo().getBlockX();
		int y = e.getTo().getBlockY();
		int z = e.getTo().getBlockZ();
		// Checks to see if player is in world with border
		String playerworld = p.getWorld().getName();
		if (config.getBoolean("BordersEnabled") == true) {
			if (!(config.getString("BorderWorld").equals(playerworld))) {
				return;
			}
			if (!p.hasPermission(config.getString("BorderOverridePermission"))) {
				// Leaving X
				if (x > config.getInt("BorderHighX") || x < config.getInt("BorderLowX")) {
					p.teleport(borderleave);
					p.sendMessage(Utils.chat(config.getString("BorderAttemptLeaveMsg")));
				}
				// Leaving Y
				if (y > config.getInt("BorderHighY") || y < config.getInt("BorderLowY")) {
					p.teleport(borderleave);
					p.sendMessage(Utils.chat(config.getString("BorderAttemptLeaveMsg")));
				}
				// Leaving Z
				if (z > config.getInt("BorderHighZ") || z < config.getInt("BorderLowZ")) {
					p.teleport(borderleave);
					p.sendMessage(Utils.chat(config.getString("BorderAttemptLeaveMsg")));
				}
			}
		}
	}
}
