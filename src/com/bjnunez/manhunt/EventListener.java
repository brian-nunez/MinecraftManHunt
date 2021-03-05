package com.bjnunez.manhunt;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {
  Main plugin;
  
  EventListener(Main plugin) {
    this.plugin = plugin;
  }
  
  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event) {
    Player player = event.getEntity();
    Player huntedPlayer = ManHuntEvent.getHuntedPlayer();
    
    if (ManHuntEvent.isInProgress()) {
      if (player.getName().equals(huntedPlayer.getName())) {
        ManHuntEvent.resetEvent();
        plugin.getServer().broadcastMessage(ChatColor.GREEN + "Hunters Win! " + ChatColor.GOLD + huntedPlayer.getName() + ChatColor.GREEN + " died!");
      }
    }
  }
  
  @EventHandler
  public void onPlayerClicks(PlayerInteractEvent event) {
      Player player = event.getPlayer();
      Action action = event.getAction();
      ItemStack item = event.getItem();
      
      if (!ManHuntEvent.isInProgress()) {
        return;
      }

       if ( action.equals( Action.RIGHT_CLICK_AIR ) || action.equals( Action.RIGHT_CLICK_BLOCK ) ) {
           if ( item != null && item.getType() == Material.COMPASS && item.getItemMeta().getDisplayName().contains(ManHuntEvent.getCompassName()) ) {
               Player huntedPlayer = ManHuntEvent.getHuntedPlayer();
               player.setCompassTarget(huntedPlayer.getLocation());
               player.sendMessage(
                   ChatColor.AQUA +
                   "Compass is now pointing to " +
                   ChatColor.GOLD +
                   huntedPlayer.getName() +
                   ChatColor.AQUA +
                   " at Y: " +
                   huntedPlayer.getLocation().getY()
               );
           } 
       }

  }
}
