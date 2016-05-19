package es.projectalpha.scc.backpacks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.utils.NumberUtils;

public class BackpackCrafts {

	private static List<String> lore = new ArrayList<String>();

	public static void registerCrafts(){
		backpackKey();
		smallBackpack();
		mediumBackpack();
		bigBackpack();
	}

	private static void backpackKey(){
		ItemStack i = new ItemStack(Material.TRIPWIRE_HOOK);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + "Llave Mochila");
		lore.clear();
		lore.add(ChatColor.GRAY + "Llave de cualquier mochila propia");
		im.setLore(lore);
		i.setItemMeta(im);

		ShapedRecipe bp = new ShapedRecipe(i);

		bp.shape(new String[] { "aaa", "ala", "aca" });

		bp.setIngredient('l', Material.IRON_INGOT);
		bp.setIngredient('c', Material.WOOD_BUTTON);

		Bukkit.getServer().addRecipe(bp);
	}

	private static void smallBackpack(){
		ItemStack i = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + "Mochila pequeña");
		setLore();
		im.setLore(lore);
		i.setItemMeta(im);

		ShapedRecipe bp = new ShapedRecipe(i);

		bp.shape(new String[] { "lll", "lcl", "lll" });

		bp.setIngredient('l', Material.LEATHER);
		bp.setIngredient('c', Material.CHEST);

		Bukkit.getServer().addRecipe(bp);
	}

	private static void mediumBackpack(){
		ItemStack i = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + "Mochila mediana");
		setLore();
		im.setLore(lore);
		i.setItemMeta(im);

		ShapedRecipe bp = new ShapedRecipe(i);

		bp.shape(new String[] { "lal", "lcl", "lll" });

		bp.setIngredient('l', Material.IRON_INGOT);
		bp.setIngredient('a', Material.WOOL);
		bp.setIngredient('c', Material.CHEST);

		Bukkit.getServer().addRecipe(bp);
	}

	private static void bigBackpack(){
		ItemStack i = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + "Mochila grande");
		setLore();
		im.setLore(lore);
		i.setItemMeta(im);

		ShapedRecipe bp = new ShapedRecipe(i);

		bp.shape(new String[] { "lal", "lcl", "lll" });

		bp.setIngredient('l', Material.DIAMOND);
		bp.setIngredient('a', Material.WOOL);
		bp.setIngredient('c', Material.CHEST);

		Bukkit.getServer().addRecipe(bp);
	}

	private static void setLore(){
		int id = SurvivalClanCore.en.getInt("mochilaID");
		SurvivalClanCore.en.set("mochilaID", id + 1);

		try {
			SurvivalClanCore.en.save(SurvivalClanCore.file);
			SurvivalClanCore.en.load(SurvivalClanCore.file);
		} catch (IOException | InvalidConfigurationException e1) {
			e1.printStackTrace();
		}

		lore.clear();
		lore.add(NumberUtils.parseString(id + 1));
		lore.add(ChatColor.GRAY + "Mochila");
		lore.add(ChatColor.RED + "Propiedad de");
		lore.add(ChatColor.YELLOW + "Nadie");
	}
}
