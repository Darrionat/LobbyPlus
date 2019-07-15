package me.Darrionat.LobbyPlus.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.Darrionat.LobbyPlus.Main;
import me.Darrionat.LobbyPlus.Utils;

public class Block implements Listener {

	private Main plugin;

	public Block(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (plugin.getConfig().getBoolean("BordersEnabled") == false) {
			return;
		}
		if (plugin.getConfig().getBoolean("AllowBreakBlocks") == true) {
			return;
		}
		Player p = e.getPlayer();
		if (p.hasPermission(plugin.getConfig().getString("BorderProtectionOverridePermission"))) {
			if (e.getBlock().getType() == Material.CHEST) {
				return;
			}
			if (e.getBlock().getType() == Material.ENDER_CHEST) {
				return;
			}
			return;
		}
		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
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
		e.setCancelled(true);
		p.sendMessage(Utils.chat(plugin.getConfig().getString("AttemptBreakBlockMsg")));
		return;
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (plugin.getConfig().getBoolean("BordersEnabled") == false) {
			return;
		}
		if (plugin.getConfig().getBoolean("AllowPlaceBlocks") == true) {
			return;
		}
		Player p = e.getPlayer();
		if (p.hasPermission(plugin.getConfig().getString("BorderProtectionOverridePermission"))) {
			return;
		}
		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
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
		e.setCancelled(true);
		p.sendMessage(Utils.chat(plugin.getConfig().getString("AttemptPlaceBlockMsg")));
		return;
	}

	@EventHandler
	public void onChestInteract(PlayerInteractEvent e) {
		if (e.getMaterial()== Material.AIR) {
			return;
		}
		if (plugin.getConfig().getBoolean("BordersEnabled") == false) {
			return;
		}
		if (plugin.getConfig().getBoolean("AllowOpenChests") == true) {
			return;
		}
		Player p = e.getPlayer();
		if (p.hasPermission(plugin.getConfig().getString("BorderProtectionOverridePermission"))) {
			return;
		}
		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
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
		Material block = e.getClickedBlock().getType();
		if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			return;
		}
		if (block == Material.CHEST) {
			e.setCancelled(true);
			p.sendMessage(Utils.chat(plugin.getConfig().getString("AttemptOpenChestMsg")));
			return;
		}
		if (block == Material.ENDER_CHEST) {
			e.setCancelled(true);
			p.sendMessage(Utils.chat(plugin.getConfig().getString("AttemptOpenChestMsg")));
			return;
		}
	}

	@EventHandler
	public void onItemFrameInteract(PlayerInteractEvent e) {
		if (e.getMaterial()== Material.AIR) {
			return;
		}
		if (plugin.getConfig().getBoolean("BordersEnabled") == false) {
			return;
		}
		if (plugin.getConfig().getBoolean("AllowClickItemFrames") == true) {
			return;
		}
		Player p = e.getPlayer();
		if (p.hasPermission(plugin.getConfig().getString("BorderProtectionOverridePermission"))) {
			return;
		}
		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
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
		Material block = e.getClickedBlock().getType();
		if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			return;
		}
		if (block == Material.ITEM_FRAME) {
			e.setCancelled(true);
			p.sendMessage(Utils.chat(plugin.getConfig().getString("AttemptItemFrameClickMsg")));
			return;
		}
	}
}
