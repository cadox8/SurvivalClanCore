package es.projectalpha.scc.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import es.projectalpha.scc.utils.Messages;

public class AddEffects implements CommandExecutor {

	private String noPerms = Messages.prefix + ChatColor.RED + "No tienes permisos para hacer esto";

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Debes ser un jugador");

			return false;
		}
		if (!label.equalsIgnoreCase("efecto")) {
			return false;
		}
		Player p = (Player) sender;
		if ((!p.hasPermission("rolcore.admin")) && (!p.isOp())) {
			p.sendMessage(noPerms);

			return false;
		}
		if (p.getInventory().getItemInMainHand().getItemMeta() == null) {
			p.sendMessage(Messages.prefix + ChatColor.RED + "Debes tener un item");

			return false;
		}
		if (args.length == 0) {
			String[] missingArgs = { ChatColor.RED + "Error:", ChatColor.RED + "/efecto [poción]" };

			p.sendMessage(missingArgs);
			return false;
		}
		ItemStack item = p.getInventory().getItemInMainHand();
		ItemMeta itemMeta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		if (args.length > 1) {
			try {
				int checkIfInt = Integer.parseInt(args[1]);
				if (checkIfInt < 1) {
					p.sendMessage("Multiplicador deber ser entre 1 y 256.");
					return false;
				}
			} catch (NumberFormatException e) {
				p.sendMessage("Multiplicador deber ser entre 1 y 256.");
				return false;
			}

			if (itemMeta.hasLore()) {
				lore = itemMeta.getLore();
			}

			StringBuilder msg = new StringBuilder();
			for (int i = 0; i < args.length; i++) {
				msg.append(args[i]).append(" ");
			}

			lore.add(ChatColor.BLUE + msg.toString().toLowerCase());

			itemMeta.setLore(lore);
			item.setItemMeta(itemMeta);
			p.sendMessage(Messages.prefix + ChatColor.AQUA + "Efecto añadido");

			return true;
		}
		return false;
	}
}
