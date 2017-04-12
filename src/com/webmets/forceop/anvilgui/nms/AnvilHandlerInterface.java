package com.webmets.forceop.anvilgui.nms;

import org.bukkit.entity.Player;

import com.webmets.forceop.anvilgui.AnvilGuiCore;


public abstract interface AnvilHandlerInterface {
	public abstract AnvilGUIInterface createNewGUI(AnvilGuiCore paramAnvilApiCore, Player paramPlayer,
			AnvilClickEventHandler paramAnvilClickEventHandler);
}
