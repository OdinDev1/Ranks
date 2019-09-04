//Made by EwMikey

package com.ewmikey;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


public class PlayerHandler {
	
	int OWNER = 100;
	int ADMIN = 80;
	int SR_MODERATOR = 60;
	int MODERATOR = 50;
	int TRAINEE = 40;
	int BUILDER = 39;
	int MVP = 30;
	int VIP = 20;
	int MEMBER = 0;
	
	public void SetupPlayer(Player p) {
		File f = new File("plugins/Ranks/PlayerData/" + p.getUniqueId() + ".yml");
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		yml.addDefault("Name", p.getName());
		yml.addDefault("Rank", MEMBER);
		yml.options().copyDefaults(true);
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean setRank(Player p, int rank) {
		File f = new File("plugins/Ranks/PlayerData/" + p.getUniqueId() + ".yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		yml.set("Rank", rank);
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public int getRank(Player p) {
		File f = new File("plugins/Ranks/PlayerData/" + p.getUniqueId() + ".yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		return yml.getInt("Rank");
	}
	
	public String getRankPrefix(int Rank) {
		if(Rank == OWNER) {
			return ChatColor.RED + "[OWNER] " + ChatColor.RESET;
		} else if(Rank == GAY) {
			return ChatColor.RED.toString() + ChatColor.BOLD + "GAY " + ChatColor.RESET;
		} else if(Rank == ADMIN) {
			return ChatColor.RED + "[ADMIN] ";
		} else if (Rank == SR_MODERATOR) {
			return ChatColor.DARK_AQUA + "[SR.MOD] " + ChatColor.RESET;
		} else if(Rank == MODERATOR) {
			return ChatColor.DARK_AQUA + "[MOD] " + ChatColor.RESET;
		} else if(Rank == TRAINEE) {
			return ChatColor.BLUE + "[HELPER] " + ChatColor.RESET;
		} else if(Rank == BUILDER) {
			return ChatColor.GREEN + "[BUILDER] " + ChatColor.RESET;
		} else if(Rank == MVP) {
			return ChatColor.BLUE.toString() + ChatColor.BOLD + "MVP ";
		} else if(Rank == VIP) {
			return ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "VIP ";
		} else if(Rank == MEMBER){ 
			return ChatColor.GRAY + "";
		} else
			return "";
		}



	public void refreshRanks() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
			for(Player pl : Bukkit.getOnlinePlayers()) {
				String prefix = getRankPrefix(getRank(pl));
				Team team = board.registerNewTeam(pl.getName());
				team.setPrefix(prefix);
				team.addEntry(pl.getName());
			}
			
			
			Objective objective = board.registerNewObjective("Stats", "dummy");
			
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			objective.setDisplayName(ChatColor.GRAY.toString() + ChatColor.BOLD + "New" + ChatColor.GREEN.toString() + ChatColor.BOLD + "Haven");
			
			String prefix = getRankPrefix(getRank(p));
			
			Score LobbyScore = objective.getScore(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Lobby:");
			LobbyScore.setScore(7);
			
			Score LobbyyScore = objective.getScore(ChatColor.RESET + "Lobby-1" );
			LobbyyScore.setScore(6);
			
			Score blankTwo = objective.getScore(" ");
			blankTwo.setScore(5);
			
			Score discordScore = objective.getScore(ChatColor.AQUA.toString() + ChatColor.BOLD + "Discord:");
			discordScore.setScore(4);
			
			Score discorddScore = objective.getScore(ChatColor.RESET + "Mikey#1002");
			discorddScore.setScore(3);
			
			Score blankOne = objective.getScore(ChatColor.RESET + " ");
			blankOne.setScore(2);
			
			Score rankScore = objective.getScore(ChatColor.GREEN.toString() + ChatColor.BOLD + "Rank: ");
			rankScore.setScore(1);
			
			Score prefixScore = objective.getScore(ChatColor.RESET + prefix);
			prefixScore.setScore(0);
			
			p.setScoreboard(board);
		}
	}
	    }

	
