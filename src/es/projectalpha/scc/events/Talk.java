package es.projectalpha.scc.events;

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
	}
}