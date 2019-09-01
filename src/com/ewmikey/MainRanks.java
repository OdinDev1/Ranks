//Made by EwMikey

package com.ewmikey;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MainRanks extends JavaPlugin implements CommandExecutor{
	
	FileHandler FileHandler = new FileHandler();
	PlayerHandler PlayerHandler = new PlayerHandler();
	Events Events = new Events(PlayerHandler);
	public String admin = (ChatColor.RED + "You must be rank " + ChatColor.RED + "[ADMIN]" + ChatColor.RED + " or above to use this command.");
	public String sr_mod = (ChatColor.RED + "You must be rank " + ChatColor.DARK_AQUA + "[SR.MOD]" + ChatColor.RED + " or above to use this command.");
	public String mod = (ChatColor.RED + "You must be rank " + ChatColor.DARK_AQUA + "[MOD]" + ChatColor.RED + " or above to use this command.");
	public String helper = (ChatColor.RED + "You must be rank " + ChatColor.BLUE + "[HELPER]" + ChatColor.RED + " or above to use this command.");
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(Events, this);
	  }
	
	@Override
	public void onDisable() {
		
	}
	
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if (sender instanceof Player) {
			Player p = (Player) sender;
		 if (cmd.getName().equalsIgnoreCase("gm")) {
			 int rank = PlayerHandler.getRank(p);
			 if(rank >= PlayerHandler.ADMIN) {
					 if(p.getGameMode() == GameMode.CREATIVE){
						 p.setGameMode(GameMode.SURVIVAL);
						 sender.sendMessage(ChatColor.RESET + "Your gamemode has been set to" + ChatColor.GREEN + " survival" + ChatColor.RESET + "!" );
						 return false;
					 }
					 else if(p.getGameMode() == GameMode.SURVIVAL) {
						 p.setGameMode(GameMode.CREATIVE);
						 sender.sendMessage(ChatColor.RESET + "Your gamemode has been set to" + ChatColor.GREEN + " creative" + ChatColor.RESET + "!" );
						 return false;
					 }
				 return false;
			 } else if(rank <= PlayerHandler.SR_MODERATOR){
				 sender.sendMessage(admin);
				 return false;
			 }
		 }
			}
			return true;
			 	}
		public boolean onCommand1(CommandSender sender, Command cmd, String label, String[] args) {
			if (sender instanceof Player) {
			Player p = (Player) sender;
		 if (cmd.getName().equalsIgnoreCase("fly")) {
			 int rank = PlayerHandler.getRank(p);
			 if(rank >= PlayerHandler.ADMIN) {
					 if(p.getGameMode() == GameMode.CREATIVE){
						 p.setGameMode(GameMode.SURVIVAL);
						 sender.sendMessage(ChatColor.RESET + "Your gamemode has been set to" + ChatColor.GREEN + " survival" + ChatColor.RESET + "!" );
						 return false;
					 }
					 else if(p.getGameMode() == GameMode.SURVIVAL) {
						 p.setGameMode(GameMode.CREATIVE);
						 sender.sendMessage(ChatColor.RESET + "Your gamemode has been set to" + ChatColor.GREEN + " creative" + ChatColor.RESET + "!" );
						 return false;
					 }
				 return false;
			 } else if(rank <= PlayerHandler.SR_MODERATOR){
				 sender.sendMessage(admin);
				 return false;
			 }
		 }
			}
			return true;
			 	}
}