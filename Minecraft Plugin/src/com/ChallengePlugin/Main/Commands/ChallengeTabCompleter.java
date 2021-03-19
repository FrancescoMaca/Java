package com.ChallengePlugin.Main.Commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class ChallengeTabCompleter implements TabCompleter {
		
	@Override
	//this class is the tab competition, returns the suggestion based on what the arguments are and what number of arguments you are at
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {

		if (arg3.length == 1) {
			List<String> actions = new ArrayList<String>();
			
			//returns only the actions that match with what you type
			List<String> tabActions = new ArrayList<String>();
			
			actions.add("add");
			actions.add("remove");
			actions.add("set");
			actions.add("display");
			actions.add("progress");
			
			for(String action : actions) {
				if (action.toLowerCase().startsWith(arg3[0].toLowerCase())){
					tabActions.add(action);
				}
			}
			
			Collections.sort(actions);
			return tabActions;
		}
		else if (arg3.length == 2) {
			
			List<String> sortedList = new ArrayList<String>();
			
			
			//returns only the blocks that match with what you type
			for(String block : ChallengePlugin.allBlocks) {
				if (block.toLowerCase().startsWith(arg3[1].toLowerCase())){
					sortedList.add(block);
				}
			}
			
			return sortedList;
			
		}
		else if (arg3.length == 3) {
			return new ArrayList<String>(0);
		}
		
		return null;
	}
}
