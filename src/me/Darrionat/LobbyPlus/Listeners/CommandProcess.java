package me.Darrionat.LobbyPlus.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.Darrionat.LobbyPlus.Main;
import me.Darrionat.LobbyPlus.Utils;

public class CommandProcess implements Listener {

	private Main plugin;

	public CommandProcess(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}

	@EventHandler
	public void commandSent(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String sentcommand = e.getMessage();
		if (plugin.getConfig().getStringList("RegisteredCommands").contains(sentcommand)) {
			for (String messages : plugin.getConfig().getStringList(sentcommand + "messages")) {
				p.sendMessage(Utils.chat(messages));
			}
			e.setCancelled(true);
			return;
		}
	}
}
