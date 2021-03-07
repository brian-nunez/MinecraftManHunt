package com.bjnunez.manhunt;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ManHuntEvent {
  private static ManHuntEvent instance = new ManHuntEvent();
  private Player huntedPlayer;
  private String compassName = "Man Hunt Compass";
  
  private ManHuntEvent() {}
  
  public void resetEvent() {
    this.huntedPlayer = null;
  }
  
  public static ManHuntEvent getEvent() {
    return instance;
  }
  
  private ItemStack makeCompass(String playerName) {
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
  
  public boolean isInProgress() {
    return this.huntedPlayer != null;
  }
  
  public ItemStack getEventCompass() {
    return this.makeCompass(this.huntedPlayer.getName());
  }
  
  public Player getHuntedPlayer() {
    return this.huntedPlayer;
  }
  
  public void setHuntedPlayer(Player player) {
    this.huntedPlayer = player;
  }
  
  public String getCompassName() {
    return this.compassName;
  }
}
