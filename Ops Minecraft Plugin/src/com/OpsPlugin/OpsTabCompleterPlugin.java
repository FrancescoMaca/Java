package com.OpsPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class OpsTabCompleterPlugin implements TabCompleter {
	
	@Override
	//this class is the tab competition, returns the suggestion based on what the arguments are and what number of arguments you are at
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {

		List<String> actions = new ArrayList<String>();
		actions.add("ops");
		if (arg3.length == 1) {
			return actions;
		}
		else {
			return null;
		}
	}
}
