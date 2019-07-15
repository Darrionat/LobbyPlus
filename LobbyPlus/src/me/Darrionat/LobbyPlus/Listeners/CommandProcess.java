package me.Darrionat.LobbyPlus.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.Darrionat.LobbyPlus.Main;

public class CommandProcess implements Listener{
	
	private Main plugin;
	
	public CommandProcess(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
	
	@EventHandler
	public void commandSent(PlayerCommandPreprocessEvent e) {
		String sent = e.getMessage();
		if (sent.startsWith(plugin.getConfig().getString("Command1"))) {
			e.setMessage("/commandone");
			return;
		}
		if (sent.startsWith(plugin.getConfig().getString("Command2"))) {
			e.setMessage("/commandtwo");
			return;
		}
		if (sent.startsWith(plugin.getConfig().getString("Command3"))) {
			e.setMessage("/commandthree");
			return;
		}
	}
}
