package es.projectalpha.scc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.minecraft.server.v1_9_R2.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_9_R2.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_9_R2.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_9_R2.PlayerConnection;

import es.projectalpha.scc.api.TPSAPI;
import es.projectalpha.scc.api.TabAPI;
import es.projectalpha.scc.backpacks.BackpackManager;
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
import es.projectalpha.scc.events.Interact;
import es.projectalpha.scc.events.Join;
import es.projectalpha.scc.events.Leave;
import es.projectalpha.scc.events.Level;
import es.projectalpha.scc.events.Move;
import es.projectalpha.scc.events.Talk;
import es.projectalpha.scc.npc.NPC;
import es.projectalpha.scc.signs.JoinRaza;
import es.projectalpha.scc.signs.Teleport;
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

	public static File file = new File("plugins/SurvivalClanCore/", "config.yml");
	public static YamlConfiguration en = YamlConfiguration.loadConfiguration(file);

	public void onEnable(){
		if (!file.exists()) {
			file.mkdir();
			en.set("mochilaID", 0);
			try {
				en.save(file);
				en.load(file);
			} catch (IOException | InvalidConfigurationException e1) {
				e1.printStackTrace();
			}
		}

		//	BackpackCrafts.registerCrafts();

		regEvents();
		regCommands();

		this.sb = Bukkit.getScoreboardManager().getNewScoreboard();
		this.obj = this.sb.registerNewObjective("scc", "dummy");

		this.teams = new Teams(this);
		if (this.teams != null) {
			this.teams.registerTeams();
			this.teams.setPrefixesAndSuffixes();
		}
		SurvivalClanCore.prefix = new Prefix(this);

		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new TPSAPI(), 100L, 1L);

		Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
			public void run(){
				prefix.refreshPrefix();

				for (Player p : Bukkit.getOnlinePlayers()) {

					for (int g = 0; g < NPC.npcList.size(); g++) {

						if (!NPC.npcList.containsKey(g)) {
							break;
						}

						PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
						connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, NPC.npcList.get(g)));
						connection.sendPacket(new PacketPlayOutNamedEntitySpawn(NPC.npcList.get(g)));
					}

					String capi1 = ChatColor.BOLD.toString() + ChatColor.AQUA + " ProjectAlpha ";
					String capi2 = ChatColor.BOLD.toString() + ChatColor.RED + " ProjectAlpha ";
					String capi3 = ChatColor.BOLD.toString() + ChatColor.DARK_AQUA + " ProjectAlpha ";
					String capi4 = ChatColor.BOLD.toString() + ChatColor.GREEN + " ProjectAlpha ";
					String g = ChatColor.BOLD.toString() + ChatColor.GOLD + ChatColor.MAGIC + "|||";

					String ip1 = ChatColor.GREEN + "IP: " + ChatColor.AQUA + "projectalpha.es" + ChatColor.GRAY + " Usuarios: " + ChatColor.LIGHT_PURPLE + Bukkit.getOnlinePlayers().size();
					String ip2 = ChatColor.LIGHT_PURPLE + "IP: " + ChatColor.DARK_GRAY + "projectalpha.es" + ChatColor.GRAY + " Usuarios: " + ChatColor.LIGHT_PURPLE + Bukkit.getOnlinePlayers().size();

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
		for (int g = 0; g < NPC.npcList.size(); g++) {
			NPC.removeNPC(g);
		}
		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.RED + "Desactivado");
	}

	private void regEvents(){
		//Razas
		new Damage(this);
		new Move(this);
		new Join(this);
		new Leave(this);
		new Interact(this);
		new Level(this);
		new BackpackManager(this);

		//EXP
		//	new Throw(this);

		//Carteles
		new JoinRaza(this);
		new Teleport(this);

		//Chat
		new Talk(this);

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
		getCommand("good").setExecutor(new WikiTonterias(this));
		getCommand("cadox8").setExecutor(new WikiTonterias(this));
		getCommand("purpurina").setExecutor(new WikiTonterias(this));
		getCommand("enfado").setExecutor(new WikiTonterias(this));
		getCommand("npc").setExecutor(new WikiTonterias(this));
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
