package es.projectalpha.scc.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {

	public static ItemStack getRing(){
		List<String> lore = new ArrayList<String>();

		lore.add(ChatColor.LIGHT_PURPLE + "Un anillo para dominarlos a todos");
		lore.add(ChatColor.GRAY + "Coleccionable");

		ItemStack i = new ItemStack(Material.WATCH);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.YELLOW + "Anillo de Doran");
		im.setLore(lore);
		i.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		i.setItemMeta(im);

		return i;
	}

	public static ItemStack getBone(){
		List<String> lore = new ArrayList<String>();

		lore.add(ChatColor.AQUA + "return items[r.nextInt(items.length)];");
		lore.add(ChatColor.GRAY + "Coleccionable");

		ItemStack i = new ItemStack(Material.BONE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.DARK_GREEN + "@TP $random.tp");
		im.setLore(lore);
		i.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		i.setItemMeta(im);

		return i;
	}

	public static ItemStack getPoppy(){
		List<String> lore = new ArrayList<String>();

		lore.add(ChatColor.YELLOW + "Una rosa de " + ChatColor.RED + "San Valentin");
		lore.add(ChatColor.GRAY + "Coleccionable");

		ItemStack i = new ItemStack(Material.RED_ROSE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + "Feliz San Valentín ^^");
		im.setLore(lore);
		i.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		i.setItemMeta(im);

		return i;
	}
}
