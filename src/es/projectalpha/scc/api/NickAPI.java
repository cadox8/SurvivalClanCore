package es.projectalpha.scc.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.CraftServer;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_9_R2.EntityPlayer;
import net.minecraft.server.v1_9_R2.MinecraftServer;
import net.minecraft.server.v1_9_R2.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_9_R2.PlayerConnection;
import net.minecraft.server.v1_9_R2.PlayerInteractManager;
import net.minecraft.server.v1_9_R2.WorldServer;

import com.mojang.authlib.GameProfile;

public class NickAPI {

	public static HashMap<UUID, String> PlayerName = new HashMap<UUID, String>();
	public static HashMap<String, Player> NamePlayer = new HashMap<String, Player>();
	public static ArrayList<String> Names = new ArrayList<String>();

	public static void setName(Player player, String name){
		PlayerName.put(player.getUniqueId(), player.getName());
		NamePlayer.put(name, player);

		MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
		EntityPlayer pNeu = new EntityPlayer(nmsServer, nmsWorld, new GameProfile(player.getUniqueId(), name), new PlayerInteractManager(nmsWorld));
		EntityPlayer p = ((CraftPlayer) player).getHandle();
		for (Player players : Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer) players).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[] { p }));
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { pNeu }));
		}
		player.setDisplayName(name);
		player.setCustomName(name);
		player.teleport(player.getLocation());
	}

	public static void setDefaultName(Player player){
		String Name = getRealName(player.getCustomName());
		MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
		EntityPlayer pNeu = new EntityPlayer(nmsServer, nmsWorld, new GameProfile(player.getUniqueId(), Name), new PlayerInteractManager(nmsWorld));
		EntityPlayer p = new EntityPlayer(nmsServer, nmsWorld, new GameProfile(player.getUniqueId(), player.getCustomName()), new PlayerInteractManager(nmsWorld));
		for (Player players : Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer) players).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[] { p }));
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { pNeu }));
		}
		player.setDisplayName(Name);
		player.setCustomName(Name);

		Location loc = new Location(player.getWorld(), player.getLocation().getX() + 500.0D, player.getLocation().getY() + 2000.0D, player.getLocation().getZ() + 500.0D);
		player.teleport(loc);
	}

	public static String getRealName(String Name){
		if (NamePlayer.containsKey(Name)) {
			Player player = (Player) NamePlayer.get(Name);
			if (PlayerName.containsKey(player.getUniqueId())) {
				return (String) PlayerName.get(player.getUniqueId());
			}
		}
		return "";
	}
}
