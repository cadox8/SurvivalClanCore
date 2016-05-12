package es.projectalpha.scc.signs;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.utils.Messages;
import es.projectalpha.scc.utils.TeamsYML;

public class JoinRaza implements Listener {

	private SurvivalClanCore plugin;

	public JoinRaza(SurvivalClanCore Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onSignChange(SignChangeEvent e){
		Player p = e.getPlayer();

		if (p.hasPermission("rolcore.admin")) {
			if (e.getLine(0).equalsIgnoreCase("{SCC}")) {
				if (TeamsYML.getTeams().getString(e.getLine(1)) != null) {
					String t = e.getLine(1);
					e.setLine(0, ChatColor.GREEN + "SCC");
					e.setLine(1, ChatColor.AQUA + "***************");
					e.setLine(2, ChatColor.DARK_PURPLE + "Entrar");
					e.setLine(3, t);
				} else {
					Messages.TEAM_DOES_NOT_EXIST(p);
					e.setCancelled(true);
					return;
				}
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && ((e.getClickedBlock().getState() instanceof Sign))) {
			Sign s = (Sign) e.getClickedBlock().getState();
			String team = TeamsYML.getTeams().getString("players." + p.getName() + ".team");
			List<String> a = TeamsYML.getTeams().getStringList(s.getLine(3) + ".members");
			List<String> b = TeamsYML.getTeams().getStringList(team + ".members");
			List<String> PIT;

			if (s.getLine(0).equalsIgnoreCase(ChatColor.GREEN + "SCC")) {
				if (team.toLowerCase().contentEquals("nomada")) {
					a.add(p.getName());
					b.remove(p.getName());
					TeamsYML.getTeams().set(team + ".members", null);
					TeamsYML.getTeams().set(team + ".members", b);
					TeamsYML.getTeams().set(s.getLine(3) + ".members", null);
					TeamsYML.getTeams().set(s.getLine(3) + ".members", a);
					TeamsYML.getTeams().set("players." + p.getName() + ".team", null);
					TeamsYML.getTeams().set("players." + p.getName() + ".team", s.getLine(3));
					PIT = TeamsYML.getTeams().getStringList("PIT");
					if (!PIT.contains(p.getName())) {
						PIT.add(p.getName());
					}
					TeamsYML.getTeams().set("PIT", PIT);
					TeamsYML.saveTeams();

					for (String t : a) {
						if (Bukkit.getPlayerExact(t) != null) {
							Player p2 = Bukkit.getPlayerExact(t);
							p2.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.GREEN + " ha entrado a tu raza");
						}
					}
					for (String t : b) {
						if (Bukkit.getPlayerExact(t) != null) {
							Player p2 = Bukkit.getPlayerExact(t);
							p2.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.GREEN + " ha salido de tu raza");
						}
					}

					p.sendMessage(Messages.prefix + ChatColor.GREEN + "Has entrado en la raza " + ChatColor.GOLD + s.getLine(3));

					p.getWorld().playEffect(p.getLocation(), Effect.PORTAL, 70);

				} else {
					p.sendMessage(Messages.prefix + ChatColor.RED + "Si no estas en nómada no puedes cambiarte de la raza");
				}
			}
		}
	}
}
