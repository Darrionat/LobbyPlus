package me.Darrionat.LobbyPlus.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import me.Darrionat.LobbyPlus.Main;
import me.Darrionat.LobbyPlus.Utils;

public class CommandThree implements CommandExecutor {

	private Main plugin;
	public CommandThree(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("commandthree").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (plugin.getConfig().getBoolean("Command3Enabled") == false) {
			return true;
		}
		for (String messages : plugin.getConfig().getStringList("command3messages")) {
			sender.sendMessage(Utils.chat(messages));
		}
		return true;
	}
}