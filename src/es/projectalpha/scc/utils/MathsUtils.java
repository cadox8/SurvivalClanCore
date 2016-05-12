package es.projectalpha.scc.utils;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class MathsUtils {

	public static void randomTeleport(Player p){
		double x = Math.random() * 1000;
		double z = Math.random() * 1000;

		double y = p.getWorld().getHighestBlockYAt((int) x, (int) z);

		final Location l1;

		l1 = new Location(p.getWorld(), x, y, z);

		Chunk c = l1.getChunk();

		if (!l1.getBlock().getRelative(BlockFace.DOWN).isLiquid() && l1.getBlock().getRelative(BlockFace.UP).isEmpty()) {
			l1.getChunk().load();
			l1.getChunk().load(true);

			while (!c.isLoaded()) {
				c.load();
			}

			p.teleport(l1);
			p.sendMessage(Messages.prefix + ChatColor.GREEN + "Has sido teleportado");

		} else {
			randomTeleport(p);
		}
	}
}
