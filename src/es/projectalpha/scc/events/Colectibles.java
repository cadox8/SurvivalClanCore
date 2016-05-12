package es.projectalpha.scc.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.utils.MathsUtils;

public class Colectibles implements Listener {

	@SuppressWarnings("unused")
	private SurvivalClanCore plugin;

	public Colectibles(SurvivalClanCore Main){
		this.plugin = Main;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();

		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getInventory().getItemInMainHand() != null) {
				if (p.getInventory().getItemInMainHand().hasItemMeta()) {
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
