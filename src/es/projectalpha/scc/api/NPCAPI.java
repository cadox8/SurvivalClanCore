package es.projectalpha.scc.api;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R1.CraftServer;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_9_R1.EntityPlayer;
import net.minecraft.server.v1_9_R1.MinecraftServer;
import net.minecraft.server.v1_9_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_9_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_9_R1.PlayerConnection;
import net.minecraft.server.v1_9_R1.PlayerInteractManager;
import net.minecraft.server.v1_9_R1.WorldServer;

import com.mojang.authlib.GameProfile;

public class NPCAPI {

	public static void summonCustomPlayer(EntityPlayer ePlayer, UUID uuid, String name, Player showNPCTo){
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld) Bukkit.getServer().getWorlds().get(0)).getHandle();

		ePlayer = new EntityPlayer(server, world, new GameProfile(uuid, name), new PlayerInteractManager(world));
		ePlayer.setLocation(showNPCTo.getLocation().getX(), showNPCTo.getLocation().getY(), showNPCTo.getLocation().getZ(), 0.0F, 0.0F);
		PlayerConnection connection = ((CraftPlayer) showNPCTo).getHandle().playerConnection;
		connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { ePlayer }));
		connection.sendPacket(new PacketPlayOutNamedEntitySpawn(ePlayer));
	}
}
