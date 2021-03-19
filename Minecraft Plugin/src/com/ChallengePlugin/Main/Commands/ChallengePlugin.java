package com.ChallengePlugin.Main.Commands;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

public class ChallengePlugin extends JavaPlugin {
	
	//allBlocks contain every signle block in the game
	public static List<String> allBlocks = new ArrayList<String>();
	
	//blocks contains all 64 stackable blocks
	public static List<String> blocks = new ArrayList<String>();
	
	//blocks contains all 16 stackable blocks
	public static List<String> blocks16 = new ArrayList<String>();
	
	//blocks contains all signle stackable blocks
	public static List<String> blocks1 = new ArrayList<String>();
	
	public ChallengePlugin() {
		
		
		/* Initializing lists */
		try {
			String[] OnePerStack = new String[] {
					"Boat", "Sword", "Shovel", "Axe", "Hoe", "Pickaxe", "Helmet", "Chesplate", "Leggings", "Boots",
					"Cap", "Tunic", "Pants", "Horse", "Saddle", "Trident", "Totem", "Fishing", "cake", "Carrot_on_a_stick",
					"Steel", "Shears", "Potion", "Water_Bucket", "Lava_Bucket", "Milk_Bucket", "Bucket_of", "Turtle_Shell",
					"Bowl", "Stew", "Minecart", "Bed", "Disc", "Book_of_", "Banner_Pattern", "Crossbow", "Shulker_Box"
			};
			
			String[] SixteenPerStack = new String[] {
					"Snowball", "Egg", "Bucket", "Ender_Pearl", "Armor_Stand", "_Banner"
					
			};
			
			List<String> lines = Files.readAllLines(new File("challenge.txt").toPath());
						
			//create the general list with all blocks
			for(int i = 0; i < lines.size(); i++) {
				
				String blockName = lines.get(i).split("\t")[0];
				
				//adds everyBlock to the general list
				blocks.add(blockName);
				
				//if the for loops find a block that is contained into one of those list they remove it from the block list
				for(int j = 0; j < OnePerStack.length; j++) {
					if (blockName.contains(OnePerStack[j])) {
						blocks1.add(blockName);
						blocks.remove(blockName);
					}
				}
				
				for(int j = 0; j < SixteenPerStack.length; j++) {
					if (blockName.contains(SixteenPerStack[j])) {
						if (SixteenPerStack[j].equals("Bucket") && !blockName.equals("Bucket"))
							continue;
						
						if(blockName.equals("Turtle_Egg")) {
							continue;
						}
						
						blocks16.add(blockName);
						blocks.remove(blockName);
					}
				}
			}
			
			//adding all lists to a single list
			allBlocks.addAll(ChallengePlugin.blocks);
			allBlocks.addAll(ChallengePlugin.blocks16);
			allBlocks.addAll(ChallengePlugin.blocks1);
			
			//logs to the console the size of each list
			getLogger().info("Blocks length: " + blocks.size());
			getLogger().info("Blocks16 length: " + blocks16.size());
			getLogger().info("Blocks1 length: " + blocks1.size());
		}
		catch(Exception e) {
			getLogger().info("Error Initializing block list: " + e.getMessage());
		}
	}
	
	@Override
	//called everytime the plugin gets enabled (as soon as the server starts)
    public void onEnable() {
		getLogger().info("Plugin enabled");
		
		//these lines says that everytime the player inputs a command that is equal to "challenge" or "c" it create an Executor that is the class ChallengeCommand.java
		getCommand("challenge").setExecutor(new ChallengeCommand());
		
		//the tabCompleter is the suggestion that the game tells you whenever you are typing the command
		getCommand("challenge").setTabCompleter(new ChallengeTabCompleter());
		
		getCommand("c").setExecutor(new ChallengeCommand());
		getCommand("c").setTabCompleter(new ChallengeTabCompleter());
    }
	
    @Override
    //called everytime the plugin gets disable (as soon as the server stops)
    public void onDisable() {
    	getLogger().info("Plugin disable");
    }
}
