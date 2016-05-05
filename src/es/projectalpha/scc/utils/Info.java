package es.projectalpha.scc.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Info {

	static ScoreboardManager manager = Bukkit.getScoreboardManager();
	static Scoreboard board = manager.getNewScoreboard();

	static Team a = board.registerNewTeam("g");

	static Objective info = board.registerNewObjective("Info", "dummy");

	public static void setScore(Player p){
		String team = TeamsYML.getTeams().getString("players." + p.getName() + ".team");
		List<String> b = TeamsYML.getTeams().getStringList(team + ".members");
		List<String> online = new ArrayList<String>();

		p.setScoreboard(manager.getNewScoreboard());
		board.clearSlot(DisplaySlot.SIDEBAR);

		for (Player pl : Bukkit.getOnlinePlayers()) {
			if (pl != null && b.contains(pl.getName())) {
				if (!online.contains(pl)) {
					online.add(pl.getName());
				}
			}
		}

		info.setDisplaySlot(DisplaySlot.SIDEBAR);
		info.setDisplayName(ChatColor.LIGHT_PURPLE + "Información");

		Score jugadores = info.getScore(ChatColor.AQUA + "Jugadores");
		jugadores.setScore(Bukkit.getOnlinePlayers().size());

		Score raza = info.getScore(ChatColor.RED + "Online Raza");
		raza.setScore(online.size());

		Score s = info.getScore(ChatColor.GRAY + "=============");
		s.setScore(-1);

		Score raza2 = info.getScore(ChatColor.GREEN + "Raza: " + ChatColor.YELLOW + team.toUpperCase());
		raza2.setScore(-2);

		p.setScoreboard(board);
	}
}
