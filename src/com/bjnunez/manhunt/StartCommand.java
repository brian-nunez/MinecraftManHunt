package com.bjnunez.manhunt;

import java.util.Collection;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {
  Main plugin;
  
  StartCommand(Main plugin) {
    this.plugin = plugin;
  }
  
  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] commandArgs) {
     if (commandArgs.length == 0) {
      sender.sendMessage(ChatColor.RED + "Usage /manhunt <start|stop> <playername>");
       return true;
     }
     
     Player player = (Player) sender;
     
     switch (commandArgs[0]) {
       case "start":
         startEvent(player, commandArgs[1]);
         break;
       case "stop":
         stopEvent(player);
         break;
       default:
         sender.sendMessage(ChatColor.RED + "Usage /manhunt <start|stop> <playername>");
     }
    
    return true;
  }
  
  private void startEvent(Player sender, String playerName) {
    if (ManHuntEvent.getEvent().isInProgress()) {
      sender.sendMessage(ChatColor.RED + "Event in progess...");
      return;
    }
    
    Player huntedPlayer = null;
    
    Collection<? extends Player> players = plugin.getServer().getOnlinePlayers();
    
    for (Player player : players) {
      if (player.getName().equals(playerName)) {
        huntedPlayer = player;
        break;
      }  
    }
    
    if (huntedPlayer == null) {
      sender.sendMessage(ChatColor.RED + "Issue finding that player...");
      return;
    }
    
    ManHuntEvent.getEvent().setHuntedPlayer(huntedPlayer);
    
    Collection<? extends Player> hunterPlayers = plugin.getServer().getOnlinePlayers();
    
    hunterPlayers.forEach(player -> {
      if (player.getName().equals(ManHuntEvent.getEvent().getHuntedPlayer().getName())) {
        return;
      }
      player
        .getInventory()
        .addItem(ManHuntEvent.getEvent().getEventCompass());
    });

    plugin.getServer().broadcastMessage(ChatColor.GREEN + "Man Hunt started! Target player is " + ChatColor.GOLD + ChatColor.BOLD + huntedPlayer.getName());
  }
  
  private void stopEvent(Player sender) {
    if (!ManHuntEvent.getEvent().isInProgress()) {
      sender.sendMessage(ChatColor.RED + "Event is not in progress...");
      return;
    }

    ManHuntEvent.getEvent().resetEvent();
    
    plugin.getServer().broadcastMessage(ChatColor.RED + "Man Hunt was stopped by " + ChatColor.GOLD + sender.getName());
  }
}






