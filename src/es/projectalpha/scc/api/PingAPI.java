package es.projectalpha.scc.api;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

public class PingAPI {

	public static int getPing(Player p){
		try {
			Object entityPlayer = p.getClass().getMethod("getHandle", new Class[0]).invoke(p, new Object[0]);
			return ((Integer) entityPlayer.getClass().getField("ping").get(entityPlayer)).intValue();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException localIllegalAccessException) {}
		return -1;
	}
}
