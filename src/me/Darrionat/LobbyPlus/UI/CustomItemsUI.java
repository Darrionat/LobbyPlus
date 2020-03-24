package me.Darrionat.LobbyPlus.UI;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import me.Darrionat.LobbyPlus.Utils;

public class CustomItemsUI {

	public static Inventory inv;
	public static String inventory_name;
	public static int inv_boxes = 3;// Amount of rows
	public static int rows = inv_boxes * 9;

	public static void initialize() {
		inventory_name = Utils.chat("&eCustom Items");

		inv = Bukkit.createInventory(null, rows);
	}

	public static Inventory GUI(Player p) {
		Inventory toReturn = Bukkit.createInventory(null, rows, inventory_name);

		// GrapplingHook
		ItemStack grapplehook = new ItemStack(Material.FISHING_ROD, 1);
		ItemMeta meta = grapplehook.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		meta.setDisplayName(Utils.chat("&eGrappling Hook"));
		meta.setLore(lore);
		grapplehook.setItemMeta(meta);
		
		PlayerInventory pinv = p.getInventory();
		
		if (pinv.contains(grapplehook)) {
			Utils.createItem(inv, Material.FISHING_ROD.toString(), 1, 1, "&eGrappling Hook &8- &aENABLED",
					"&bTravel quickly with the grappling hook!");
		} else {
			Utils.createItem(inv, Material.FISHING_ROD.toString(), 1, 1, "&eGrappling Hook &8- &cDISABLED",
					"&bTravel quickly with the grappling hook!");
		}
		//Rainbow Armor
		String rainbowarmor = Utils.chat("&cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r");
		String rainbowdisabled = Utils.chat("&cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r &8- &cDISABLED");
		String rainbowenabled = Utils.chat("&cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r &8- &aENABLED");
		String rainbowlore = Utils.chat("&bHave a full set of &cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r");

		if (pinv.getHelmet() == null) {
			Utils.createItem(inv, Material.LEATHER_CHESTPLATE.toString(), 1, 2, rainbowdisabled, rainbowlore);
			if (pinv.getChestplate() == null) {
				Utils.createItem(inv, Material.LEATHER_CHESTPLATE.toString(), 1, 2, rainbowdisabled, rainbowlore);
				if (pinv.getLeggings() == null) {
					Utils.createItem(inv, Material.LEATHER_CHESTPLATE.toString(), 1, 2, rainbowdisabled, rainbowlore);
					if (pinv.getBoots() == null) {
						Utils.createItem(inv, Material.LEATHER_CHESTPLATE.toString(), 1, 2, rainbowdisabled,
								rainbowlore);
					} else {
						if (pinv.getBoots().getItemMeta().getDisplayName().equalsIgnoreCase(rainbowarmor)) {
							Utils.createItem(inv, Material.LEATHER_CHESTPLATE.toString(), 1, 2, rainbowenabled,
									rainbowlore);
						} else {
							Utils.createItem(inv, Material.LEATHER_CHESTPLATE.toString(), 1, 2, rainbowdisabled,
									rainbowlore);
						}

					}
				} else {
					if (pinv.getLeggings().getItemMeta().getDisplayName().equalsIgnoreCase(rainbowarmor)) {
						Utils.createItem(inv, Material.LEATHER_CHESTPLATE.toString(), 1, 2, rainbowenabled,
								rainbowlore);
					} else {
						Utils.createItem(inv, Material.LEATHER_CHESTPLATE.toString(), 1, 2, rainbowdisabled,
								rainbowlore);
					}
				}
			} else {

				if (pinv.getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase(rainbowarmor)) {
					Utils.createItem(inv, Material.LEATHER_CHESTPLATE.toString(), 1, 2, rainbowenabled, rainbowlore);
				} else {
					Utils.createItem(inv, Material.LEATHER_CHESTPLATE.toString(), 1, 2, rainbowdisabled, rainbowlore);
				}
			}
		} else {

			if (pinv.getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase(rainbowarmor)) {
				Utils.createItem(inv, Material.LEATHER_CHESTPLATE.toString(), 1, 2, rainbowenabled, rainbowlore);
			} else {
				Utils.createItem(inv, Material.LEATHER_CHESTPLATE.toString(), 1, 2, rainbowdisabled, rainbowlore);
			}
		}

		//Close Menu Icon
		Utils.createItem(inv, Material.BARRIER.toString(), 1, 23, "&4Close Menu");

		toReturn.setContents(inv.getContents());
		return toReturn;
	}

	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
		if (clicked == null || clicked.getItemMeta() == null) {
			return;
		}
		String clickname = clicked.getItemMeta().getDisplayName();
		// Grappling Hook
		ItemStack grapplehook = new ItemStack(Material.FISHING_ROD, 1);
		ItemMeta meta = grapplehook.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		meta.setDisplayName(Utils.chat("&eGrappling Hook"));
		meta.setLore(lore);
		grapplehook.setItemMeta(meta);
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

		if (clickname.equalsIgnoreCase(Utils.chat("&4Close Menu"))) {
			p.closeInventory();
			return;
		}
		// Grappling Hook
		if (clickname.equalsIgnoreCase(Utils.chat("&eGrappling Hook &8- &cDISABLED"))) {

			p.getInventory().addItem(grapplehook);
			p.closeInventory();
			p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 2F, 1F);
			p.sendMessage(Utils.chat("&eGrappling Hook &aENABLED"));
			return;
		}
		if (clickname.equalsIgnoreCase(Utils.chat("&eGrappling Hook &8- &aENABLED"))) {
			p.getInventory().remove(grapplehook);
			p.closeInventory();
			p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 2F, 1F);
			p.sendMessage(Utils.chat("&eGrappling Hook &cDISABLED"));
			return;
		}
		// Rainbow Armor

		if (clickname.equalsIgnoreCase(Utils.chat("&cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r &8- &cDISABLED"))) {

			if (p.getInventory().getHelmet() != null || p.getInventory().getChestplate() != null
					|| p.getInventory().getLeggings() != null || p.getInventory().getBoots() != null) {
				p.sendMessage(Utils
						.chat("&cYou must take off all your armor to equip &cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r&c!"));
				return;
			}
			p.getInventory().setHelmet(rainbowhelmet);
			p.getInventory().setChestplate(rainbowchestplate);
			p.getInventory().setLeggings(rainbowleggings);
			p.getInventory().setBoots(rainbowboots);
			p.closeInventory();
			p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 2F, 1F);
			p.sendMessage(Utils.chat("&cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r &aENABLED"));

			return;
		}
		if (clickname.equalsIgnoreCase(Utils.chat("&cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r &8- &aENABLED"))) {
			p.getInventory().setHelmet(null);
			p.getInventory().setChestplate(null);
			p.getInventory().setLeggings(null);
			p.getInventory().setBoots(null);
			p.closeInventory();
			p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 2F, 1F);
			p.sendMessage(Utils.chat("&cR&6a&ei&an&2b&9o&3w &cA&6r&em&ao&2r &cDISABLED"));
			return;
		}
	}
}
