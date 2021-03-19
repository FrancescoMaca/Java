package com.OpsPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class OpsPlugin extends JavaPlugin implements CommandExecutor {
	
	static List<Player> onlinePlayers = new ArrayList<Player>();
	
	public OpsPlugin() {
		onlinePlayers = (List<Player>)Bukkit.getOnlinePlayers();
	}
	
	public void onEnable() {
		getLogger().info("Plugin enabled");
		getCommand("ops").setExecutor(new OpsCommandPlugin());
		getCommand("ops").setTabCompleter(new OpsTabCompleterPlugin());
	}
	
	public void onDisable() {
		getLogger().info("Plugin disabled");
	}
	
}
