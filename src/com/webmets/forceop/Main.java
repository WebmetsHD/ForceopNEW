package com.webmets.forceop;

import org.bukkit.plugin.java.JavaPlugin;

import com.webmets.forceop.anvilgui.AnvilGuiCore;

public class Main extends JavaPlugin {

	public static Main instance;
	
	public AnvilGuiCore anvil;
	
	@Override
	public void onEnable() {
		anvil = new AnvilGuiCore();
		anvil.init(this);
		getServer().getPluginManager().registerEvents(new Forceop(this), this);
		instance = this;
	}
	
	public static Main getInstance(){
		return instance;
	}
	
}
