package es.projectalpha.scc.events;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.utils.Messages;
import es.projectalpha.scc.utils.TeamsYML;

public class Damage implements Listener {

	private SurvivalClanCore plugin;

	public Damage(SurvivalClanCore Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		Entity damaged = e.getEntity();
		Entity damager = e.getDamager();

		if (damaged instanceof Player && damager instanceof Player) {
			Player p = (Player) damaged;
			Player pl = (Player) damager;

			String team = TeamsYML.getTeams().getString("players." + p.getName() + ".team");

			List<String> members = TeamsYML.getTeams().getStringList("nomada.members");
			List<String> m = TeamsYML.getTeams().getStringList(team + ".members");

			if ((m.contains(p.getName())) && (m.contains(pl.getName()))) {
				e.setCancelled(true);
				Messages.SAME_TEAM(pl);
				return;
			}

			if (members.contains(p.getName())) {
				e.setCancelled(true);
				pl.sendMessage(Messages.prefix + ChatColor.RED + "No puedes pegar a nadie que esta en nómada");
				return;
			}
			if (members.contains(pl.getName())) {
				e.setCancelled(true);
				pl.sendMessage(Messages.prefix + ChatColor.RED + "No puedes pegar a nadie estando en nómada");
				return;
			}
		}

		if (damaged instanceof Player && damager instanceof Player) {
			DamageCause end = e.getCause();
			Player p = (Player) damaged;
			Player pl = (Player) damager;

			String team = TeamsYML.getTeams().getString("players." + p.getName() + ".team");

			List<String> members = TeamsYML.getTeams().getStringList("nomada.members");
			List<String> m = TeamsYML.getTeams().getStringList(team + ".members");

			if (end == DamageCause.PROJECTILE) {

				if ((m.contains(p.getName())) && (m.contains(pl.getName()))) {
					e.setCancelled(true);
					Messages.SAME_TEAM(pl);
					return;
				}

				if (members.contains(p.getName())) {
					e.setCancelled(true);
					pl.sendMessage(Messages.prefix + ChatColor.RED + "No puedes pegar a nadie que esta en nómada");
					return;
				}
				if (members.contains(pl.getName())) {
					e.setCancelled(true);
					pl.sendMessage(Messages.prefix + ChatColor.RED + "No puedes pegar a nadie estando en nómada");
					return;
				}
			}
		}
	}
}
