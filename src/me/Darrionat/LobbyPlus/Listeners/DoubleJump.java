package me.Darrionat.LobbyPlus.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import me.Darrionat.LobbyPlus.Main;

public class DoubleJump implements Listener {

	private Main plugin;

	public DoubleJump(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
		if (plugin.getConfig().getBoolean("DoubleJumpOnlyInBorders") == true) {
			// If their x value is not less than their high and it's not greater than their
			// low.
			if (x > plugin.getConfig().getInt("BorderHighX") || x < plugin.getConfig().getInt("BorderLowX")) {
				return;
			}
			// Not in Y
			if (y > plugin.getConfig().getInt("BorderHighY") || y < plugin.getConfig().getInt("BorderLowY")) {
				return;
			}
			// Not in Z
			if (z > plugin.getConfig().getInt("BorderHighZ") || z < plugin.getConfig().getInt("BorderLowZ")) {
				return;
			}
		}
		if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		if (plugin.getConfig().getBoolean("DoubleJumpEnabled") == false) {
			return;
		}
		if (plugin.getConfig().getBoolean("DoubleJumpPermissionNeeded") == true) {
			if (p.hasPermission(plugin.getConfig().getString("DoubleJumpPermission"))) {
				if (p.isOnGround()) {
					p.setAllowFlight(true);
				}
				return;
			}
		}
		p.setAllowFlight(false);
		p.setAllowFlight(true);
		return;

	}

	@EventHandler
	public void onPlayerToggleFly(PlayerToggleFlightEvent e) {
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		loc.setY(loc.getY() - 2);
		if (plugin.getConfig().getBoolean("DoubleJumpEnabled") == false) {
			return;
		}
		if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
		if (plugin.getConfig().getBoolean("DoubleJumpOnlyInBorders") == true) {
			// If their x value is not less than their high and it's not greater than their
			// low.
			if (x > plugin.getConfig().getInt("BorderHighX") || x < plugin.getConfig().getInt("BorderLowX")) {
				p.setAllowFlight(false);
				return;
			}
			// Not in Y
			if (y > plugin.getConfig().getInt("BorderHighY") || y < plugin.getConfig().getInt("BorderLowY")) {
				p.setAllowFlight(false);
				return;
			}
			// Not in Z
			if (z > plugin.getConfig().getInt("BorderHighZ") || z < plugin.getConfig().getInt("BorderLowZ")) {
				p.setAllowFlight(false);
				return;
			}
		}
		Material block = loc.getWorld().getBlockAt(loc).getType();

		int doublejumpmult = plugin.getConfig().getInt("DoubleJumpMultiplier");
		Vector v = p.getLocation().getDirection().multiply(doublejumpmult).setY(doublejumpmult);
		if (plugin.getConfig().getBoolean("DoubleJumpPermissionNeeded") == true) {
			if (p.hasPermission(plugin.getConfig().getString("DoubleJumpPermission"))) {
				if (block == Material.AIR) {
					e.setCancelled(true);
					return;
				}
				e.setCancelled(true);
				p.setFlying(false);
				p.setAllowFlight(false);
				p.setVelocity(v);
				return;
			}
		}
		if (block == Material.AIR) {
			e.setCancelled(true);
			return;
		}
		e.setCancelled(true);
		p.setFlying(false);
		p.setAllowFlight(false);
		p.setVelocity(v);
		return;

	}
}
