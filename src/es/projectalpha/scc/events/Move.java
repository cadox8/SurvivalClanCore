package es.projectalpha.scc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.utils.Messages;

public class Move implements Listener {

	private SurvivalClanCore plugin;

	public Move(SurvivalClanCore Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		Player p = e.getPlayer();

		if (this.plugin.teleport.contains(p.getName())) {
			if ((e.getFrom().getX() != e.getTo().getX()) || (e.getFrom().getZ() != e.getTo().getZ()) || (e.getFrom().getY() != e.getTo().getY())) {
				this.plugin.teleport.remove(p.getName());
				Messages.TELEPORT_HAS_STOPPED(p);
				e.setCancelled(true);
			}
		}
	}
}
