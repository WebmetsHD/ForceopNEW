package com.webmets.forceop.anvilgui.nms;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract interface AnvilGUIInterface {
	public abstract Player getPlayer();

	public abstract void setSlot(AnvilSlot paramAnvilSlot, ItemStack paramItemStack);

	public abstract void open();

	public abstract void destroy();
}
