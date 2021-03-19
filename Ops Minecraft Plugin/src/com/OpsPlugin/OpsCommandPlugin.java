package com.OpsPlugin;

import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class OpsCommandPlugin implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		int randomPlayerIndex = new Random().nextInt() % OpsPlugin.onlinePlayers.size();
	    OpsPlugin.onlinePlayers.get(randomPlayerIndex).kickPlayer("You are the choosen one (L)");
		return true;
	}
	
}
