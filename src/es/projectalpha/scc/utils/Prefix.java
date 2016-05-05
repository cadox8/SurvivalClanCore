package es.projectalpha.scc.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import es.projectalpha.scc.SurvivalClanCore;

public class Prefix {

	private SurvivalClanCore pl;

	public Prefix(SurvivalClanCore Main){
		this.pl = Main;
	}

	@SuppressWarnings("deprecation")
	public void refreshPrefix(){
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.hasPermission("tb.admin")) {
				this.pl.admin.addPlayer(p);
			} else {
				if (p.hasPermission("tb.mod")) {
					this.pl.mod.addPlayer(p);
				} else {
					if (p.hasPermission("tb.ayudante")) {
						this.pl.helper.addPlayer(p);
					} else {
						if (p.hasPermission("tb.constructor")) {
							this.pl.builder.addPlayer(p);
						} else {
							if (p.hasPermission("tb.dev")) {
								this.pl.developer.addPlayer(p);
							} else {
								if (p.hasPermission("tb.vip")) {
									this.pl.vip.addPlayer(p);
								} else {
									if (p.hasPermission("tb.origin")) {
										this.pl.origin.addPlayer(p);
									} else {
										if (p.hasPermission("tb.yt")) {
											this.pl.yt.addPlayer(p);
										} else {
											if (p.hasPermission("tb.nomada")) {
												this.pl.nomada.addPlayer(p);
											} else {
												this.pl.def.addPlayer(p);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
			p.setScoreboard(this.pl.sb);
		}
	}
}
