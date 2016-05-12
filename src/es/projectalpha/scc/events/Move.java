package es.projectalpha.scc.events;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.cmd.WikiTonterias;
import es.projectalpha.scc.utils.Messages;

public class Move implements Listener {

	private SurvivalClanCore plugin;

	public Move(SurvivalClanCore Main){
		this.plugin = Main;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

	@EventHandler
	public void onEntityToggleGlideEvent(EntityToggleGlideEvent event){
		if (((event.getEntity() instanceof Player)) && (playerCanSwim((Player) event.getEntity()))) {
			event.setCancelled(true);
		}
	}

	public boolean playerCanSwim(Player p){
		if ((p.getLocation().getBlock().getType() == Material.STATIONARY_WATER) && (p.getLocation().subtract(0.0D, 1, 0.0D).getBlock().getType() == Material.STATIONARY_WATER) && (p.getVehicle() == null)) {
			return true;
		}
		return false;
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		Player p = e.getPlayer();

		if (playerCanSwim(p)) {
			p.setGliding(true);
		}

		if (this.plugin.teleport.contains(p.getName())) {
			if ((e.getFrom().getX() != e.getTo().getX()) || (e.getFrom().getZ() != e.getTo().getZ()) || (e.getFrom().getY() != e.getTo().getY())) {
				this.plugin.teleport.remove(p.getName());
				Messages.TELEPORT_HAS_STOPPED(p);
				e.setCancelled(true);
			}
		}

		if (WikiTonterias.t.contains(p)) {
			p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX() + 2, p.getLocation().getY() + 2, p.getLocation().getZ()), Effect.LARGE_SMOKE, 600);
			p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX() - 2, p.getLocation().getY() + 2, p.getLocation().getZ()), Effect.LARGE_SMOKE, 600);

			p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ() + 2), Effect.LARGE_SMOKE, 600);
			p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ() - 2), Effect.LARGE_SMOKE, 600);
		}

		if (WikiTonterias.pur.contains(p)) {
			for (double x = 0.0; x < 3; x++) {
				for (double y = 0.0; y < 4; y++) {
					for (double z = 0.0; z < 3; z++) {
						p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX() + x, p.getLocation().getY() + y, p.getLocation().getZ() + z), Effect.COLOURED_DUST, 6000000);
						p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX() - x, p.getLocation().getY() + y, p.getLocation().getZ() - z), Effect.COLOURED_DUST, 6000000);
						p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX() + x - 0.1, p.getLocation().getY() + y, p.getLocation().getZ() - z + 0.1), Effect.COLOURED_DUST, 6000000);
						p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX() - x + 0.1, p.getLocation().getY() + y, p.getLocation().getZ() + z - 0.1), Effect.COLOURED_DUST, 6000000);
						p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()), Effect.COLOURED_DUST, 6000000);
					}
				}
			}
		}

		if (WikiTonterias.en.contains(p)) {
			for (double x = 0.0; x < 3; x++) {
				for (double y = 0.0; y < 4; y++) {
					for (double z = 0.0; z < 3; z++) {
						p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX() + x, p.getLocation().getY() + y, p.getLocation().getZ() + z), Effect.VILLAGER_THUNDERCLOUD, 6000000);
						p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX() - x, p.getLocation().getY() + y, p.getLocation().getZ() - z), Effect.VILLAGER_THUNDERCLOUD, 6000000);
						p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX() + x - 0.1, p.getLocation().getY() + y, p.getLocation().getZ() - z + 0.1), Effect.VILLAGER_THUNDERCLOUD, 6000000);
						p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX() - x + 0.1, p.getLocation().getY() + y, p.getLocation().getZ() + z - 0.1), Effect.VILLAGER_THUNDERCLOUD, 6000000);
						p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()), Effect.VILLAGER_THUNDERCLOUD, 6000000);
					}
				}
			}
		}
	}
}
