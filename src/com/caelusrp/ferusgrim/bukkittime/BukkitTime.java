package com.caelusrp.ferusgrim.bukkittime;

import java.util.Date;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitTime extends JavaPlugin{
	public static final Logger logger = Logger.getLogger("Minecraft");
	private final String btName = "[BukkitTime]";
	private final String btNoPermission = " Not enough permissions!";
	
	public void onEnable(){
	}
	
	public void onDisable(){
		logger.info(btName + " has been disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args){
		String commandName = cmd.getName().toLowerCase();
		String[] trimmedArgs = args;
		if(commandName.equals("bukkittime")){
			return commandHandler(sender, trimmedArgs);
		}
		return false;
	}
	
	public boolean commandHandler(CommandSender sender, String[] trimmedArgs){
		if(trimmedArgs.length > 0){
			String[] args = RearangeString(1, trimmedArgs);
			String CommandName = trimmedArgs[0];
			if(CommandName.equals("help")){
				return PrintHelper(sender);
			}
			if(CommandName.equals("reload")){
				return ReloadPlugin(sender, args);
			}
		}
		if(trimmedArgs.length == 0){
			return UseBukkitTime(sender);
		}
		return true;
	}
	
	private boolean ReloadPlugin(CommandSender sender, String[] args){
		boolean auth = false;
		Player player = null;
		if(sender instanceof Player){
			player = (Player) sender;
			if(player.hasPermission("bukkittime.reload")){
				auth = true;
			}
		}else{
			auth = true;
		}
		if(auth == true){
			onDisable();
			onReload();
			logger.info(btName + " " + (player == null ?
					"A console administrator" :
						player.getName()) + " reloaded BukkitTime!");
			return true;
		}
		sender.sendMessage(btName + btNoPermission);
		return true;
	}
	
	public void onReload(){
		logger.info(btName + "Plugin has been enabled!");
		onEnable();
	}
	
	private boolean UseBukkitTime(CommandSender sender){
		boolean auth = false;
		Player player = null;
		if(sender instanceof Player){
			player = (Player) sender;
			if(player.hasPermission("bukkittime.use")){
				auth = true;
			}
		}else{
			auth = true;
		}
		if(auth == true){
			Date BukkitTime = new Date();
			sender.sendMessage("The time is: " + BukkitTime);
			return true;
		}
		sender.sendMessage(btName + btNoPermission);
		return true;
	}
	
	private boolean PrintHelper(CommandSender sender){
		boolean auth = false;
		Player player = null;
		if(sender instanceof Player){
			player = (Player) sender;
			if(player.hasPermission("bukkittime.help")){
				auth = true;
			}
		}else{
			auth = true;
		}
		if(auth == true){
			sender.sendMessage("====================================");
			sender.sendMessage("=> BukkitTime Help Menu            <");
			sender.sendMessage("====================================");
			sender.sendMessage("=> /bukkittime                     <");
			sender.sendMessage("=> /bukkittime help                <");
			sender.sendMessage("=> /bukkittime reload              <");
			sender.sendMessage("====================================");
			return true;
		}
		sender.sendMessage(btName + btNoPermission);
		return true;
	}
	
	public static String[] RearangeString(int startIndex, String[] string){
		String TMPString = "";
		for(int i = startIndex;
				i < string.length;
				i++){
			String Add = " ";
			if(i == startIndex){
				Add = "";
			}
			TMPString += Add + string[i];
		}
		return TMPString.split("\\ ");
	}
}
