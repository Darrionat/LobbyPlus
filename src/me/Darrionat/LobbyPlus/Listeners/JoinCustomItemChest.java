package me.Darrionat.LobbyPlus.Listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Darrionat.LobbyPlus.Main;
import me.Darrionat.LobbyPlus.Utils;
import me.Darrionat.LobbyPlus.UI.CustomItemsUI;

public class JoinCustomItemChest implements Listener {

	private Main plugin;

	public JoinCustomItemChest(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		// Make the item.
		// Chest or whatever they choose
		ItemStack item = new ItemStack(Material.getMaterial(plugin.getConfig().getString("GUIItemType")));
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(Utils.chat(plugin.getConfig().getString("GUIItemLore")));
		meta.setDisplayName(Utils.chat(plugin.getConfig().getString("GUIItemName")));
		meta.setLore(lore);
		item.setItemMeta(meta);
		// GrappleHook
		ItemStack grapplehook = new ItemStack(Material.FISHING_ROD, 1);
		ItemMeta grapplemeta = grapplehook.getItemMeta();
		ArrayList<String> grapplelore = new ArrayList<String>();
		grapplemeta.setDisplayName(Utils.chat("&eGrappling Hook"));
		grapplemeta.setLore(grapplelore);
		grapplehook.setItemMeta(grapplemeta);
		// Rainbow Helmet
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
		if (plugin.getConfig().getBoolean("GUIItemOnLogin") == false) {
			// Remove custom items from inventory.
			if (p.getInventory().contains(item)) {
				p.getInventory().remove(item);
			}
			if (p.getInventory().contains(grapplehook)) {
				p.getInventory().remove(grapplehook);
			}
			if (p.getInventory().contains(rainbowhelmet)) {
				p.getInventory().remove(rainbowhelmet);
			}
			if (p.getInventory().contains(rainbowchestplate)) {
				p.getInventory().remove(rainbowchestplate);
			}
			if (p.getInventory().contains(rainbowleggings)) {
				p.getInventory().remove(rainbowleggings);
			}
			if (p.getInventory().contains(rainbowboots)) {
				p.getInventory().remove(rainbowboots);
			}
			return;
		}
		// Useful variables
		int slot = p.getInventory().firstEmpty();
		int configslot = plugin.getConfig().getInt("GUIItemSlot") - 1;
		// If their inventory is full.
		if (p.getInventory().contains(item)) {
			return;
		}
		if (slot == -1) {
			System.out.println(Utils.chat("&c[" + plugin.getName() + "] " + p.getDisplayName()
					+ "'s inventory is too full to add your login item."));
			return;
		}
		if (p.getInventory().getItem(configslot) == null) {
			p.getInventory().setItem(configslot, item);
			return;
		}
		// If player already has item
		if (p.getInventory().getItem(configslot).getType() == item.getType()) {
			return;
		}

		// If it's air, make the item there.

		if (p.getInventory().getItem(configslot).getType() == Material.AIR) {
			p.getInventory().setItem(configslot, item);
			return;
		}

		// Doesn't have an item, inventory isn't full, and it's not air, add to
		// inventory.
		System.out.println(
				Utils.chat("&cThat slot is already taken for that player, putting item in first open slot..."));
		p.getInventory().setItem(slot, item);
		return;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem() == null) {
			return;
		}
		ItemMeta clickeditem = e.getCurrentItem().getItemMeta();
		if (clickeditem == null) {
			return;
		}
		String name = clickeditem.getDisplayName();
		String GUIItemName = Utils.chat(plugin.getConfig().getString("GUIItemName"));

		if (plugin.getConfig().getBoolean("PlayerCanMoveItem") == true) {
			return;
		}
		if (p.getGameMode() == GameMode.CREATIVE) {
			return;
		} // Checks if it's the GUI item or not
		if (!name.equalsIgnoreCase(GUIItemName)) {
			return;
		}
		e.setCancelled(true);
		return;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand() == null) {
			return;
		}
		if (plugin.getConfig().getBoolean("PlayerCanDropItem") == true) {
			return;
		}
		Item item = e.getItemDrop();
		ItemMeta handmeta = item.getItemStack().getItemMeta();
		String name = handmeta.getDisplayName();
		String GUIItemName = Utils.chat(plugin.getConfig().getString("GUIItemName"));

//if (Bukkit.getVersion().contains("1.8.1")) {
		// stuff
//} commented out after v1.0 was posted

		if (!name.equalsIgnoreCase(GUIItemName) && !name.equalsIgnoreCase(Utils.chat("&eGrappling Hook"))) {
			return;
		}

		e.setCancelled(true);
	}

	// Open by right click
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerUse(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getType() != Material.getMaterial(plugin.getConfig().getString("GUIItemType"))) {
			return;
		}
		// Prevent regular items from opening the GUI.
		ItemMeta hand = p.getItemInHand().getItemMeta();

		String name = hand.getDisplayName();
		String GUIItemName = Utils.chat(plugin.getConfig().getString("GUIItemName"));

		if (!name.equalsIgnoreCase(GUIItemName)) {
			return;
		}

		p.openInventory(CustomItemsUI.GUI(p));
	}

	// Can't place custom items chest item
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		String name = p.getItemInHand().getItemMeta().getDisplayName();
		String namechest = Utils.chat("&eCustom Items");
		if (e.getBlockPlaced().getType() == Material.CHEST) {
			if (name.equalsIgnoreCase(namechest)) {
				e.setCancelled(true);
			}
		}
	}
}
