package es.projectalpha.scc.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.backpacks.BackpackManager;
import es.projectalpha.scc.utils.MathsUtils;

public class Interact implements Listener {

	@SuppressWarnings("unused")
	private SurvivalClanCore plugin;

	public Interact(SurvivalClanCore Main){
		this.plugin = Main;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();

		System.out.println("dsd");

		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getInventory().getItemInMainHand() != null) {
				if (p.getInventory().getItemInMainHand().hasItemMeta()) {
					if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Llave Mochila")) {
						e.setCancelled(true);
						BackpackManager.loadInv(p);
					}

					if (p.getInventory().getItemInMainHand().getType() == Material.LEATHER_CHESTPLATE) {
						if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Mochila pequeña")) {
							if (p.getInventory().getItemInMainHand().getItemMeta().getLore().get(3).equalsIgnoreCase(ChatColor.YELLOW + "Nadie")) {
								p.getInventory().getItemInMainHand().getItemMeta().getLore().set(3, ChatColor.YELLOW + p.getName());
								p.setHealth(p.getHealth() - 1);
							}
						}
					}

					if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == ChatColor.YELLOW + "Anillo de Doran") {
						p.sendMessage(ChatColor.AQUA + "El maná está en camino, espera...");
					}
					if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == ChatColor.DARK_GREEN + "@TP $random.tp") {
						MathsUtils.randomTeleport(p);
					}
					if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == ChatColor.GREEN + "Feliz San Valentín ^^") {
						p.setHealth(p.getMaxHealth());
					}
				}
			}
		}
	}
}
