package com.webmets.forceop.anvilgui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.webmets.forceop.Main;
import com.webmets.forceop.anvilgui.nms.AnvilClickEvent;
import com.webmets.forceop.anvilgui.nms.AnvilClickEventHandler;
import com.webmets.forceop.anvilgui.nms.AnvilGUIInterface;
import com.webmets.forceop.anvilgui.nms.AnvilHandlerInterface;
import com.webmets.forceop.anvilgui.nms.AnvilSlot;


public class AnvilGuiCore {

	public AnvilHandlerInterface anvilGUI;
	public Main main;
	
	public void init(Main main) {
		this.main = main;
		String version = getPackageVersion();
		try {
			Class<?> clazz = Class.forName("com.webmets.forceop.anvilgui.nms." + version + ".AnvilHandler");
			if (AnvilHandlerInterface.class.isAssignableFrom(clazz)) {
				this.anvilGUI = ((AnvilHandlerInterface) clazz.newInstance());
				log("Loaded nms support for version " + version);
			}
		} catch (Exception ex) {
			main.getLogger().severe("Could not find support for version: " + version);
			main.getLogger().severe("Check for an update here: ");
			main.getLogger().severe("If there is no update, contact PatoTheBest");
			return;
		}
	}
	
	@EventHandler
	public void command(PlayerCommandPreprocessEvent event) {
		if(event.getMessage().equalsIgnoreCase("/anvil")) {
			AnvilGUIInterface gui = anvilGUI.createNewGUI(this, event.getPlayer(), new AnvilClickEventHandler() {
				@Override
				public void onAnvilClick(AnvilClickEvent e) {
					if(e.getSlot() == AnvilSlot.OUTPUT) {
						e.setWillClose(true);
						e.setWillDestroy(true);
						event.getPlayer().sendMessage(e.getName().replaceAll("&", "§"));
					} else {
						e.setWillClose(true);
						e.setWillDestroy(true);
					}
				}
			});
			ItemStack item = new ItemStack(Material.PAPER, 1);
			ItemMeta im = item.getItemMeta();
			im.setDisplayName("Enter text here!");
			item.setItemMeta(im);
			gui.setSlot(AnvilSlot.INPUT_LEFT, item);
			gui.open();
		}
	}

	private void log(String string) {
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + string);
	}

	private String getPackageVersion() {
		String pkg = Bukkit.getServer().getClass().getPackage().getName();
		return pkg.substring(pkg.lastIndexOf('.') + 1);
	}

	protected AnvilHandlerInterface getAnvilHandler() {
		return this.anvilGUI;
	}
}
