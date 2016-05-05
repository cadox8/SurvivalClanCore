package es.projectalpha.scc.events;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.api.ActionBarAPI;
import es.projectalpha.scc.api.TitleAPI;
import es.projectalpha.scc.cmd.Maintenance;
import es.projectalpha.scc.utils.Messages;
import es.projectalpha.scc.utils.TeamsYML;

public class Join implements Listener {

	private SurvivalClanCore plugin;

	public Join(SurvivalClanCore Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	private int getRandomNumber(){
		Random r = new Random();
		return r.nextInt(3) + 1;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e){
		final Player p = e.getPlayer();
		String team = TeamsYML.getTeams().getString("players." + p.getName() + ".team");
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK);

		if (Maintenance.enMantenimiento == true && !p.hasPermission("mantenimiento.estar")) {
			p.kickPlayer(Messages.prefix + ChatColor.RED + "El servidor esta en mantenimiento, revisa el twitter para saber más" + ChatColor.AQUA + " @ProjectAlphaSV");
			return;
		}

		if (team == null) {
			List<String> PIT;
			List<String> a = TeamsYML.getTeams().getStringList("nomada.members");

			a.add(p.getName());
			TeamsYML.getTeams().set("nomada.members", a);
			TeamsYML.getTeams().set("players." + p.getName() + ".team", "nomada");
			PIT = TeamsYML.getTeams().getStringList("PIT");
			if (!PIT.contains(p.getName())) {
				PIT.add(p.getName());
			}
			TeamsYML.getTeams().set("PIT", PIT);
			TeamsYML.saveTeams();

			TitleAPI.sendTitle(p, 0, 5, 0, ChatColor.AQUA + "Bienvenido, " + ChatColor.RED + p.getName(), ChatColor.GREEN + "Tu raza es:" + ChatColor.GOLD + " nomada");

			p.sendMessage(Messages.prefix + ChatColor.GREEN + "Has entrado en la raza" + ChatColor.GOLD + " nomada");
		} else {
			TitleAPI.sendTitle(p, 0, 5, 0, ChatColor.AQUA + "Bienvenido, " + ChatColor.RED + p.getName(), ChatColor.GREEN + "Estas en la raza: " + ChatColor.GOLD + team);
		}

		e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.GOLD + p.getName() + ChatColor.DARK_AQUA + " ha entrado al servidor");

		p.sendMessage(Messages.prefix + ChatColor.AQUA + "Recuerda usar el /ayuda para obtener ayuda en el servidor");

		if (getRandomNumber() == 1) {
			ActionBarAPI.sendActionBar(p, Messages.prefix + ChatColor.DARK_AQUA + "Recuerda usar el /ayuda para obtener ayuda en el servidor");
		}
		if (getRandomNumber() == 2) {
			ActionBarAPI.sendActionBar(p, Messages.prefix + ChatColor.RED + "Si tienes alguna duda, no dudes en preguntar al staff");
		}
		if (getRandomNumber() == 3) {
			ActionBarAPI.sendActionBar(p, Messages.prefix + ChatColor.DARK_AQUA + "Recuerda usar el /ayuda para obtener ayuda en el servidor");
		}

		p.setScoreboard(this.plugin.sb);
		SurvivalClanCore.prefix.refreshPrefix();

		if (p.hasPermission("scc.admin")) {
			p.sendMessage(day + "");
		}

		if (day == 6) {

		}
	}
}
