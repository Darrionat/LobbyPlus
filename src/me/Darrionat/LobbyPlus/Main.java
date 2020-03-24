package me.Darrionat.LobbyPlus;

import org.bukkit.plugin.java.JavaPlugin;

import me.Darrionat.LobbyPlus.Commands.LobbyPlus;
import me.Darrionat.LobbyPlus.Listeners.Block;
import me.Darrionat.LobbyPlus.Listeners.CommandProcess;
import me.Darrionat.LobbyPlus.Listeners.DoubleJump;
import me.Darrionat.LobbyPlus.Listeners.EntityExplode;
import me.Darrionat.LobbyPlus.Listeners.EntityHit;
import me.Darrionat.LobbyPlus.Listeners.GrappleHook;
import me.Darrionat.LobbyPlus.Listeners.InventoryClick;
import me.Darrionat.LobbyPlus.Listeners.JoinCustomItemChest;
import me.Darrionat.LobbyPlus.Listeners.JoinLeave;
import me.Darrionat.LobbyPlus.Listeners.MobSpawn;
import me.Darrionat.LobbyPlus.Listeners.PlayerDeath;
import me.Darrionat.LobbyPlus.Listeners.Playermove;
import me.Darrionat.LobbyPlus.Listeners.RainbowArmor;
import me.Darrionat.LobbyPlus.Listeners.RainbowArmorClick;
import me.Darrionat.LobbyPlus.UI.CustomItemsUI;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		new JoinLeave(this);
		new Playermove(this);
		new CommandProcess(this);
		new LobbyPlus(this);
		new Block(this);
		new EntityHit(this);
		new DoubleJump(this);
		new GrappleHook(this);
		new JoinCustomItemChest(this);
		new InventoryClick(this);
		new RainbowArmor(this);
		new RainbowArmorClick(this);
		new PlayerDeath(this);
		new MobSpawn(this);
		new EntityExplode(this);

		RainbowArmor.makeClockAndChangingTimers();
		CustomItemsUI.initialize();
		saveDefaultConfig();
	}

	public void onDisable() {

	}
}
