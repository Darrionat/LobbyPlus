package me.Darrionat.LobbyPlus.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import me.Darrionat.LobbyPlus.Main;
import me.Darrionat.LobbyPlus.Utils;

public class EntityHit implements Listener {

	private Main plugin;

	public EntityHit(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerHit(EntityDamageByEntityEvent e) {

		if (plugin.getConfig().getBoolean("BordersEnabled") == false) {
			return;
		}
		// if (plugin.getConfig().getBoolean("DetectNPCOnHit") == true) {
		// if (e.getEntity() instanceof HumanEntity) {
		// if (e.getEntity() instanceof Player) {
		// return;
		// }
		// if (plugin.getConfig().getBoolean("AllowNPCHit") == true) {
		// e.getDamager().sendMessage(plugin.getConfig().getString("NPCHitMsg"));
		// }
		// return;
		// }
		// }
		String world = e.getDamager().getWorld().getName();
		if (!(plugin.getConfig().getString("BorderWorld").equals(world))) {
			return;
		}
		if (!(e.getDamager() instanceof Player)) {
			if (e.getEntity() instanceof Player) {
				// EntityHitsPlayer

				if (plugin.getConfig().getBoolean("AllowMobsToHitPlayers") == false) {
					e.setCancelled(true);
					return;
				}
			}
		}
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				// Player hits player
				if (plugin.getConfig().getBoolean("AllowPvP") == true) {
					return;
				}
				if (!e.getDamager().hasPermission(plugin.getConfig().getString("BorderProtectionOverridePermission"))) {
					e.setCancelled(true);
					e.getDamager().sendMessage(Utils.chat(plugin.getConfig().getString("AttemptPvPMsg")));
					return;
				}
				return;
			}
			// Player hits entity
			if (!(e.getEntity() instanceof Player)) {
				if (plugin.getConfig().getBoolean("AllowPlayersHitMobs") == false) {
					e.setCancelled(true);
					e.getDamager().sendMessage(Utils.chat(plugin.getConfig().getString("AttemptPvPMsg")));
					return;
				}
			}
		}
	}
}