package es.projectalpha.scc.events;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.utils.TeamsYML;

public class Talk implements Listener {

	private SurvivalClanCore plugin;

	public Talk(SurvivalClanCore Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	public static int values;

	@EventHandler
	public void onTalk(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String team = TeamsYML.getTeams().getString("players." + p.getName() + ".team");
		String world = "";

		if (p.getWorld().getName().toLowerCase().equalsIgnoreCase("world")) {
			world = "SurvivalClan";
		}
		if (p.getWorld().getName().toLowerCase().equalsIgnoreCase("world_nether")) {
			world = "Nether";
		}
		if (p.getWorld().getName().toLowerCase().equalsIgnoreCase("world_the_end")) {
			world = "End";
		}
		if (p.getWorld().getName().toLowerCase().equalsIgnoreCase("recursos")) {
			world = "Recursos";
		}
		if (p.getWorld().getName().toLowerCase().equalsIgnoreCase("recursos_end")) {
			world = "End Recursos";
		}
		if (p.getWorld().getName().toLowerCase().equalsIgnoreCase("recursos_nether")) {
			world = "Nether Recursos";
		}
		if (p.getWorld().getName().toLowerCase().equalsIgnoreCase("eventos")) {
			world = "Eventos";
		}

		String chat = ChatColor.GRAY + "[" + ChatColor.RED + world + ChatColor.GRAY + "] " + ChatColor.GRAY + "[" + ChatColor.RED + team.toUpperCase() + ChatColor.GRAY + "] " + "%s" + ChatColor.RESET + ": " + "%s";

		e.setFormat(chat);

		if (e.getMessage().equalsIgnoreCase("gg")) {
			p.sendMessage(ChatColor.YELLOW + "* Buen Karma :D");
			e.setMessage(getGGMessage());
		}
		if (e.getMessage().equalsIgnoreCase("bg")) {
			p.sendMessage(ChatColor.YELLOW + "* Mal Karma D:");
			e.setMessage(getBDMessage());
		}
		if (e.getMessage().equalsIgnoreCase("ez")) {
			p.sendMessage(ChatColor.YELLOW + "* Mmmmm Platano(?)");
			e.setMessage(getEZMessage());
		}
		if (e.getMessage().equalsIgnoreCase("afk")) {
			e.setMessage(ChatColor.RED + "I'm AFK my team suck bye");
		}
	}

	public String getGGMessage(){
		Random r = new Random();

		switch (r.nextInt(6)) {
		case 0:
			return ChatColor.RED + "GG EZ WP";
		case 1:
			return ChatColor.GREEN + "Lo has hecho muy bien, good game";
		case 2:
			return ChatColor.AQUA + "N00b, learn2play";
		case 3:
			return ChatColor.YELLOW + "yiyi izi pizi, tu madre en bizi, lemon ezkuizi";
		case 4:
			return ChatColor.DARK_AQUA + "Not feed, only skill";
		case 5:
			return ChatColor.DARK_PURPLE + "I'm Bronze V";
		default:
			return ChatColor.AQUA + "N00b, learn2play";
		}
	}

	public String getBDMessage(){
		Random r = new Random();

		switch (r.nextInt(7)) {
		case 0:
			return ChatColor.RED + "GG EZ WP";
		case 1:
			return ChatColor.GREEN + "Lo has hecho muy bien, good game";
		case 2:
			return ChatColor.AQUA + "N00b, learn2play";
		case 3:
			return ChatColor.DARK_AQUA + "Report jungler for no help";
		case 4:
			return ChatColor.GRAY + "Report team noob don´t have skill";
		case 5:
			return ChatColor.DARK_BLUE + "My team suck report pls";
		case 6:
			return ChatColor.DARK_PURPLE + "Report bot for feed and the bot enemy for kill me";
		default:
			return ChatColor.AQUA + "N00b, learn2play";
		}
	}

	public String getEZMessage(){
		Random r = new Random();

		switch (r.nextInt(3)) {
		case 0:
			return ChatColor.RED + "This game is too easy";
		case 1:
			return ChatColor.GREEN + "Your team is so bad";
		case 2:
			return ChatColor.AQUA + "I'm Bronze V";
		default:
			return ChatColor.AQUA + "N00b, learn2play";
		}
	}
}