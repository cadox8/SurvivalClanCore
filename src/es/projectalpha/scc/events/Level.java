package es.projectalpha.scc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import es.projectalpha.scc.SurvivalClanCore;

public class Level {

	@SuppressWarnings("unused")
	private SurvivalClanCore plugin;

	public Level(SurvivalClanCore Main){
		this.plugin = Main;
	}

	@EventHandler
	public void onLvlUP(PlayerLevelChangeEvent e){
		Player p = e.getPlayer();
		int lvlv = e.getOldLevel();
		int lvln = e.getNewLevel();

		if (lvln <= 50) {
			if (lvln < lvlv) {
				p.setMaxHealth(p.getMaxHealth() - 1);
			}
			if (lvln > lvlv) {
				p.setMaxHealth(p.getMaxHealth() + 1);
			}
		}
	}
}
