package me.Darrionat.LobbyPlus.Listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Darrionat.LobbyPlus.Main;
import me.Darrionat.LobbyPlus.Utils;

public class RainbowArmorClick implements Listener {
	private Main plugin;

	public RainbowArmorClick(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		// Rainbow Helmet
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem() == null) {
			return;
		}
		ItemStack rainbowhelmet = new ItemStack(Material.LEATHER_HELMET, 1);
		ItemMeta rainbowhelmmeta = rainbowhelmet.getItemMeta();
		ArrayList<String> rainbowhelmlore = new ArrayList<String>();
		rainbowhelmmeta.setDisplayName(Utils.chat("&cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r")); // Rainbow Armor
		rainbowhelmmeta.setLore(rainbowhelmlore);
		rainbowhelmet.setItemMeta(rainbowhelmmeta);
		// Chestplate
		ItemStack rainbowchestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		ItemMeta rainbowchestmeta = rainbowchestplate.getItemMeta();
		ArrayList<String> rainbowchestlore = new ArrayList<String>();
		rainbowchestmeta.setDisplayName(Utils.chat("&cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r")); // Rainbow Armor
		rainbowchestmeta.setLore(rainbowchestlore);
		rainbowchestplate.setItemMeta(rainbowchestmeta);
		// Leggings
		ItemStack rainbowleggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		ItemMeta rainbowleggingsmeta = rainbowleggings.getItemMeta();
		ArrayList<String> rainbowleggingslore = new ArrayList<String>();
		rainbowleggingsmeta.setDisplayName(Utils.chat("&cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r")); // Rainbow Armor
		rainbowleggingsmeta.setLore(rainbowleggingslore);
		rainbowleggings.setItemMeta(rainbowleggingsmeta);
		// Boots
		ItemStack rainbowboots = new ItemStack(Material.LEATHER_BOOTS, 1);
		ItemMeta rainbowbootsmeta = rainbowboots.getItemMeta();
		ArrayList<String> rainbowbootslore = new ArrayList<String>();
		rainbowbootsmeta.setDisplayName(Utils.chat("&cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r")); // Rainbow Armor
		rainbowbootsmeta.setLore(rainbowbootslore);
		rainbowboots.setItemMeta(rainbowbootsmeta);
		
		if (e.getCurrentItem().getItemMeta() == null) {
			return;
		}
		if (p.getGameMode() == GameMode.CREATIVE) {
			return;
		}
		String clickname = e.getCurrentItem().getItemMeta().getDisplayName();
		if (clickname.equalsIgnoreCase(Utils.chat("&cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r"))) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		
		Item item = e.getItemDrop();
		ItemMeta itemmeta = item.getItemStack().getItemMeta();
		String name = itemmeta.getDisplayName();
		String rainbowarmorname = Utils.chat("&cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r");

		if (!name.equalsIgnoreCase(rainbowarmorname)) {
			return;
		}
		e.setCancelled(true);
	}
}