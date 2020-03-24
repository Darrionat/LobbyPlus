package me.Darrionat.LobbyPlus.Listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import me.Darrionat.LobbyPlus.Main;
import me.Darrionat.LobbyPlus.Utils;

public class GrappleHook implements Listener {

	private Main plugin;
	HashMap<String, Long> cooldowns = new HashMap<String, Long>();

	public GrappleHook(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}

	@EventHandler
	public void onFish(PlayerFishEvent e) {

		Player p = e.getPlayer();
		Entity entity = e.getHook();
		Block block = entity.getWorld().getBlockAt(entity.getLocation());

		ItemStack mainhand = p.getItemInHand();
		ItemMeta handmeta = p.getItemInHand().getItemMeta();
		String name = handmeta.getDisplayName();

		if (!name.equalsIgnoreCase(Utils.chat("&eGrappling Hook"))) {
			return;
		}

		int cooldownTime = 2; // Get number of seconds from wherever you want
		if (cooldowns.containsKey(p.getName())) {
			long secondsLeft = ((cooldowns.get(p.getName()) / 1000) + cooldownTime)
					- (System.currentTimeMillis() / 1000);
			if (secondsLeft > 0) {
				p.sendMessage(Utils.chat("&cSlow down, you are using this too fast!"));
				return;
			}
		}
		if (e.getState() != State.IN_GROUND) {
			return;
		}
		cooldowns.put(p.getName(), System.currentTimeMillis());

		int xhook = entity.getLocation().getBlockX();
		int yhook = entity.getLocation().getBlockY();
		int zhook = entity.getLocation().getBlockZ();
		int xplayer = p.getLocation().getBlockX();
		int yplayer = p.getLocation().getBlockY();
		int zplayer = p.getLocation().getBlockZ();
		int y = yplayer + 2;

		Vector player = new Vector(xplayer, yplayer, zplayer);
		Vector hook = new Vector(xhook, y, zhook);
		Vector v = ((hook.subtract(player)).multiply(0.5));
		// Vector v = entity.getLocation().getDirection().multiply(-2).setY(1);

		p.setVelocity(v);
		mainhand.setDurability((short) -1);
		return;

	}
}
