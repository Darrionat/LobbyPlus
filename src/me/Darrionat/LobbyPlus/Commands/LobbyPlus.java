package me.Darrionat.LobbyPlus.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Darrionat.LobbyPlus.Main;
import me.Darrionat.LobbyPlus.Utils;

public class LobbyPlus implements CommandExecutor {

	private Main plugin;

	public LobbyPlus(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("lobbyplus").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(Utils.chat("&6---LobbyPlus---"));
			sender.sendMessage(Utils.chat("&6/lobbyplus - Shows this message"));
			sender.sendMessage(Utils.chat("&6/lobbyplus reload - Reloads the plugin's config."));
			return true;
		} //If the first argument isn't reload, give the list.
		if (!(args[0].equalsIgnoreCase("reload"))) {
			sender.sendMessage(Utils.chat("&6---LobbyPlus---"));
			sender.sendMessage(Utils.chat("&6/lobbyplus - Shows this message"));
			sender.sendMessage(Utils.chat("&6/lobbyplus reload - Reloads the plugin's config."));
			return true;
		}
		//If the sender isn't a player and they do /lobbyplus reload
		if (args[0].equalsIgnoreCase("reload")) {
			if (!(sender instanceof Player)) {
				Bukkit.getPluginManager().getPlugin(plugin.getName()).reloadConfig();
				sender.sendMessage(Utils
						.chat("&a" + plugin.getName() + "v" + plugin.getDescription().getVersion() + "'s config reloaded!"));
				return true;
			}
			//Checks if the player doesn't have the permission
			Player p = (Player) sender;
			if (!p.hasPermission("lobbyplus.reload")) {
				p.sendMessage(Utils.chat("&cYou do not have the permission lobbyplus.reload"));
				return true;
			}
			//Reload
			Bukkit.getPluginManager().getPlugin(plugin.getName()).reloadConfig();
			sender.sendMessage(
					Utils.chat("&a" + plugin.getName() + "v" + plugin.getDescription().getVersion() + "'s config reloaded!"));
			return true;
		}
		return true;
	}
}
