package es.projectalpha.scc.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.utils.AirDrop;
import es.projectalpha.scc.utils.Messages;

public class AirDropEvents implements Listener {

	private SurvivalClanCore plugin;

	public AirDropEvents(SurvivalClanCore Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	private int y3 = 13;

	private int f;

	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		Player p = e.getPlayer();
		Block b = e.getBlock();
		ItemStack i = e.getItemInHand();

		final World w = e.getBlock().getWorld();
		final double y = e.getBlock().getLocation().getY();
		final double x = e.getBlock().getLocation().getX();
		final double z = e.getBlock().getLocation().getZ();

		long id = AirDrop.getCrateKey();
		String type = "";

		if (b.getType() == i.getType()) {
			if (b.getType().equals(Material.ENDER_CHEST)) {

				for (int y2 = 0; y2 < y3; y2++) {
					if (w.getHighestBlockYAt(new Location(w, x, y, z)) <= 13) {
						p.sendMessage(Messages.prefix + ChatColor.RED + "Debes estar al aire libre para hacer esto");
						return;
					}
				}

				if (i.getItemMeta().getDisplayName().equalsIgnoreCase(Messages.cSmall)) {
					e.setCancelled(true);

					AirDrop.cSmall.add(p);
					AirDrop.lSmall.put(e.getBlock().getLocation(), id);

					type = "Pequeño";
				}

				if (i.getItemMeta().getDisplayName().equalsIgnoreCase(Messages.cMedium)) {
					e.setCancelled(true);

					AirDrop.cMedium.add(p);
					AirDrop.lMedium.put(e.getBlock().getLocation(), id);

					type = "Mediano";
				}

				if (i.getItemMeta().getDisplayName().equalsIgnoreCase(Messages.cBig)) {
					e.setCancelled(true);

					AirDrop.cBig.add(p);
					AirDrop.lBig.put(e.getBlock().getLocation(), id);

					type = "Grande";
				}

				p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));

				List<String> lore = new ArrayList<String>();

				lore.clear();
				lore.add(id + "");

				ItemStack k = new ItemStack(Material.TRIPWIRE_HOOK);
				ItemMeta km = k.getItemMeta();
				km.setDisplayName(ChatColor.YELLOW + "Llave AirDrop " + type);
				km.setLore(lore);
				k.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
				km.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				k.setItemMeta(km);

				p.getInventory().setItemInMainHand(k);

				f = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
					@Override
					public void run(){

						y3--;

						w.getBlockAt((int) x, (int) y + y3, (int) z).setType(Material.ENDER_CHEST);
						w.getBlockAt((int) x, (int) y + (y3 + 1), (int) z).setType(Material.AIR);

						w.playEffect(new Location(w, x, y, z), Effect.FIREWORK_SHOOT, 5);
						w.playEffect(new Location(w, x, y, z), Effect.FIREWORK_SHOOT, 5);
						w.playEffect(new Location(w, x, y, z), Effect.FIREWORK_SHOOT, 5);

						w.playEffect(new Location(w, x, y + y3, z), Effect.FLAME, 10);
						w.playEffect(new Location(w, x, y + y3, z), Effect.FLAME, 10);
						w.playEffect(new Location(w, x, y + y3, z), Effect.FLAME, 10);

						if (y3 == 0) {
							w.playEffect(new Location(w, x, y, z), Effect.FOOTSTEP, 20);
							w.playSound(new Location(w, x, y, z), Sound.BLOCK_ANVIL_FALL, 10.0F, 10.0F);
							y3 = 13;
							Bukkit.getScheduler().cancelTask(f);
						}
					}
				}, 0L, 10L);
			}
		}
	}

	@EventHandler
	public void onClick(PlayerInteractEvent e){
		Block b = e.getClickedBlock();
		Player p = e.getPlayer();
		long id;

		boolean lucky = false;
		boolean unlucky = false;
		int lvlLucky = 0;
		int lvlUnlucky = 0;
		ArrayList<ItemStack> item;

		if (AirDrop.cSmall.contains(p) || AirDrop.cMedium.contains(p) || AirDrop.cBig.contains(p)) {

			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasLore()) {

					if (b.getType() != Material.ENDER_CHEST) {
						return;
					}

					if (!p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Llave AirDrop ")) {
						p.sendMessage(Messages.prefix + ChatColor.RED + "Tienes que abrirlo con la llave");
						return;
					}

					e.setCancelled(true);

					Location l = e.getClickedBlock().getLocation();

					id = Long.parseLong(p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0));

					if (p.getInventory().firstEmpty() == -1) {
						p.sendMessage(Messages.prefix + ChatColor.RED + "Tienes el inventario lleno...");
						return;
					}

					if (p.hasPotionEffect(PotionEffectType.LUCK)) {
						lucky = true;

						for (PotionEffect pe : p.getActivePotionEffects()) {
							if (pe.getType() == PotionEffectType.LUCK) {
								lvlLucky = pe.getAmplifier();
							}
						}
					}
					if (p.hasPotionEffect(PotionEffectType.UNLUCK)) {
						unlucky = true;

						for (PotionEffect pe : p.getActivePotionEffects()) {
							if (pe.getType() == PotionEffectType.UNLUCK) {
								lvlUnlucky = pe.getAmplifier();
							}
						}
					}

					if (AirDrop.cSmall.contains(p)) {
						if (AirDrop.lSmall.containsKey(l)) {
							if (AirDrop.lSmall.get(l) == id) {
								AirDrop.lSmall.remove(l);
								p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));

								l.getWorld().playEffect(l, Effect.LAVA_POP, 10);
								l.getWorld().playSound(l, Sound.BLOCK_ANVIL_HIT, 4.0F, 4.0F);

								p.sendMessage(Messages.prefix + ChatColor.GREEN + "Se te han dado los items");

								item = AirDrop.getItems(1, lucky, unlucky, lvlLucky, lvlUnlucky);

								for (int items = 0; items < item.size(); items++) {
									p.getInventory().addItem(item.get(items));
								}
							}
						}
					}

					if (AirDrop.cMedium.contains(p)) {
						if (AirDrop.lMedium.containsKey(l)) {
							if (AirDrop.lMedium.get(l) == id) {
								AirDrop.lMedium.remove(l);
								p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));

								l.getWorld().playEffect(l, Effect.LAVA_POP, 10);
								l.getWorld().playSound(l, Sound.BLOCK_ANVIL_HIT, 4.0F, 4.0F);

								p.sendMessage(Messages.prefix + ChatColor.GREEN + "Se te han dado los items");

								item = AirDrop.getItems(2, lucky, unlucky, lvlLucky, lvlUnlucky);

								for (int items = 0; items < item.size(); items++) {
									p.getInventory().addItem(item.get(items));
								}
							}
						}
					}

					if (AirDrop.cBig.contains(p)) {
						if (AirDrop.lBig.containsKey(l)) {
							if (AirDrop.lBig.get(l) == id) {
								AirDrop.lBig.remove(l);
								p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));

								l.getWorld().playEffect(l, Effect.LAVA_POP, 10);
								l.getWorld().playSound(l, Sound.BLOCK_ANVIL_HIT, 4.0F, 4.0F);

								p.sendMessage(Messages.prefix + ChatColor.GREEN + "Se te han dado los items");

								item = AirDrop.getItems(3, lucky, unlucky, lvlLucky, lvlUnlucky);

								for (int items = 0; items < item.size(); items++) {
									p.getInventory().addItem(item.get(items));
								}
							}
						}
					}
					e.getClickedBlock().setType(Material.AIR);
				}
			}
		}
	}
}
