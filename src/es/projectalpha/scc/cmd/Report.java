package es.projectalpha.scc.cmd;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import es.projectalpha.scc.utils.Messages;

public class Report implements CommandExecutor {

	public static File FileRep = new File("plugins/RolCore/Reportes.yml");
	public static File FileRepb = new File("plugins/RolCore/ReportesB.yml");

	public static YamlConfiguration rep = YamlConfiguration.loadConfiguration(FileRep);
	public static YamlConfiguration repb = YamlConfiguration.loadConfiguration(FileRepb);

	public static List<String> lRep = new ArrayList<String>();
	public static List<String> lRepB = new ArrayList<String>();

	public static HashMap<Player, Integer> avisos = new HashMap<Player, Integer>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p = (Player) sender;

		if (p.hasPermission("rolcore.admin")) {
			if ((cmd.getName().equalsIgnoreCase("avisar")) && ((sender instanceof Player))) {
				if (args.length == 0) {
					p.sendMessage(Messages.prefix + ChatColor.RED + "Usa: '/avisar <jugador> <motivo>'");
				}
				if (args.length >= 2) {
					Player pl = Bukkit.getPlayerExact(args[0]);

					StringBuilder msg = new StringBuilder();
					for (int i = 1; i < args.length; i++) {
						msg.append(args[i]).append(" ");
					}
					if (pl == null) {
						p.sendMessage(Messages.prefix + ChatColor.RED + "El jugador no esta online");
						return true;
					}
					if (avisos.get(pl) == null) {
						avisos.put(pl, Integer.valueOf(0));
					}
					if (((Integer) avisos.get(pl)).intValue() >= 3) {
						pl.kickPlayer(Messages.prefix + ChatColor.RED + "Has tenido más de tres avisos en el servidor. Recuerda leerte las normas o seras sancionado" + ChatColor.AQUA + " (Por " + p.getName() + ")");
						p.sendMessage(Messages.prefix + pl.getName() + ChatColor.GREEN + " ha sido kickeado por tener más de tres avisos");
						return true;
					}
					int times = ((Integer) avisos.get(pl)).intValue();
					avisos.remove(pl);
					avisos.put(pl, Integer.valueOf(times + 1));

					p.sendMessage(Messages.prefix + ChatColor.GREEN + "Has avisado a " + ChatColor.RED + pl.getName() + ChatColor.GREEN + " por " + ChatColor.WHITE + msg);
					pl.sendMessage(Messages.prefix + ChatColor.DARK_AQUA + p.getName() + ChatColor.DARK_GREEN + " te a avisado con: " + ChatColor.WHITE + msg);
				}
			}
		} else {
			Messages.NO_PERMS(p);
		}
		return false;
	}
}
