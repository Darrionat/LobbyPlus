package me.Darrionat.LobbyPlus.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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
		Location borderleave = new Location(world, plugin.getConfig().getInt("BorderTpX"),
				plugin.getConfig().getInt("BorderTpY"), plugin.getConfig().getInt("BorderTpZ"),
				plugin.getConfig().getInt("BorderTpYaw"), plugin.getConfig().getInt("BorderTpPitch"));// 180 YAW 0 PITCH
		int x = e.getTo().getBlockX();
		int y = e.getTo().getBlockY();
		int z = e.getTo().getBlockZ();
		// Checks to see if player is in world with border
		String playerworld = p.getWorld().getName();
		if (plugin.getConfig().getBoolean("BordersEnabled") == true) {
			if (!(plugin.getConfig().getString("BorderWorld").equals(playerworld))) {
				return;
			}
			if (!p.hasPermission(plugin.getConfig().getString("BorderOverridePermission"))) {
				//Leaving X
				if (x > plugin.getConfig().getInt("BorderHighX") || x < plugin.getConfig().getInt("BorderLowX")) {
					p.teleport(borderleave);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("BorderAttemptLeaveMsg")));
				}
				//Leaving Y
				if (y > plugin.getConfig().getInt("BorderHighY") || y < plugin.getConfig().getInt("BorderLowY")) {
					p.teleport(borderleave);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("BorderAttemptLeaveMsg")));
				}
				//Leaving Z
				if (z > plugin.getConfig().getInt("BorderHighZ") || z < plugin.getConfig().getInt("BorderLowZ")) {
					p.teleport(borderleave);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("BorderAttemptLeaveMsg")));
				}
			}
		}
	}
}
