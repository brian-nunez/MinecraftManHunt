package com.bjnunez.manhunt;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  @Override
  public void onEnable() {
    getLogger().info("ManHunt Enabled");
    PluginManager pm = getServer().getPluginManager();
    
    EventListener eventListener = new EventListener(this);
    
    this.getCommand("manhunt").setExecutor(new StartCommand(this));
    
    pm.registerEvents(eventListener, this);
  }

  @Override
  public void onDisable() {
    
  }
  
}
