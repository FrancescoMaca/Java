package com.ChallengePlugin.Main.Commands;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChallengeCommand implements CommandExecutor {
	
	//this method is called each time the command you have assigned to it is executed, in our case every time you do /challenge or /c
	//if the return value is true it means that the method did something
	//if it returns false then Minecraft will take care of that saying by default "Something wrong happened" or something like that
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	
    	Player player = (Player) sender;
    	
    	if (sender instanceof Player) {
    		
    		//tests if the command inserted matches the wanted command
    		if (command.getName().equalsIgnoreCase("challenge") ||
    			command.getName().equalsIgnoreCase("c")) {
    			
    			if (args.length == 0) {
    				player.sendMessage("Usage: /challenge <add|remove|set> <block> <quantity>");
    				return true;
    			}
    			
    			try {
    				//Switch with possible actions the command can do
    				switch(args[0]) {
    				
    				//adds an amount of blocks from a certain block type from database file
        			case "add":
        				if (ChallengePlugin.allBlocks.contains(args[1])) {
        					player.sendMessage(ChallengeAdd(args[1], args[2]));
        				}
        				break;
        			
        			//removes an amount of blocks from a certain block type from database file
        			case "remove":
        				if (ChallengePlugin.allBlocks.contains(args[1]))
        					player.sendMessage(ChallengeRemove(args[1], args[2]));
        				break;
        				
        			//set a quantity of blocks to a block type in the database
        			case "set":
        				if (ChallengePlugin.allBlocks.contains(args[1]))
        					player.sendMessage(ChallengeSet(args[1], args[2]));
        				break;
        				
        			//shows the user the amount of blocks of a certain type from the database
        			case "display":
        				if (ChallengePlugin.allBlocks.contains(args[1]))
        					player.sendMessage(ChallengeDisplay(args[1]));
        				else
        					player.sendMessage("\"" + args[1] + "\" not found");
        				break;
        			
        			//shows the user the challenge progress (0-100%)
        			case "progress":
        				player.sendMessage(ShowChallengeProgress());
        				break;
        			}
    			}
    			//if any of the switch's case fail this message will be shown to the user
    			catch(ArrayIndexOutOfBoundsException e) {
    				player.sendMessage("Usage: /challenge <add|remove|set> <block> <quantity>");
    				return true;
    			}
    			
    			return true;
    		}
    	}
    	return false;
    }
	
	//USAGE:
	// ChallengeAdd(Bell /* stackable up to 64 */, "12,60")
	// means that the Bell block will be getting (12 * 64) + 60 blocks
	// ChellengeAdd(Snowball, "10, 3") /* stackable up to 16 */
	// means that the Snowball will get (10 * 16) + 3 blocks
	private String ChallengeAdd(String block, String quantity) {
		try {
			//stores the entire file to a list (few kilobytes)
        	List<String> lines = Files.readAllLines(new File("challenge.txt").toPath());
        	
        	//iterates through every line of the "database"
			for(int i = 0; i < lines.size(); i++) {
				
				//splits the line in three parts: [blockName] [obtainedBlocks] [MissingBlocks]
				String[] columns = lines.get(i).split("\t");
				
				//removing magic numbers defining each index
				final int blockNameIndex = 0;
				final int objtainedAmountIndex = 1;
				
				//testing if there is a match between the current line and the block the user wants to add to
				//No need to add an else statement in case the block is not found because there is already when calling this function
				if (Objects.equals(columns[blockNameIndex], block)) {
					
					//In minecraft each every block has a stack size. the stone, for example can be stacked up to 64.
					//some items, as snowballs, are only stackable up to 16
					//and tools and armor pieces are stackable up to 1 per stack.
					
					//defining the default max stack
					int MaxStack = 64;
					
					//if the block is contained in a list that has only block stackable up to 1 per stack then the MaxStack will be changed to 1
					if (ChallengePlugin.blocks1.contains(block)) {
						MaxStack = 1;
					}

					//if the block is contained in a list that has only block stackable up to 16 per stack then the MaxStack will be changed to 16
					if (ChallengePlugin.blocks16.contains(block)) {
						MaxStack = 16;
					}

					int quantityNumber = 0;
					
					//checks if the quantity the user wants to add has a comma. if it has then those number will be converted in an amount
					if (quantity.contains(",")) {
						quantityNumber = Integer.parseInt(quantity.split(",")[0]) * MaxStack + Integer.parseInt(quantity.split(",")[1]);
					}
					else {
						quantityNumber = Integer.parseInt(quantity.split(",")[0]);
					}
					
					//converts the quantity to a integer
					int blockCount = Integer.parseInt(columns[objtainedAmountIndex]) + quantityNumber;
					
					
					//checks the bound of BlockCount
					final int LargeChestSlots = 54; 
					if (blockCount < 0) {
						blockCount = 0;
					}
					else if (blockCount > MaxStack * LargeChestSlots) {
						blockCount = MaxStack * LargeChestSlots;
					}
					
					
					//changing the line with the new value
					lines.set(i, columns[0] + '\t' + blockCount + '\t' + ((MaxStack * 54) - blockCount));
					
					//exits the loop
					break;
				}

			}
			
			//re-writes the file with the new line
			FileWriter fw = new FileWriter(new File("challenge.txt"));
			
			//emptying the file
			PrintWriter writer = new PrintWriter("challenge.txt");
			writer.write("");
			writer.close();
			
			for(String line : lines) {
				fw.write(line + '\n');
			}
			
			fw.close();
			
			//return message
			if (quantity.contains(",")) {
				return quantity.split(",")[0] + " stacks and " + quantity.split(",")[1] + " blocks " + ChatColor.GRAY + " has been removed from " + ChatColor.WHITE + block + ".\n" + ChallengeDisplay(block);
			}
			else {
				return quantity + " blocks " + ChatColor.GRAY + " has been added from " + ChatColor.WHITE + block + ".\n" + ChallengeDisplay(block);
			}
			
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	//same as ChallengeAdd but instead of adding the blocks to the database it removes them
	private String ChallengeRemove(String block, String quantity) {
		try {
        	List<String> lines = Files.readAllLines(new File("challenge.txt").toPath());
        	
			for(int i = 0; i < lines.size(); i++) {
				
				String[] columns = lines.get(i).split("\t");
				
				if (Objects.equals(columns[0], block)) {

					int MaxStack = 64;
					
					if (ChallengePlugin.blocks1.contains(block)) {
						MaxStack = 1;
					}
					else if (ChallengePlugin.blocks16.contains(block)) {
						MaxStack = 16;
					}
					
					int quantityNumber = 0;
					
					if (quantity.contains(",")) {
						quantityNumber = Integer.parseInt(quantity.split(",")[0]) * MaxStack + Integer.parseInt(quantity.split(",")[1]);
					}
					else {
						quantityNumber = Integer.parseInt(quantity.split(",")[0]);
					}					
					
					int blockCount = Integer.parseInt(columns[1]) - quantityNumber;
					
					if (blockCount < 0) {
						blockCount = 0;
					}
					else if (blockCount > MaxStack * 54) {
						blockCount = MaxStack * 54;
					}
					
					lines.set(i, columns[0] + '\t' + blockCount + '\t' + (MaxStack * 54 - blockCount));
					break;
				}

			}
			
			FileWriter fw = new FileWriter(new File("challenge.txt"));
			
			//emptying the file
			PrintWriter writer = new PrintWriter("challenge.txt");
			writer.write("");
			writer.close();
			
			for(String line : lines) {
				fw.write(line + '\n');
			}
			
			fw.close();
			
			if (quantity.contains(",")) {
				return quantity.split(",")[0] + " stacks and " + quantity.split(",")[1] + " blocks " + ChatColor.GRAY + " has been removed from " + ChatColor.WHITE + block + ".\n" + ChallengeDisplay(block);
			}
			else {
				return quantity + " blocks " + ChatColor.GRAY + " has been removed from " + ChatColor.WHITE + block + ".\n" + ChallengeDisplay(block);
			}
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	
	//This is equal to the others but instead of removing or adding a value, it sets the exact value of a certain block
	private String ChallengeSet(String block, String quantity) {
		try {
        	List<String> lines = Files.readAllLines(new File("challenge.txt").toPath());
        	
			for(int i = 0; i < lines.size(); i++) {
				
				String[] columns = lines.get(i).split("\t");
				
				if (Objects.equals(columns[0], block)) {
					
					int MaxStack = 64;
					
					if (ChallengePlugin.blocks1.contains(block)) {
						MaxStack = 1;
					}
					else if (ChallengePlugin.blocks16.contains(block)) {
						MaxStack = 16;
					}
					
					int quantityNumber = 0;
					
					if (quantity.contains(",")) {
						quantityNumber = Integer.parseInt(quantity.split(",")[0]) * MaxStack + Integer.parseInt(quantity.split(",")[1]);
					}
					else {
						quantityNumber = Integer.parseInt(quantity.split(",")[0]);
					}
					
					int blockCount = quantityNumber;
					
					if (blockCount < 0) {
						blockCount = 0;
					}
					else if (blockCount > MaxStack * 54) {
						blockCount = MaxStack * 54;
					}
					 
					lines.set(i, columns[0] + '\t' + blockCount + '\t' + (MaxStack * 54 - blockCount));
					break;
				}

			}
			
			FileWriter fw = new FileWriter(new File("challenge.txt"));
			
			//emptying the file
			PrintWriter writer = new PrintWriter("challenge.txt");
			writer.write("");
			writer.close();
			
			for(String line : lines) {
				fw.write(line + '\n');
			}
			
			fw.close();
			
			if (quantity.contains(",")) {
				return block + ChatColor.GRAY + "'s quantity has been set to " + ChatColor.WHITE + quantity.split(",")[0] + " stacks and " + quantity.split(",")[1] + " blocks" + ".\n" + ChallengeDisplay(block);
			}
			else {
				return block + ChatColor.GRAY + "'s quantity has been set to " + ChatColor.WHITE + quantity.split(",")[0] + " blocks.\n" + ChallengeDisplay(block);
			}
			
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	//Displays the quantity of a block
	private String ChallengeDisplay(String block) {
		try {
			//saves the file into a list
        	List<String> lines = Files.readAllLines(new File("challenge.txt").toPath());
        	
        	//searches for the block the user wants
			for(int i = 0; i < lines.size(); i++) {
				String[] columns = lines.get(i).split("\t");
				
				if (Objects.equals(columns[0], block)) {
					
					//set the stack quantity
					int MaxStack = 64;
					
					if (ChallengePlugin.blocks1.contains(block)) {
						MaxStack = 1;
					}
					else if (ChallengePlugin.blocks16.contains(block)) {
						MaxStack = 16;
					}
					//THIS PART IS A BIT MESSY
					
					//Converts the number stored in the second column of the database ( [blockName] [obtainedBlocks] [MissingBlocks] )
					//for example 3000 in a format of (Stacks, Blocks) so 3000 becomes (46, 56) 
					//3000 / 64 = 46.875 so 46 full stacks | 0.875 * 64 = 56 (remaining blocks)
					
					String BlocksObtainedInStacks = String.valueOf(Float.valueOf(columns[1]) / (float)MaxStack);
				
					int Stacks = Integer.parseInt(BlocksObtainedInStacks.split("\\.")[0]);
					float Blocks = Float.parseFloat("0." + BlocksObtainedInStacks.split("\\.")[1]) * MaxStack;
					
					float BlocksRemaining = MaxStack - Blocks;
					int StacksRemaining = 0;
					
					//if the division by the MaxStack does not have rest, then the blocks remaining are set to 0
					if (BlocksRemaining == 0 || BlocksRemaining == MaxStack) {
						StacksRemaining  = 54 - Stacks;
						BlocksRemaining = 0;
					}
					else {
						StacksRemaining = 54 - Stacks - 1;

					}
					
					//build the string with the total blocks and the stack number with the blocks
					String RemainignString = "";
					
					if (!Objects.equals(Stacks, 54)) {
						RemainignString = columns[2] + " (" + StacksRemaining + " stacks and "+ BlocksRemaining + ")";	
					}
					else {
						RemainignString = ChatColor.GREEN + "None";
					}
					
					return ChatColor.GRAY + "Name: " +
						   ChatColor.WHITE + columns[0] + "\n" +
						   ChatColor.GRAY + " Obtained: " +
						   ChatColor.WHITE + columns[1] + " (" + Stacks + " stacks and "+ (int)Blocks + ")" + "\n" + 
						   ChatColor.GRAY + " Missing: " +
						   ChatColor.WHITE + RemainignString + "\n";
				}
			}
			
		} catch (Exception e) {
			return "Command Display Error\n" + e.toString();
		}
		return null;
	}

	//calculates the percentage of the obtained blocks in the database and it devides them by the TotalBlockszz
	private String ShowChallengeProgress() {
		
		final int TotalBlocks = 2324862;
		int ObtainedBlocks = 0;
		
		if (TotalBlocks <= ObtainedBlocks) {
			return ChatColor.GREEN + "Challenge Completed!\n" + 
				   ChatColor.WHITE + ObtainedBlocks + "/" + TotalBlocks + " collected";
		}
		
		try {
			List<String> lines = Files.readAllLines(new File("challenge.txt").toPath());
			
			for(int i = 0; i < lines.size(); i++) {
				String[] columns = lines.get(i).split("\t"); //divided in [blockName] [obtainedBlocks] [MissingBlocks]
				ObtainedBlocks += Integer.parseInt(columns[1]);
				
			}
			float percentage = ((float)ObtainedBlocks / (float)TotalBlocks) * 100.0f;
			return ChatColor.GRAY + "Challenge Progress: " + ChatColor.GOLD + percentage + "%";
		}
		catch(Exception e) {
			return e.toString();
		}
	}
}
