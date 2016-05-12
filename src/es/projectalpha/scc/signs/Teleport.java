package es.projectalpha.scc.signs;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.utils.MathsUtils;

public class Teleport implements Listener {

	private SurvivalClanCore plugin;

	public Teleport(SurvivalClanCore Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onSignChange(SignChangeEvent e){
		Player p = e.getPlayer();

		if (p.hasPermission("rolcore.admin")) {
			if (e.getLine(0).equalsIgnoreCase("{TP}")) {
				e.setLine(0, ChatColor.GREEN + "TP Random");
				e.setLine(1, ChatColor.AQUA + "***************");
				e.setLine(2, ChatColor.DARK_PURPLE + "Click");
				e.setLine(3, ChatColor.AQUA + "***************");
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && ((e.getClickedBlock().getState() instanceof Sign))) {
			Sign s = (Sign) e.getClickedBlock().getState();

			if (s.getLine(0).equalsIgnoreCase(ChatColor.GREEN + "TP Random")) {
				MathsUtils.randomTeleport(p);
			}
		}
	}
}
