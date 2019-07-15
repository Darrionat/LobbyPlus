package me.Darrionat.LobbyPlus.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import me.Darrionat.LobbyPlus.Main;
import me.Darrionat.LobbyPlus.Utils;

public class CommandOne implements CommandExecutor {

	private Main plugin;
	public CommandOne(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("commandone").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (plugin.getConfig().getBoolean("Command1Enabled") == false) {
			return true;
		}
		for (String messages : plugin.getConfig().getStringList("command1messages")) {
			sender.sendMessage(Utils.chat(messages));
		}
		return true;
	}
}