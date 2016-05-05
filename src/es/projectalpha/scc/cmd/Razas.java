package es.projectalpha.scc.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.utils.Messages;
import es.projectalpha.scc.utils.TeamsYML;

public class Razas implements CommandExecutor {

	private Map<String, Location> hqloc = new HashMap<String, Location>();

	public Map<String, Location> gethqLoc(){
		return this.hqloc;
	}

	public void sethqLoc(Map<String, Location> hqloc){
		this.hqloc = hqloc;
	}

	private Map<String, Location> rallyloc = new HashMap<String, Location>();

	public Map<String, Location> getrallyLoc(){
		return this.rallyloc;
	}

	public void setrallyLoc(Map<String, Location> rallyloc){
		this.rallyloc = rallyloc;
	}

	private List<String> teamList = new ArrayList<String>();
	private SurvivalClanCore main;

	public List<String> getteamList(){
		return this.teamList;
	}

	public void setteamList(List<String> teamList){
		this.teamList = teamList;
	}

	public Razas(SurvivalClanCore instance){
		this.main = instance;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Solo puede hacer esto un jugador");
			return false;
		}
		final Player p = (Player) sender;
		if (label.equalsIgnoreCase("raza")) {
			if (args.length == 0) {
				Messages.TEAM_HELP(p);
			}
			if (args.length == 1) {
				if ((args[0].equalsIgnoreCase("info")) || (args[0].equalsIgnoreCase("ayuda")) || (args[0].equalsIgnoreCase("ponbase")) || (args[0].equalsIgnoreCase("base"))) {
					if (args[0].equalsIgnoreCase("info")) {
						String team = TeamsYML.getTeams().getString("players." + p.getName() + ".team");
						if (team != null) {
							p.sendMessage(ChatColor.GRAY + "§4§m*--------------------------*");
							p.sendMessage(ChatColor.DARK_AQUA + "Información de la Raza:");
							p.sendMessage(ChatColor.GRAY + "Raza: " + ChatColor.DARK_AQUA + team);
							p.sendMessage(ChatColor.GRAY + "Miembros: ");
							for (String s : TeamsYML.getTeams().getStringList(team + ".members")) {
								Player p2 = Bukkit.getPlayerExact(s);
								String s2 = s;
								if (p2 != null) {
									s2 = s2 + ChatColor.GREEN + "    (Online)";
								} else {
									s2 = s2 + ChatColor.RED + "   (Offline)";
								}
								p.sendMessage("- " + ChatColor.AQUA + s2);
							}
							p.sendMessage(ChatColor.GRAY + "§4§m*--------------------------*");
						} else {
							Messages.NOT_IN_A_TEAM(p);
						}
					}
					if (args[0].equalsIgnoreCase("ayuda")) {
						Messages.TEAM_HELP(p);
					}
				} else {
					Messages.TEAM_HELP(p);
				}
				if (args[0].equalsIgnoreCase("base")) {
					final String team = TeamsYML.getTeams().getString("players." + p.getName() + ".team");
					if (team != null) {
						Messages.GOING_TO_HQ(p);
						this.main.teleport.add(p.getName());
						Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(this.main, new Runnable() {
							public void run(){
								if (Razas.this.main.teleport.contains(p.getName())) {

									double x = TeamsYML.getTeams().getDouble(team + ".base.X");
									double y = TeamsYML.getTeams().getDouble(team + ".base.Y");
									double z = TeamsYML.getTeams().getDouble(team + ".base.Z");
									String w = TeamsYML.getTeams().getString(team + ".base.World");
									Location l = new Location(Bukkit.getWorld(w), x, y, z);

									p.teleport(l);

									Razas.this.main.teleport.remove(p.getName());
									Messages.TELEPORTED_TO_HQ(p);
								} else {}
							}
						}, 100L);
					} else {
						Messages.NOT_IN_A_TEAM(p);
					}
				}
			}
			if (args.length == 2) {
				if ((args[0].equalsIgnoreCase("info")) || (args[0].equalsIgnoreCase("echar")) || (args[0].equalsIgnoreCase("ponbase")) || (args[0].equalsIgnoreCase("crear")) || (args[0].equalsIgnoreCase("jugador")) || (args[0].equalsIgnoreCase("borrar"))) {
					if (args[0].equalsIgnoreCase("info")) {
						String team = args[1];
						if ((args[1] != null) && (TeamsYML.getTeams().getString(team) != null)) {
							p.sendMessage(ChatColor.GRAY + "=======================");
							p.sendMessage(ChatColor.DARK_AQUA + "Información de Razas:");
							p.sendMessage(ChatColor.GRAY + "Raza: " + ChatColor.DARK_AQUA + team);
							p.sendMessage(ChatColor.GRAY + "Miembros: ");
							for (String s : TeamsYML.getTeams().getStringList(team + ".members")) {
								Player p2 = Bukkit.getPlayerExact(s);
								String s2 = s;
								if (p2 != null) {
									s2 = s2 + ChatColor.GREEN + "    (Online)";
								} else {
									s2 = s2 + ChatColor.RED + "   (Offline)";
								}
								p.sendMessage("- " + ChatColor.AQUA + s2);
							}
							p.sendMessage(ChatColor.GRAY + "=======================");
						} else {
							Messages.TEAM_DOES_NOT_EXIST(p);
						}
					}
					if (args[0].equalsIgnoreCase("jugador")) {
						Player pl = Bukkit.getPlayerExact(args[1]);
						String team = TeamsYML.getTeams().getString("players." + pl.getName() + ".team");

						p.sendMessage(ChatColor.GRAY + "=======================");
						p.sendMessage(ChatColor.DARK_AQUA + "Información de " + pl.getName() + ":");
						p.sendMessage(ChatColor.GRAY + "Raza: " + ChatColor.DARK_AQUA + team);
						p.sendMessage(ChatColor.GRAY + "=======================");
					}
					if (args[0].equalsIgnoreCase("borrar")) {
						if (p.hasPermission("rolcore.admin")) {
							Player pl = Bukkit.getPlayerExact(args[1]);
							String team = TeamsYML.getTeams().getString("players." + pl.getName() + ".team");
							List<String> PIT;

							if (pl != null) {
								List<String> b = TeamsYML.getTeams().getStringList(team + ".members");
								b.remove(pl.getName());
								TeamsYML.getTeams().set(team + ".members", null);
								TeamsYML.getTeams().set(team + ".members", b);
								TeamsYML.getTeams().set("players." + pl.getName() + ".team", null);
								PIT = TeamsYML.getTeams().getStringList("PIT");
								if (!PIT.contains(pl.getName())) {
									PIT.add(pl.getName());
								} else {
									PIT.remove(pl.getName());
								}
								TeamsYML.getTeams().set("PIT", PIT);
								TeamsYML.saveTeams();

								p.kickPlayer(Messages.prefix + ChatColor.RED + "Has sido borrado de las razas, entra de nuevo al servidor y podras volver a jugar con normalidad. Siento los bugs ^Cadox8");
							}
						} else {
							Messages.NO_PERMS(p);
						}
					}
					if (args[0].equalsIgnoreCase("ponbase")) {
						String team = args[1];
						if (team != null) {
							if (p.hasPermission("razas.admin")) {
								this.hqloc.put(team, p.getLocation());

								TeamsYML.getTeams().set(team + ".base.X", p.getLocation().getX());
								TeamsYML.getTeams().set(team + ".base.Y", p.getLocation().getY());
								TeamsYML.getTeams().set(team + ".base.Z", p.getLocation().getZ());
								TeamsYML.getTeams().set(team + ".base.World", p.getLocation().getWorld().getName());
								TeamsYML.saveTeams();

								Messages.TEAM_HQ_SET(p);
							} else {
								Messages.NO_PERMS(p);
							}
						}
					}
					if (args[0].equalsIgnoreCase("echar")) {
						Player pl = Bukkit.getPlayerExact(args[1]);
						String team = TeamsYML.getTeams().getString("players." + pl.getName() + ".team");
						List<String> PIT;

						if (pl != null) {
							if (p.hasPermission("razas.admin")) {
								if (team.toLowerCase() != "nomada") {
									List<String> a = TeamsYML.getTeams().getStringList("nomada.members");
									List<String> b = TeamsYML.getTeams().getStringList(team + ".members");
									a.add(pl.getName());
									b.remove(pl.getName());
									TeamsYML.getTeams().set(team + ".members", null);
									TeamsYML.getTeams().set(team + ".members", b);
									TeamsYML.getTeams().set("nomada.members", null);
									TeamsYML.getTeams().set("nomada.members", a);
									TeamsYML.getTeams().set("players." + pl.getName() + ".team", null);
									TeamsYML.getTeams().set("players." + pl.getName() + ".team", "nomada");
									PIT = TeamsYML.getTeams().getStringList("PIT");
									if (!PIT.contains(pl.getName())) {
										PIT.add(pl.getName());
									}
									TeamsYML.getTeams().set("PIT", PIT);
									TeamsYML.saveTeams();
									for (String s : b) {
										if (Bukkit.getPlayerExact(s) != null) {
											Player p2 = Bukkit.getPlayerExact(s);
											p2.sendMessage(ChatColor.GRAY + pl.getName() + ChatColor.GREEN + " ha salido de tu raza");
										}
									}
									p.sendMessage(Messages.prefix + ChatColor.RED + "Has echado a " + ChatColor.AQUA + pl.getName() + ChatColor.RED + " de su raza");
								} else {
									p.sendMessage("Nope");
								}
							} else {
								Messages.NO_PERMS(p);
							}
						}
					}
					if (args[0].equalsIgnoreCase("crear")) {
						List<String> PIT;
						if (p.hasPermission("razas.admin")) {
							if (this.teamList.contains(args[1].toLowerCase())) {
								Messages.TEAM_CREATED_ALREADY(p);
							} else if (args[1] != null) {
								String team = TeamsYML.getTeams().getString("players." + p.getName() + ".team");

								this.teamList.add(args[1].toLowerCase());
								TeamsYML.getTeams().set("players." + p.getName() + ".team", args[1]);

								List<String> a = new ArrayList<String>();
								List<String> b = TeamsYML.getTeams().getStringList(team + ".members");
								a.add(p.getName());
								b.remove(p.getName());
								TeamsYML.getTeams().set(team + ".members", null);
								TeamsYML.getTeams().set(team + ".members", b);
								TeamsYML.getTeams().set(args[1] + ".members", null);
								TeamsYML.getTeams().set(args[1] + ".members", a);

								PIT = TeamsYML.getTeams().getStringList("PIT");
								if (!PIT.contains(p.getName())) {
									PIT.add(p.getName());
								}
								TeamsYML.getTeams().set("PIT", PIT);
								TeamsYML.saveTeams();

								Messages.TEAM_CREATED(p);
							} else {
								Messages.TEAM_CREATE_ARGS_ERROR(p);
							}
						} else {
							Messages.NO_PERMS(p);
						}
					}
				} else {
					Messages.TEAM_HELP(p);
				}
			}
			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("poner")) {
					List<String> PIT;
					if (p.hasPermission("razas.admin")) {
						Player pl = Bukkit.getPlayerExact(args[2]);
						String team = TeamsYML.getTeams().getString("players." + pl.getName() + ".team");

						if (TeamsYML.getTeams().getString(args[1]) != null) {
							List<String> a = TeamsYML.getTeams().getStringList(args[1] + ".members");
							List<String> b = TeamsYML.getTeams().getStringList(team + ".members");
							if (args[1] == team) {
								return true;
							}

							for (String s : a) {
								if (Bukkit.getPlayerExact(s) != null) {
									Player p2 = Bukkit.getPlayerExact(s);
									p2.sendMessage(ChatColor.GRAY + pl.getName() + ChatColor.GREEN + " ha entrado a tu raza");
								}
							}
							a.add(pl.getName());
							b.remove(pl.getName());
							TeamsYML.getTeams().set(team + ".members", null);
							TeamsYML.getTeams().set(team + ".members", b);
							TeamsYML.getTeams().set(args[1] + ".members", null);
							TeamsYML.getTeams().set(args[1] + ".members", a);
							TeamsYML.getTeams().set("players." + pl.getName() + ".team", null);
							TeamsYML.getTeams().set("players." + pl.getName() + ".team", args[1]);
							PIT = TeamsYML.getTeams().getStringList("PIT");
							if (!PIT.contains(pl.getName())) {
								PIT.add(pl.getName());
							}
							TeamsYML.getTeams().set("PIT", PIT);
							TeamsYML.saveTeams();

							Messages.JOINED_TEAM_SUCCESSFULLY(pl);
						} else {
							Messages.TEAM_DOES_NOT_EXIST(p);
						}
					} else {
						Messages.NO_PERMS(p);
					}
				}
			}
		}
		return false;
	}
}
