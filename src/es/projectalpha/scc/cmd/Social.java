package es.projectalpha.scc.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.projectalpha.scc.api.JsonAPI;
import es.projectalpha.scc.utils.Messages;

public class Social implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("twitter") && ((sender instanceof Player))) {
			if (args.length == 0) {
				JsonAPI.jsonURL(p, "Este es nuestro twitter [Click]", "@ProjectAlphaSV", ChatColor.WHITE, ChatColor.AQUA, "http://twitter.com/ProjectAlphaSV");
			}
		}
		if (cmd.getName().equalsIgnoreCase("youtube") && ((sender instanceof Player))) {
			if (args.length == 0) {
				JsonAPI.jsonURL(p, "Este es nuestro canal de youtube [Click]", "ProjectAlpha", ChatColor.WHITE, ChatColor.RED, "https://www.youtube.com/channel/UCZgDPvX_mcECVa7J3oDrfpg");
			}
		}
		if (cmd.getName().equalsIgnoreCase("web") && ((sender instanceof Player))) {
			if (args.length == 0) {
				JsonAPI.jsonURL(p, "Esta es nuestra web [Click]", "projectalpha.es", ChatColor.WHITE, ChatColor.GREEN, "http://projectalpha.es");
			}
		}
		if (cmd.getName().equalsIgnoreCase("ts") && ((sender instanceof Player))) {
			if (args.length == 0) {
				p.sendMessage(Messages.prefix + "Nuestro TeamSpeak es: " + ChatColor.GOLD + "projectalpha.es");
			}
		}
		if (cmd.getName().equalsIgnoreCase("foro") && ((sender instanceof Player))) {
			if (args.length == 0) {
				JsonAPI.jsonURL(p, "Este es nuestro foro [Click]", "projectalpha.es/nforo", ChatColor.WHITE, ChatColor.LIGHT_PURPLE, "http://projectalpha.es/nforo");
			}
		}
		return false;
	}
}
