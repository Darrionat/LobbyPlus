package me.Darrionat.LobbyPlus;

import org.bukkit.plugin.java.JavaPlugin;

import me.Darrionat.LobbyPlus.Commands.CommandOne;
import me.Darrionat.LobbyPlus.Commands.CommandThree;
import me.Darrionat.LobbyPlus.Commands.CommandTwo;
import me.Darrionat.LobbyPlus.Commands.LobbyPlus;
import me.Darrionat.LobbyPlus.Listeners.Block;
import me.Darrionat.LobbyPlus.Listeners.CommandProcess;
import me.Darrionat.LobbyPlus.Listeners.EntityHit;
import me.Darrionat.LobbyPlus.Listeners.JoinLeave;
import me.Darrionat.LobbyPlus.Listeners.Playermove;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		new JoinLeave(this);
		new CommandOne(this);
		new CommandTwo(this);
		new CommandThree(this);
		new Playermove(this);
		new CommandProcess(this);
		new LobbyPlus(this);
		new Block(this);
		new EntityHit(this);
		saveDefaultConfig();
	}

	public void onDisable() {

	}
}
