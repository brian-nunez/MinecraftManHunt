package com.bjnunez.manhunt;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ManHuntEvent {
  private static Player huntedPlayer;
  private static String compassName = "Man Hunt Compass";
  
  public static void resetEvent() {
    ManHuntEvent.huntedPlayer = null;
  }
  
  private static ItemStack makeCompass(String playerName) {
    ItemStack compass = new ItemStack(Material.COMPASS);
    
    ItemMeta meta = compass.getItemMeta();
    
    meta.setDisplayName(ChatColor.GOLD + compassName);
    
    ArrayList<String> lore = new ArrayList<String>();
    lore.add(ChatColor.GREEN + "Created to follow " + ChatColor.AQUA + playerName);
    
    meta.setLore(lore);
    
    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
    
    compass.setItemMeta(meta);
    
    return compass;
  }
  
  public static boolean isInProgress() {
    return ManHuntEvent.huntedPlayer != null;
  }
  
  public static ItemStack getEventCompass() {
    return ManHuntEvent.makeCompass(ManHuntEvent.huntedPlayer.getName());
  }
  
  public static Player getHuntedPlayer() {
    return ManHuntEvent.huntedPlayer;
  }
  
  public static void setHuntedPlayer(Player player) {
    ManHuntEvent.huntedPlayer = player;
  }
  
  public static String getCompassName() {
    return ManHuntEvent.compassName;
  }
}
