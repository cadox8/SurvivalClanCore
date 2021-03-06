package es.projectalpha.scc.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import es.projectalpha.scc.utils.Messages;

public class AirDropCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("airdrop") && ((sender instanceof Player))) {
			if (args.length == 0) {
				p.sendMessage(Messages.prefix + ChatColor.RED + "Debes seleccionar un cofre. " + ChatColor.YELLOW + "/airdrop <peque/mediano/grande>");
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("peque")) {
					ItemStack c = new ItemStack(Material.ENDER_CHEST);
					ItemMeta cm = c.getItemMeta();
					cm.setDisplayName(Messages.cSmall);
					c.setItemMeta(cm);
					p.getInventory().addItem(c);
					p.sendMessage(Messages.prefix + ChatColor.GREEN + "Se te ha dado el cofre " + ChatColor.RED + "pequeño");
				}
				if (args[0].equalsIgnoreCase("mediano")) {
					ItemStack c = new ItemStack(Material.ENDER_CHEST);
					ItemMeta cm = c.getItemMeta();
					cm.setDisplayName(Messages.cMedium);
					c.setItemMeta(cm);
					p.getInventory().addItem(c);
					p.sendMessage(Messages.prefix + ChatColor.GREEN + "Se te ha dado el cofre " + ChatColor.RED + "mediano");
				}
				if (args[0].equalsIgnoreCase("grande")) {
					ItemStack c = new ItemStack(Material.ENDER_CHEST);
					ItemMeta cm = c.getItemMeta();
					cm.setDisplayName(Messages.cBig);
					c.setItemMeta(cm);
					p.getInventory().addItem(c);
					p.sendMessage(Messages.prefix + ChatColor.GREEN + "Se te ha dado el cofre " + ChatColor.RED + "grande");
				}
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("items")) {
					List<String> lore = new ArrayList<String>();
					lore.add(args[1]);

					ItemStack c = new ItemStack(Material.BLAZE_POWDER);
					ItemMeta cm = c.getItemMeta();
					cm.setDisplayName(ChatColor.DARK_AQUA + "AirDrop Items");
					cm.setLore(lore);
					c.setItemMeta(cm);
					p.getInventory().addItem(c);
					p.sendMessage(Messages.prefix + ChatColor.GREEN + "Se te ha dado el " + ChatColor.RED + "Selector de Items");
				}
			}
		}
		return false;
	}
}
