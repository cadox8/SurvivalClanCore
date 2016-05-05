package es.projectalpha.scc.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import es.projectalpha.scc.SurvivalClanCore;

public class TeamsYML {

	@SuppressWarnings("unused")
	private static SurvivalClanCore plugin;

	public TeamsYML(SurvivalClanCore Main){
		plugin = Main;
	}

	public static YamlConfiguration team = null;
	public static File teamFile = null;

	public static void reloadTeams(){
		if (teamFile == null) {
			teamFile = new File(Bukkit.getPluginManager().getPlugin("SurvivalClanCore").getDataFolder(), "razas.yml");
		}
		team = YamlConfiguration.loadConfiguration(teamFile);

		InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("SurvivalClanCore").getResource("razas.yml");
		if (defConfigStream != null) {
			@SuppressWarnings("deprecation")
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			if ((!teamFile.exists()) || (teamFile.length() == 0L)) {
				team.setDefaults(defConfig);
			}
		}
	}

	public static FileConfiguration getTeams(){
		if (team == null) {
			reloadTeams();
		}
		return team;
	}

	public static void saveTeams(){
		if ((team == null) || (teamFile == null)) {
			return;
		}
		try {
			getTeams().save(teamFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			Bukkit.getLogger().log(Level.SEVERE, "Error al guardar la config de " + teamFile, ex);
		}
	}
}
