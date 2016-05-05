package es.projectalpha.scc.cevents;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerElytraFlyEvent extends PlayerMoveEvent {

	private static HandlerList handlerList = new HandlerList();

	public PlayerElytraFlyEvent(Player player, Location from, Location to){
		super(player, from, to);
	}

	public HandlerList getHandlers(){
		return handlerList;
	}

	public static HandlerList getHandlerList(){
		return handlerList;
	}
}
