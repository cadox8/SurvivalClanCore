package es.projectalpha.scc;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import es.projectalpha.scc.api.TabAPI;
import es.projectalpha.scc.cmd.AddEffects;
import es.projectalpha.scc.cmd.AirDropCMD;
import es.projectalpha.scc.cmd.Ayuda;
import es.projectalpha.scc.cmd.Maintenance;
import es.projectalpha.scc.cmd.Razas;
import es.projectalpha.scc.cmd.Report;
import es.projectalpha.scc.cmd.Rol;
import es.projectalpha.scc.cmd.Social;
import es.projectalpha.scc.cmd.WikiTonterias;
import es.projectalpha.scc.events.AirDropEvents;
import es.projectalpha.scc.events.Damage;
import es.projectalpha.scc.events.Effects;
import es.projectalpha.scc.events.Join;
import es.projectalpha.scc.events.Leave;
import es.projectalpha.scc.events.Move;
import es.projectalpha.scc.events.Talk;
import es.projectalpha.scc.signs.JoinRaza;
import es.projectalpha.scc.utils.Messages;
import es.projectalpha.scc.utils.Prefix;
import es.projectalpha.scc.utils.Teams;

public class SurvivalClanCore extends JavaPlugin {

	public Scoreboard sb;
	public Objective obj;
	public Team admin;
	public Team mod;
	public Team helper;
	public Team builder;
	public Team developer;
	public Team def;
	public Team vip;
	public Team origin;
	public Team yt;
	public Team nomada;
	public Teams teams;
	public static Prefix prefix;

	public static SurvivalClanCore pl;

	public void onEnable(){

		regEvents();
		regCommands();

		this.sb = Bukkit.getScoreboardManager().getNewScoreboard();
		this.obj = this.sb.registerNewObjective("dummy", "inicio");

		this.teams = new Teams(this);
		if (this.teams != null) {
			this.teams.registerTeams();
			this.teams.setPrefixesAndSuffixes();
		}
		SurvivalClanCore.prefix = new Prefix(this);

		Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
			public void run(){
				prefix.refreshPrefix();

				for (Player p : Bukkit.getOnlinePlayers()) {
					//	Info.setScore(p);

					String capi1 = ChatColor.BOLD.toString() + ChatColor.AQUA + " ProjectAlpha ";
					String capi2 = ChatColor.BOLD.toString() + ChatColor.RED + " ProjectAlpha ";
					String capi3 = ChatColor.BOLD.toString() + ChatColor.DARK_AQUA + " ProjectAlpha ";
					String capi4 = ChatColor.BOLD.toString() + ChatColor.GREEN + " ProjectAlpha ";
					String g = ChatColor.BOLD.toString() + ChatColor.GOLD + ChatColor.MAGIC + "|||";

					String ip1 = ChatColor.GREEN + "IP: " + ChatColor.AQUA + "projectalpha.es";
					String ip2 = ChatColor.LIGHT_PURPLE + "IP: " + ChatColor.DARK_GRAY + "projectalpha.es";

					double id = Math.random();

					if (id < 0.3) {
						TabAPI.sendTabTitle(p, "  " + g + capi1 + g, ip2);
					}
					if (id < 0.6 && id >= 0.3) {
						TabAPI.sendTabTitle(p, "  " + g + capi2 + g, ip1);
					}
					if (id >= 0.5 && id < 0.7) {
						TabAPI.sendTabTitle(p, "  " + g + capi3 + g, ip2);
					}
					if (id >= 0.7) {
						TabAPI.sendTabTitle(p, "  " + g + capi4 + g, ip1);
					}
				}
			}
		}, 0L, 20L);

		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "Activado");
	}

	public ArrayList<String> teleport = new ArrayList<String>();

	public void onDisable(){
		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.RED + "Desactivado");
	}

	private void regEvents(){
		//Razas
		new Damage(this);
		new Move(this);
		new Join(this);
		new Leave(this);

		//EXP
		//	new Throw(this);

		//Carteles
		new JoinRaza(this);

		//Chat
		new Talk(this);

		//Test
		new Effects(this);

		new AirDropEvents(this);
	}

	private void regCommands(){
		//Razas
		getCommand("raza").setExecutor(new Razas(this));
		//Mantenimiento
		getCommand("mantenimiento").setExecutor(new Maintenance());
		//Efectos
		getCommand("efecto").setExecutor(new AddEffects());
		//Ayuda
		getCommand("ayuda").setExecutor(new Ayuda(this));
		//Rol
		getCommand("emociones").setExecutor(new Rol());
		getCommand("besar").setExecutor(new Rol());
		getCommand("abrazar").setExecutor(new Rol());
		getCommand("golpear").setExecutor(new Rol());
		getCommand("mirar").setExecutor(new Rol());
		getCommand("mirarm").setExecutor(new Rol());
		getCommand("mirars").setExecutor(new Rol());
		getCommand("poke").setExecutor(new Rol());
		getCommand("vomitar").setExecutor(new Rol());
		getCommand("lanzar").setExecutor(new Rol());
		getCommand("reir").setExecutor(new Rol());
		//WikiTonterias
		getCommand("good").setExecutor(new WikiTonterias());
		getCommand("cadox8").setExecutor(new WikiTonterias());
		getCommand("purpurina").setExecutor(new WikiTonterias());
		getCommand("enfado").setExecutor(new WikiTonterias());
		//Report
		getCommand("avisar").setExecutor(new Report());
		//Social
		getCommand("web").setExecutor(new Social());
		getCommand("ts").setExecutor(new Social());
		getCommand("twitter").setExecutor(new Social());
		getCommand("youtube").setExecutor(new Social());
		getCommand("foro").setExecutor(new Social());
		//AirDrop
		getCommand("airdrop").setExecutor(new AirDropCMD());
	}

	public static SurvivalClanCore getSCC(){
		return pl;
	}
}