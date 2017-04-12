package com.webmets.forceop.anvilgui.nms;

import org.bukkit.entity.Player;

public class AnvilClickEvent {
	private AnvilSlot slot;
	private String name;
	private String playerName;
	private boolean close = true;
	private boolean destroy = true;

	public AnvilClickEvent(AnvilSlot slot, String name, Player player) {
		this.slot = slot;
		this.name = name;
		this.playerName = player.getName();
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public AnvilSlot getSlot() {
		return this.slot;
	}

	public String getName() {
		return this.name;
	}

	public boolean getWillClose() {
		return this.close;
	}

	public void setWillClose(boolean close) {
		this.close = close;
	}

	public boolean getWillDestroy() {
		return this.destroy;
	}

	public void setWillDestroy(boolean destroy) {
		this.destroy = destroy;
	}

	public void destroy() {
		this.name = null;
		this.playerName = null;
		this.slot = null;
	}
}
