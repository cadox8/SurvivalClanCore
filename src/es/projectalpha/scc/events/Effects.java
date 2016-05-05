package es.projectalpha.scc.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import es.projectalpha.scc.SurvivalClanCore;

public class Effects implements Listener {

	private SurvivalClanCore plugin;

	public Effects(SurvivalClanCore Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void playerMoveEvent(PlayerMoveEvent e){
		Player p = e.getPlayer();
		PlayerInventory inv = p.getInventory();

		if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta() || !e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
			if (p.getActivePotionEffects().size() != 0) {
				for (PotionEffect pe : p.getActivePotionEffects()) {
					if (pe.getDuration() > 9700) {
						p.removePotionEffect(pe.getType());
					} else {
						return;
					}
					break;
				}
			} else {
				return;
			}
		}

		if (inv.getItemInMainHand() == null || !e.getPlayer().getInventory().getItemInMainHand().hasItemMeta() || !e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
			return;
		}

		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "velocidad 1 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 0, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "velocidad 2 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 1, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "velocidad 3 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 2, true, false));
		}

		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "regeneracion 1 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 9999999, 0, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "regeneracion 2 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 9999999, 1, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "regeneracion 3 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 9999999, 2, true, false));
		}

		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "fuerza 1 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999999, 0, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "fuerza 2 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999999, 1, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "fuerza 3 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999999, 2, true, false));
		}

		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "salto 1 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9999999, 0, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "salto 2 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9999999, 1, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "salto 3 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9999999, 2, true, false));
		}

		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "res. fuego 1 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9999999, 0, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "res. fuego 2 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9999999, 1, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "res. fuego 3 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9999999, 2, true, false));
		}

		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "resistencia 1 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999999, 0, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "resistencia 2 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999999, 1, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "resistencia 3 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999999, 2, true, false));
		}

		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "v. nocturna 1 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9999999, 0, true, false));
		}

		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "invisibilidad 1 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9999999, 0, true, false));
		}

		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "veneno 1 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 9999999, 0, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "veneno 2 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 9999999, 1, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "veneno 3 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 9999999, 2, true, false));
		}

		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "lentitud 1 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 9999999, 0, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "lentitud 2 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 9999999, 1, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "lentitud 3 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 9999999, 2, true, false));
		}

		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "wither 1 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 9999999, 0, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "wither 2 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 9999999, 1, true, false));
		}
		if (inv.getItemInMainHand().getItemMeta().getLore().contains(ChatColor.BLUE + "wither 3 ")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 9999999, 2, true, false));
		}
	}
}
