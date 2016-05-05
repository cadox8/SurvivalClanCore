package es.projectalpha.scc.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.projectalpha.scc.utils.Messages;

public class Maintenance implements CommandExecutor {

	public static boolean enMantenimiento = false;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("mantenimiento") && ((sender instanceof Player))) {
			if (p.hasPermission("mantenimiento.admin")) {
				if (args.length == 0) {
					p.sendMessage(Messages.prefix + ChatColor.RED + "Debes decir que quieres hacer. " + ChatColor.YELLOW + "/man <activar/desactivar>");
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("activar")) {
						if (enMantenimiento == false) {
							enMantenimiento = true;
							for (Player pl : Bukkit.getOnlinePlayers()) {
								if (!pl.hasPermission("mantenimiento.estar")) {
									pl.kickPlayer(Messages.prefix + ChatColor.RED + "El servidor esta en mantenimiento, revisa el twitter para saber más" + ChatColor.AQUA + " @ProjectAlphaSV");
								}
							}
							p.sendMessage(Messages.prefix + ChatColor.GREEN + "Se ha activado el modo mantenimiento");
						} else {
							p.sendMessage(Messages.prefix + ChatColor.RED + "El servidor ya esta en mantenimiento");
						}
					}
					if (args[0].equalsIgnoreCase("desactivar")) {
						if (enMantenimiento == true) {
							enMantenimiento = false;
							p.sendMessage(Messages.prefix + ChatColor.GREEN + "Se ha desactivado el modo mantenimiento");
						} else {
							p.sendMessage(Messages.prefix + ChatColor.RED + "El servidor ya no esta en mantenimiento");
						}
					}
				}
			} else {
				p.sendMessage(Messages.prefix + ChatColor.RED + "No tienes permisos para esto");
			}
		}
		return false;
	}
}
