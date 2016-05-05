package es.projectalpha.scc.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {

	public static String prefix = ChatColor.RED + "SCC" + ChatColor.GRAY + " >> ";
	public static String bot = ChatColor.GOLD + ChatColor.BOLD.toString() + "Tom" + ChatColor.GRAY + " >> " + ChatColor.RESET;

	public static String cSmall = ChatColor.AQUA + "AirDrop Pequeño";
	public static String cMedium = ChatColor.AQUA + "AirDrop Mediano";
	public static String cBig = ChatColor.AQUA + "AirDrop Grande";

	public static void TEAM_HELP(Player p){
		p.sendMessage(ChatColor.GRAY + "---- " + ChatColor.DARK_AQUA + "Todos" + ChatColor.GRAY + " ----");
		p.sendMessage(ChatColor.DARK_AQUA + "/raza info <team>" + ChatColor.GRAY + " - Información de otras razas");
		p.sendMessage(ChatColor.DARK_AQUA + "/raza jugador <jugador>" + ChatColor.GRAY + " - Información de un jugador");
		p.sendMessage(ChatColor.DARK_AQUA + "/raza base" + ChatColor.GRAY + " - Te teleporta a la base de tu raza");
		if (p.hasPermission("razas.admin")) {
			p.sendMessage(ChatColor.GRAY + "---- " + ChatColor.DARK_AQUA + "Admins" + ChatColor.GRAY + " ----");
			p.sendMessage(ChatColor.DARK_AQUA + "/raza crear <nombre>" + ChatColor.GRAY + " - Crea una nueva raza");
			p.sendMessage(ChatColor.DARK_AQUA + "/raza poner <raza> <jugador>" + ChatColor.GRAY + "Mete a un jugador a una raza");
			p.sendMessage(ChatColor.DARK_AQUA + "/raza echar <jugador>" + ChatColor.GRAY + " - Echa a un jugador a una la raza 'Nómada'");
			p.sendMessage(ChatColor.DARK_AQUA + "/raza ponbase <raza>" + ChatColor.GRAY + " - Pones la base de la raza");
			p.sendMessage(ChatColor.DARK_AQUA + "/raza borrar <jugador>" + ChatColor.GRAY + " - Elimina a un jugador de las razas");
		}
	}

	public static void TEAM_CREATE_ARGS_ERROR(Player p){
		p.sendMessage(prefix + ChatColor.RED + "Usa: " + ChatColor.DARK_AQUA + "/razas crear <raza>");
	}

	public static void TEAM_CREATED(Player p){
		p.sendMessage(prefix + ChatColor.YELLOW + TeamsYML.getTeams().getString(new StringBuilder("players.").append(p.getName()).append(".team").toString()) + ChatColor.GREEN + " fue creado satisfactoriamente");
	}

	public static void NOT_IN_A_TEAM(Player p){
		p.sendMessage(prefix + ChatColor.RED + "No estas en ninguna raza");
	}

	public static void TEAM_DOES_NOT_EXIST(Player p){
		p.sendMessage(prefix + ChatColor.RED + "La raza no existe");
	}

	public static void TEAM_CREATED_ALREADY(Player p){
		p.sendMessage(prefix + ChatColor.RED + "Esta raza ya ha sido creada");
	}

	public static void JOINED_TEAM_SUCCESSFULLY(Player p){
		p.sendMessage(prefix + ChatColor.GRAY + "Entraste en la raza " + ChatColor.YELLOW + TeamsYML.getTeams().getString(new StringBuilder("players.").append(p.getName()).append(".team").toString()));
	}

	public static void SAME_TEAM(Player p){
		p.sendMessage(prefix + ChatColor.RED + "No puedes pegar a nadie de tu misma raza");
	}

	public static void TEAM_HQ_SET(Player p){
		p.sendMessage(prefix + ChatColor.GREEN + "Has puesto la base de la raza");
	}

	public static void HQ_NOT_SET(Player p){
		p.sendMessage(prefix + ChatColor.RED + "No ha sido puesta la base de la raza");
	}

	public static void TELEPORTED_TO_HQ(Player p){
		p.sendMessage(prefix + ChatColor.GREEN + "Teletrasportado a la base");
	}

	public static void GOING_TO_HQ(Player p){
		p.sendMessage(prefix + ChatColor.GREEN + "Espera 5 segundos para ir a la base sin moverte");
	}

	public static void TELEPORT_HAS_STOPPED(Player p){
		p.sendMessage(prefix + ChatColor.RED + "Has parado tu teletransporte");
	}

	public static void NO_PERMS(Player p){
		p.sendMessage(prefix + ChatColor.RED + "No tienes permisos para esto");
	}
}
