package es.projectalpha.scc.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import es.projectalpha.scc.utils.Messages;

public class WikiTonterias implements CommandExecutor {

	public static ArrayList<Player> t = new ArrayList<Player>();
	public static ArrayList<Player> pur = new ArrayList<Player>();
	public static ArrayList<Player> en = new ArrayList<Player>();

	private static List<String> lore = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("good") && ((sender instanceof Player))) {
			if (args.length == 0) {
				Bukkit.broadcastMessage(getMessage(p));
			}
		}
		if (cmd.getName().equalsIgnoreCase("purpurina") && ((sender instanceof Player))) {
			if (args.length == 0) {
				if (p.hasPermission("rolcore.vip")) {
					if (!pur.contains(p)) {
						pur.add(p);
					} else {
						pur.remove(p);
					}
				} else {
					Messages.NO_PERMS(p);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("enfado") && ((sender instanceof Player))) {
			if (args.length == 0) {
				if (p.hasPermission("rolcore.origin")) {
					if (!en.contains(p)) {
						en.add(p);
					} else {
						en.remove(p);
					}
				} else {
					Messages.NO_PERMS(p);
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("cadox8") && ((sender instanceof Player))) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.GREEN + "Oh, has encontrado un " + ChatColor.RED + "Easter Egg. " + ChatColor.DARK_AQUA + "Recuerda darme abrazos!");
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("particulas")) {
					if (p.hasPermission("rolcore.admin")) {
						if (!t.contains(p)) {
							t.add(p);
						} else {
							t.remove(p);
						}
					} else {
						p.sendMessage(Messages.prefix + ChatColor.RED + "Este es un comando de testeo, sorry");
					}
				}

				if (args[0].equalsIgnoreCase("reiniciar")) {
					if (p.hasPermission("rolcore.admin")) {
						if (!t.isEmpty()) {
							t.clear();
						}
						if (!pur.isEmpty()) {
							pur.clear();
						}
						if (!en.isEmpty()) {
							en.clear();
						}
					} else {
						p.sendMessage(Messages.prefix + ChatColor.RED + "Este es un comando de testeo, sorry");
					}
				}
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

		return ChatColor.RED + p.getName() + ChatColor.AQUA + " dice que el servidor esta sabrosÃ³n";
	}

	public static ItemStack createValentine(){
		if (!lore.isEmpty()) {
			lore.clear();
		}
		lore.add(ChatColor.GREEN + "Ya que nadie os va a regalar nada");
		lore.add(ChatColor.GOLD + "cadox8 " + ChatColor.GREEN + "os regala esta preciosa rosa");
		lore.add(ChatColor.LIGHT_PURPLE + "â?¤ â?¤â?¤â?¤â?¤â?¤â?¤â?¤â?¤â?¤â?¤ ");

		ItemStack i = new ItemStack(Material.RED_ROSE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Rosa de San ValentÃ­n");
		im.setLore(lore);
		i.setItemMeta(im);

		return i;
	}
}
