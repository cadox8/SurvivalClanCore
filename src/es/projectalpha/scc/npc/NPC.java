package es.projectalpha.scc.npc;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R2.CraftServer;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;

import net.minecraft.server.v1_9_R2.Block;
import net.minecraft.server.v1_9_R2.EntityPlayer;
import net.minecraft.server.v1_9_R2.EnumItemSlot;
import net.minecraft.server.v1_9_R2.ItemStack;
import net.minecraft.server.v1_9_R2.MinecraftServer;
import net.minecraft.server.v1_9_R2.PlayerInteractManager;
import net.minecraft.server.v1_9_R2.WorldServer;

import com.mojang.authlib.GameProfile;

public class NPC extends EntityPlayer {

	public static HashMap<Integer, NPC> npcList = new HashMap<Integer, NPC>();

	private static MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
	private static WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();

	public NPC(GameProfile gameprofile){
		super(nmsServer, nmsWorld, gameprofile, new PlayerInteractManager(nmsWorld));
	}

	public static void spawn(NPC npc, Location l){
		if (npcList.size() == 0) {
			npcList.put(0, npc);
		} else {
			npcList.put(npcList.size() + 1, npc);
		}

		npc.setLocation(l.getX(), l.getY(), l.getZ(), 0, 0);

		npc.setInvulnerable(true);
		npc.setEquipment(EnumItemSlot.MAINHAND, new ItemStack(Block.getByName(Material.COMMAND.toString())));
		npc.setEquipment(EnumItemSlot.OFFHAND, new ItemStack(Block.getById(64)));
	}

	public static void removeNPC(int id){
		npcList.remove(id);
	}
}
