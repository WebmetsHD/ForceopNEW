package com.webmets.forceop.anvilgui.nms.v1_8_R3;

import org.bukkit.entity.Player;

import com.webmets.forceop.anvilgui.AnvilGuiCore;
import com.webmets.forceop.anvilgui.nms.AnvilClickEventHandler;
import com.webmets.forceop.anvilgui.nms.AnvilGUIInterface;
import com.webmets.forceop.anvilgui.nms.AnvilHandlerInterface;


public class AnvilHandler implements AnvilHandlerInterface {
	public AnvilGUIInterface createNewGUI(AnvilGuiCore plugin, Player player, AnvilClickEventHandler handler) {
		return new AnvilGui(plugin, player, handler);
	}
}
