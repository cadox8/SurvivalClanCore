package es.projectalpha.scc.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.projectalpha.scc.api.NPCAPI;

public class WikiTonterias implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("good") && ((sender instanceof Player))) {
			if (args.length == 0) {
				Bukkit.broadcastMessage(getMessage(p));
			}
		}

		if (cmd.getName().equalsIgnoreCase("npc") && ((sender instanceof Player))) {
			if (args.length == 1) {
				NPCAPI npc = new NPCAPI(args[0], p.getLocation());
				npc.destroy();
				npc.spawn();
			}
		}

		if (cmd.getName().equalsIgnoreCase("cadox8") && ((sender instanceof Player))) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.GREEN + "Oh, has encontrado un " + ChatColor.RED + "Easter Egg. " + ChatColor.DARK_AQUA + "Recuerda darme abrazos!");
			}
		}
		return false;
	}

	private String getMessage(Player p){
		int n = (int) (Math.random() * 10);

		if (n <= 2) {
			return ChatColor.RED + p.getName() + ChatColor.AQUA + " dice que el servidor esta on fire!";
		}
		if (n <= 4) {
			return ChatColor.RED + p.getName() + ChatColor.AQUA + " dice: ProjectAlpha, sooooooooogood";
		}
		if (n <= 6) {
			return ChatColor.RED + p.getName() + ChatColor.AQUA + " dice que el servidor esta SuperChachiMegaHiperGuay";
		}
		if (n <= 8) {
			return ChatColor.RED + p.getName() + ChatColor.AQUA + " dice que el servidor esta re bueno";
		}

		return ChatColor.RED + p.getName() + ChatColor.AQUA + " dice que el servidor esta sabrosón";
	}
}
