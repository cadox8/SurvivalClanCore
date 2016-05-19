package es.projectalpha.scc.backpacks;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.utils.Messages;
import es.projectalpha.scc.utils.NumberUtils;

public class BackpackManager implements Listener {

	private SurvivalClanCore plugin;

	public static File fileInv = new File("plugins/SurvivalClanCore", "inventarios.yml");
	public static YamlConfiguration inv = YamlConfiguration.loadConfiguration(fileInv);

	public BackpackManager(SurvivalClanCore Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	public static HashMap<Player, Player> otherInvs = new HashMap<Player, Player>();

	private static HashMap<Player, Integer> openBPID = new HashMap<Player, Integer>();

	public static void saveInv(Player p, Inventory i){
		inv.set(p.getName(), null);
		saveInv();

		for (int x = 0; x < getInvSize(p); x++) {
			if (i.getItem(x) != null) {
				inv.set(p.getName() + "." + openBPID.get(p) + ".inv_" + x, i.getItem(x));
			}
		}
		saveInv();
	}

	public static void removeInv(Player p){
		inv.set(p.getName(), null);
		saveInv();
	}

	public static void loadInv(Player p){
		if (!p.getInventory().getChestplate().getItemMeta().getLore().get(3).equalsIgnoreCase(p.getName())) {
			p.sendMessage(Messages.prefix + ChatColor.RED + "No puedes abrir una mochila unida a otro dueño");
			return;
		}
		p.openInventory(getInventory(p));
	}

	public static void loadOtherInv(Player p, Player pl){
		p.openInventory(getInventory(pl));
	}

	private static Inventory getInventory(Player p){
		Inventory i = Bukkit.createInventory(null, getInvSize(p), ChatColor.RED + "Mochila");
		ItemStack[] item = i.getContents();

		openBPID.put(p, NumberUtils.parseInt(p.getInventory().getChestplate().getItemMeta().getLore().get(0)));

		if (inv.contains(p.getName() + "." + openBPID.get(p))) {
			for (int x = 0; x < getInvSize(p); x++) {
				item[x] = inv.getItemStack(p.getName() + "." + openBPID.get(p) + ".inv_" + x);
			}
			i.setContents(item);
			return i;
		} else {
			return i;
		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e){
		Player p = (Player) e.getPlayer();

		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.RED + "Mochila")) {
			if (otherInvs.containsKey(p)) {
				p = otherInvs.get(p);
				saveInv(p, e.getInventory());
				otherInvs.remove(p);
				openBPID.remove(p);
				return;
			}
			saveInv(p, e.getInventory());
			openBPID.remove(p);
		}
	}

	private static int getInvSize(Player p){
		if (p.getInventory().getChestplate().hasItemMeta() && p.getInventory().getChestplate().getItemMeta().hasLore()) {
			if (p.getInventory().getChestplate().getItemMeta().getLore().get(1).equalsIgnoreCase(ChatColor.GRAY + "Mochila")) {
				if (p.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE) {
					return 18;
				}
				if (p.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) {
					return 27;
				}
				if (p.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE) {
					return 36;
				}
			}
		}
		return 0;
	}

	private static void saveInv(){
		if (!fileInv.exists()) {
			fileInv.mkdir();
			try {
				inv.save(fileInv);
				inv.load(fileInv);
			} catch (IOException | InvalidConfigurationException e1) {
				e1.printStackTrace();
			}
		}
	}
}
