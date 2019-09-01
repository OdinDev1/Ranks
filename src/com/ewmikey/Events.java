//Made by EwMikey

package com.ewmikey;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;



public class Events implements Listener {
	
	PlayerHandler PlayerHandler;
	MainRanks MainRanks;
	
	public Events(PlayerHandler _PlayerHandler) {
		PlayerHandler = _PlayerHandler;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {

		System.out.println(e.getPlayer().getName());
		Player p = e.getPlayer();
		PlayerHandler.SetupPlayer(p);
		PlayerHandler.refreshRanks();
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		Player p = e.getPlayer();
		String name = p.getName();
		String prefix = PlayerHandler.getRankPrefix(PlayerHandler.getRank(p));
		String message = e.getMessage();
		Bukkit.broadcastMessage(prefix + ChatColor.GRAY + name + ChatColor.DARK_GRAY + ": " + ChatColor.RESET + message);
	}
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String[] args = e.getMessage().split(" ");
		String cmd = args[0].replaceAll("/", "").toLowerCase();
		int rank = PlayerHandler.getRank(p);
		if(cmd.equals("rank")) {
			if(rank >= PlayerHandler.ADMIN) {
				e.setCancelled(true);
				if(args.length == 3) {
					String targetName = args[1];
					Player target = Bukkit.getPlayer(targetName);
					if(target != null) {
						int rankValue = 0;
						String rankName = args[2].toLowerCase();
						if(rankName.equals("owner")) {
							rankValue = PlayerHandler.OWNER;
						}else if(rankName.equals("admin")) {
							rankValue = PlayerHandler.ADMIN;
						}else if(rankName.equals("sr_moderator")) {
							rankValue = PlayerHandler.SR_MODERATOR;
						}else if(rankName.equals("moderator")) {
							rankValue = PlayerHandler.MODERATOR;
						}else if(rankName.equals("trainee")) {
							rankValue = PlayerHandler.TRAINEE;
						}else if(rankName.equals("builder")) {
							rankValue = PlayerHandler.BUILDER;
						}else if(rankName.equals("mvp")) {
							rankValue = PlayerHandler.MVP;
						}else if(rankName.equals("vip")) {
							rankValue = PlayerHandler.VIP;
						}else if(rankName.equals("member")) {
							rankValue = PlayerHandler.MEMBER;
						}else {
							rankValue = -1;
						}
						if(rankValue >= 0) {
							if(rankValue < rank) {
								if(PlayerHandler.getRank(target) < rank) {
									if(PlayerHandler.setRank(target, rankValue)) {
									p.sendMessage(ChatColor.RED + "Successfully set " + ChatColor.YELLOW + target.getName()+ ChatColor.RED + "'s rank to " + ChatColor.GOLD + rankName + ChatColor.RED + "!");
									target.sendMessage(ChatColor.RED + "Your rank has been set to " + ChatColor.GOLD + rankName + ChatColor.RED + "!");
									p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
									target.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
									PlayerHandler.refreshRanks();
								}else if(PlayerHandler.getRank(target) > rank) {
									p.sendMessage(ChatColor.RED + "Error: You can't modify people who are the same or a higher rank than you!");
								}
							}else if(rankValue > rank){
								p.sendMessage(ChatColor.RED + "Error: You can't use ranks bigger than or equal to yours!");
							}
						}else {
							p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + rankName + ChatColor.RED +" is not a rank!");
						}
					}else if(rank <= PlayerHandler.SR_MODERATOR){
						p.sendMessage(ChatColor.RED + "You must be rank " + ChatColor.RED + "[ADMIN]" + ChatColor.RED + " or above to use this command.");
					}
				}else if(args.length == 0) {
					p.sendMessage(ChatColor.RED + "Usage: /rank <player> <rank>");
				}
			}
		}
	}
	}
}




