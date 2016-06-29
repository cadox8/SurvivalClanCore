package es.projectalpha.scc.api;

import org.bukkit.Location;

import es.projectalpha.scc.utils.ParticleEffect;

public class ParticleAPI {

	public static void sendParticles(ParticleEffect.OrdinaryColor color, ParticleEffect pe, Location start, Location end, int repeatCount){
		double dist = Math.abs(end.distance(start));

		for (int i = -1; i < repeatCount; i++) {
			double delta = i / 10.0D / dist;
			double x = (1.0D - delta) * start.getX() + delta * (end.getX() + 0.5D);
			double y = (1.0D - delta) * start.getY() + delta * (end.getY() + 0.5D);
			double z = (1.0D - delta) * start.getZ() + delta * (end.getZ() + 0.5D);

			Location l = new Location(start.getWorld(), x, y, z);

			try {
				pe.display(color, l, 257.0D);
			} catch (Exception ex) {
				pe.display(0.0F, 0.0F, 0.0F, 0.0F, 1, l, 16.0D);
			}
		}
	}

	public static void sendParticles(ParticleEffect pe, Location start, Location end, int repeatCount){
		double dist = Math.abs(end.distance(start));

		for (int i = -1; i < repeatCount; i++) {
			double delta = i / 10.0D / dist;
			double x = (1.0D - delta) * start.getX() + delta * (end.getX() + 0.5D);
			double y = (1.0D - delta) * start.getY() + delta * (end.getY() + 0.5D);
			double z = (1.0D - delta) * start.getZ() + delta * (end.getZ() + 0.5D);

			Location l = new Location(start.getWorld(), x, y, z);

			pe.display(0.0F, 0.0F, 0.0F, 0.0F, 1, l, 16.0D);
		}
	}

	public static void sendParticles(ParticleEffect pe, float offsetX, float offsetY, float offsetZ, Location center, double range){
		pe.display(offsetX, offsetY, offsetZ, 0.0F, 1, center, range);
	}

	public static void setButterfly(Location loc, Location loc2, ParticleEffect.OrdinaryColor c1, ParticleEffect.OrdinaryColor c2, ParticleEffect.OrdinaryColor c3){
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-1.2000000000000002D)), 30.0D);
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-1.4000000000000001D)), 30.0D);
		loc.add(0.0D, 0.2D, 0.0D);
		loc2 = loc.clone();
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-1.0D)), 30.0D);
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-1.2000000000000002D)), 30.0D);
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-1.4000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-1.6D)), 30.0D);
		loc.add(0.0D, 0.2D, 0.0D);
		loc2 = loc.clone();
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-0.8D)), 30.0D);
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-1.0D)), 30.0D);
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-1.2000000000000002D)), 30.0D);
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-1.4000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-1.6D)), 30.0D);

		loc.add(0.0D, 0.2D, 0.0D);
		loc2 = loc.clone();
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-0.6000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-0.8D)), 30.0D);
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-1.0D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.2000000000000002D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.4000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.6D)), 30.0D);
		loc.add(0.0D, 0.2D, 0.0D);
		loc2 = loc.clone();
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-0.4D)), 30.0D);
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-0.6000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-0.8D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.0D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.2000000000000002D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.4000000000000001D)), 30.0D);
		loc.add(0.0D, 0.2D, 0.0D);
		loc2 = loc.clone();
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-0.4D)), 30.0D);
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-0.6000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-0.8D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.0D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.2000000000000002D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.4000000000000001D)), 30.0D);
		loc.add(0.0D, 0.2D, 0.0D);
		loc2 = loc.clone();
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-0.4D)), 30.0D);
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-0.6000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-0.8D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.0D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.2000000000000002D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.4000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.6D)), 30.0D);
		loc.add(0.0D, 0.2D, 0.0D);
		loc2 = loc.clone();
		ParticleEffect.REDSTONE.display(c1, loc2.clone().add(loc2.getDirection().multiply(-0.4D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-0.6000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-0.8D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.0D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.2000000000000002D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.4000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.6D)), 30.0D);
		loc.add(0.0D, 0.2D, 0.0D);
		loc2 = loc.clone();
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-0.4D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-0.6000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-0.8D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-1.0D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.2000000000000002D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.4000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.6D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.8D)), 30.0D);
		loc.add(0.0D, 0.2D, 0.0D);
		loc2 = loc.clone();
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-2.0D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-0.6000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c2, loc2.clone().add(loc2.getDirection().multiply(-0.8D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.0D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.2000000000000002D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.4000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.6D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.8D)), 30.0D);
		loc.add(0.0D, 0.2D, 0.0D);
		loc2 = loc.clone();
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.0D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.2000000000000002D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.4000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.6D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.8D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-2.0D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-2.2D)), 30.0D);
		loc.add(0.0D, 0.2D, 0.0D);
		loc2 = loc.clone();
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.4000000000000001D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.6D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-1.8D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-2.0D)), 30.0D);
		ParticleEffect.REDSTONE.display(c3, loc2.clone().add(loc2.getDirection().multiply(-2.2D)), 30.0D);
	}
}
