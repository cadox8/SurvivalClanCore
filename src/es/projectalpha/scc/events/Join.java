package es.projectalpha.scc.events;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.api.ActionBarAPI;
import es.projectalpha.scc.api.TitleAPI;
import es.projectalpha.scc.cmd.Maintenance;
import es.projectalpha.scc.utils.Messages;
import es.projectalpha.scc.utils.TeamsYML;

public class Join implements Listener {

	private SurvivalClanCore plugin;

	File file = new File("plugins/SurvivalClanCore/", "config.yml");
	YamlConfiguration en = YamlConfiguration.loadConfiguration(file);

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
		List<String> jugadores;

		if (Maintenance.enMantenimiento == true && !p.hasPermission("mantenimiento.estar")) {
			p.kickPlayer(Messages.prefix + ChatColor.RED + "El servidor esta en mantenimiento, revisa el twitter para saber más" + ChatColor.AQUA + " @ProjectAlphaSV");
			return;
		}

		if (!file.exists()) {
			file.mkdir();
			try {
				en.save(file);
				en.load(file);
			} catch (IOException | InvalidConfigurationException e1) {
				e1.printStackTrace();
			}
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

		if (day == 6) {
			jugadores = en.getStringList("jugadores");

			if (!jugadores.contains(p.getName())) {
				jugadores.add(p.getName());
				en.set("jugadores", jugadores);

				try {
					en.save(file);
					en.load(file);
				} catch (IOException | InvalidConfigurationException e1) {
					e1.printStackTrace();
				}

				ItemStack c = new ItemStack(Material.ENDER_CHEST);
				ItemMeta cm = c.getItemMeta();
				cm.setDisplayName(Messages.cSmall);
				c.setItemMeta(cm);
				p.getInventory().addItem(c);
				p.sendMessage(Messages.prefix + ChatColor.GREEN + "Se te ha dado el cofre " + ChatColor.RED + "pequeño");
			}
		}
	}
}
