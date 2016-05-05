package es.projectalpha.scc.events;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.minecraft.server.v1_9_R1.EntityPlayer;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.cevents.PlayerElytraDisableEvent;
import es.projectalpha.scc.cevents.PlayerElytraEnableEvent;
import es.projectalpha.scc.cevents.PlayerElytraFlyEvent;

public class ElytraEvents implements Listener {

	private List<UUID> flyingList;
	private SurvivalClanCore plugin;

	public ElytraEvents(SurvivalClanCore Main){
		Main.getServer().getPluginManager().registerEvents(this, this.plugin);
		this.flyingList = new ArrayList<UUID>();
		this.plugin = Main;
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e){
		EntityPlayer player = ((CraftPlayer) e.getPlayer()).getHandle();
		if (isFlying(player)) {
			if (this.flyingList.contains(e.getPlayer().getUniqueId())) {
				PlayerElytraFlyEvent elytraFlyEvent = new PlayerElytraFlyEvent(e.getPlayer(), e.getFrom(), e.getTo());
				this.plugin.getServer().getPluginManager().callEvent(elytraFlyEvent);

				e.setCancelled(elytraFlyEvent.isCancelled());
			} else {
				PlayerElytraEnableEvent elytraEnableEvent = new PlayerElytraEnableEvent(e.getPlayer());
				this.plugin.getServer().getPluginManager().callEvent(elytraEnableEvent);
				if (elytraEnableEvent.isCancelled()) {
					setFlying(player, false);
				} else {
					this.flyingList.add(e.getPlayer().getUniqueId());
				}
			}
		} else if (this.flyingList.contains(e.getPlayer().getUniqueId())) {
			PlayerElytraDisableEvent elytraDisableEvent = new PlayerElytraDisableEvent(e.getPlayer());
			this.plugin.getServer().getPluginManager().callEvent(elytraDisableEvent);
			if (elytraDisableEvent.isCancelled()) {
				setFlying(player, true);
			} else {
				this.flyingList.remove(e.getPlayer().getUniqueId());
			}
		}
	}

	private void setFlying(EntityPlayer player, boolean flying){
		player.setFlag(7, flying);
	}

	private boolean isFlying(EntityPlayer player){
		return player.cB();
	}
}
