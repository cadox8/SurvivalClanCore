package es.projectalpha.scc.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.projectalpha.scc.utils.Messages;

public class Rol implements CommandExecutor {

	private String f = ChatColor.GRAY + " => ";

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("emociones") && ((sender instanceof Player))) {
			if (args.length == 0) {
				p.sendMessage(Messages.prefix + ChatColor.AQUA + "Comandos de emociones");
				p.sendMessage(Messages.prefix + ChatColor.DARK_GRAY + "Nota: lo que esta entre <> es opcional");
				p.sendMessage(ChatColor.DARK_GREEN + "/besar <jugador>" + f + ChatColor.YELLOW + "Besas al servidor o al jugador");
				p.sendMessage(ChatColor.DARK_GREEN + "/abrazar <jugador>" + f + ChatColor.YELLOW + "Abraza al servidor o al jugador");
				p.sendMessage(ChatColor.DARK_GREEN + "/golpear <jugador>" + f + ChatColor.YELLOW + "Golpeas al servidor o al jugador");
				p.sendMessage(ChatColor.DARK_GREEN + "/mirar <jugador>" + f + ChatColor.YELLOW + "Miras Disimuladamente al servidor o al jugador");
				p.sendMessage(ChatColor.DARK_GREEN + "/mirars <jugador>" + f + ChatColor.YELLOW + "Miras Sensualmente al servidor o al jugador");
				p.sendMessage(ChatColor.DARK_GREEN + "/mirarm <jugador>" + f + ChatColor.YELLOW + "Miras Malamente al servidor o al jugador");
				p.sendMessage(ChatColor.DARK_GREEN + "/poke <jugador>" + f + ChatColor.YELLOW + "Pokea al servidor o al jugador");
				p.sendMessage(ChatColor.DARK_GREEN + "/vomitar <jugador>" + f + ChatColor.YELLOW + "Vomitar al servidor o al jugador");
				//p.sendMessage(ChatColor.DARK_GREEN + "/lanzar <objeto> <jugador>" + f + ChatColor.YELLOW + "Lanza un objeto al servidor o al jugador");
			}
		}
		if (cmd.getName().equalsIgnoreCase("besar") && ((sender instanceof Player))) {
			if (args.length == 0) {
				Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " ha besado a todo el servidor");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.HEART, 40);
				}
			}
			if (args.length == 1) {
				Player pl = Bukkit.getPlayerExact(args[0]);

				if (pl == null) {
					p.sendMessage(Messages.prefix + ChatColor.RED + "Este jugador no existe");
					return true;
				}

				if (pl == p) {
					pl.sendMessage(ChatColor.GREEN + "Sabemos que estas necesitado de esto, pero no te lo puedes dar a ti mismo");
					return true;
				}

				pl.sendMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " te ha besado :D");
				p.sendMessage(ChatColor.YELLOW + "Has besado a " + ChatColor.RED + pl.getName());
				pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.HEART, 40);
				p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ()), Effect.HEART, 40);
			}
		}
		if (cmd.getName().equalsIgnoreCase("abrazar") && ((sender instanceof Player))) {
			if (args.length == 0) {
				Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " ha abrazado a todo el servidor");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.HAPPY_VILLAGER, 40);
				}
			}
			if (args.length == 1) {
				Player pl = Bukkit.getPlayerExact(args[0]);

				if (pl == null) {
					p.sendMessage(Messages.prefix + ChatColor.RED + "Este jugador no existe");
					return true;
				}

				if (pl == p) {
					pl.sendMessage(ChatColor.GREEN + "Sabemos que estas necesitado de esto, pero no te lo puedes dar a ti mismo");
					return true;
				}

				pl.sendMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " te ha abrazado :D");
				p.sendMessage(ChatColor.YELLOW + "Has abrazado a " + ChatColor.RED + pl.getName());
				pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.HAPPY_VILLAGER, 40);
				p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ()), Effect.HAPPY_VILLAGER, 40);
			}
		}
		if (cmd.getName().equalsIgnoreCase("golpear") && ((sender instanceof Player))) {
			if (args.length == 0) {
				Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " ha golpeado a todo el servidor");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.CRIT, 40);
				}
			}
			if (args.length == 1) {
				Player pl = Bukkit.getPlayerExact(args[0]);

				if (pl == null) {
					p.sendMessage(Messages.prefix + ChatColor.RED + "Este jugador no existe");
					return true;
				}

				if (pl == p) {
					pl.sendMessage(ChatColor.GREEN + "¡Que no te pegues!");
					return true;
				}

				pl.sendMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " te ha golpeado :D");
				p.sendMessage(ChatColor.YELLOW + "Has golpeado a " + ChatColor.RED + pl.getName());
				pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.CRIT, 40);
				p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ()), Effect.CRIT, 40);
			}
		}
		if (cmd.getName().equalsIgnoreCase("mirar") && ((sender instanceof Player))) {
			if (args.length == 0) {
				Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " ha mirado disimuladamente a todo el servidor");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.BLAZE_SHOOT, 40);
				}
			}
			if (args.length == 1) {
				Player pl = Bukkit.getPlayerExact(args[0]);

				if (pl == null) {
					p.sendMessage(Messages.prefix + ChatColor.RED + "Este jugador no existe");
					return true;
				}

				if (pl == p) {
					pl.sendMessage(ChatColor.GREEN + "¿Como te miras disimuladamente a ti mismo?");
					return true;
				}

				pl.sendMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " te ha mirado disimuladamente :D");
				p.sendMessage(ChatColor.YELLOW + "Has mirado disimuladamente a " + ChatColor.RED + pl.getName());
				pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.BLAZE_SHOOT, 40);
				p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ()), Effect.BLAZE_SHOOT, 40);
			}
		}
		if (cmd.getName().equalsIgnoreCase("mirarm") && ((sender instanceof Player))) {
			if (args.length == 0) {
				Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " ha mirado mal a todo el servidor");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.EXPLOSION_HUGE, 40);
				}
			}
			if (args.length == 1) {
				Player pl = Bukkit.getPlayerExact(args[0]);

				if (pl == null) {
					p.sendMessage(Messages.prefix + ChatColor.RED + "Este jugador no existe");
					return true;
				}

				if (pl == p) {
					pl.sendMessage(ChatColor.GREEN + "¿Como te miras mal a ti mismo?");
					return true;
				}

				pl.sendMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " te ha mirado mal :D");
				p.sendMessage(ChatColor.YELLOW + "Has mirado mal a " + ChatColor.RED + pl.getName());
				pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.EXPLOSION_HUGE, 40);
				p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ()), Effect.EXPLOSION_HUGE, 40);
			}
		}
		if (cmd.getName().equalsIgnoreCase("mirars") && ((sender instanceof Player))) {
			if (args.length == 0) {
				Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " ha mirado sensualmente a todo el servidor");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.NOTE, 40);
				}
			}
			if (args.length == 1) {
				Player pl = Bukkit.getPlayerExact(args[0]);

				if (pl == null) {
					p.sendMessage(Messages.prefix + ChatColor.RED + "Este jugador no existe");
					return true;
				}

				if (pl == p) {
					pl.sendMessage(ChatColor.GREEN + "¿Como te miras sensualmente a ti mismo?");
					return true;
				}

				pl.sendMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " te ha mirado sensualmente :D");
				p.sendMessage(ChatColor.YELLOW + "Has mirado sensualmente a " + ChatColor.RED + pl.getName());
				pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.NOTE, 40);
				p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ()), Effect.NOTE, 40);
			}
		}
		if (cmd.getName().equalsIgnoreCase("poke") && ((sender instanceof Player))) {
			if (args.length == 0) {

				Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " ha pokeado a todo el servidor");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.VILLAGER_THUNDERCLOUD, 40);
					pl.playSound(pl.getLocation(), Sound.BLOCK_ANVIL_USE, 4.0F, 4.0F);
				}
			}
			if (args.length == 1) {
				Player pl = Bukkit.getPlayerExact(args[0]);

				if (pl == null) {
					p.sendMessage(Messages.prefix + ChatColor.RED + "Este jugador no existe");
					return true;
				}

				if (pl == p) {
					pl.sendMessage(ChatColor.GREEN + "Mmmmm es molesto el poke...");
					return true;
				}

				pl.sendMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " te ha pokeado :D");
				p.sendMessage(ChatColor.YELLOW + "Has pokeado a " + ChatColor.RED + pl.getName());
				pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.VILLAGER_THUNDERCLOUD, 40);
				pl.playSound(pl.getLocation(), Sound.BLOCK_ANVIL_USE, 4.0F, 4.0F);
				p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ()), Effect.VILLAGER_THUNDERCLOUD, 40);
			}
		}
		if (cmd.getName().equalsIgnoreCase("vomitar") && ((sender instanceof Player))) {
			if (args.length == 0) {

				Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " ha vomitado encima de todo el servidor");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.GHAST_SHOOT, 40);
					pl.playSound(pl.getLocation(), Sound.ENTITY_SLIME_SQUISH, 4.0F, 4.0F);
				}
			}
			if (args.length == 1) {
				Player pl = Bukkit.getPlayerExact(args[0]);

				if (pl == null) {
					p.sendMessage(Messages.prefix + ChatColor.RED + "Este jugador no existe");
					return true;
				}

				if (pl == p) {
					pl.sendMessage(ChatColor.GREEN + "El buen fetiche, ¿no?");
					return true;
				}

				pl.sendMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " te ha vomitado :D");
				p.sendMessage(ChatColor.YELLOW + "Has vomitado a " + ChatColor.RED + pl.getName());
				pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.GHAST_SHOOT, 40);
				pl.playSound(pl.getLocation(), Sound.ENTITY_SLIME_SQUISH, 4.0F, 4.0F);
				p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ()), Effect.GHAST_SHOOT, 40);
			}
		}

		if (cmd.getName().equalsIgnoreCase("reir") && ((sender instanceof Player))) {
			if (args.length == 0) {

				Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " se ha reido de todo el servidor");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.GHAST_SHOOT, 40);
					pl.playSound(pl.getLocation(), Sound.ENTITY_SLIME_ATTACK, 4.0F, 4.0F);
				}
			}
			if (args.length == 1) {
				String pl = args[0];

				Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " se ha reido de " + ChatColor.RED + pl);

				p.playSound(p.getLocation(), Sound.ENTITY_SLIME_ATTACK, 4.0F, 4.0F);
				p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ()), Effect.BOW_FIRE, 40);
			}
		}
		if (cmd.getName().equalsIgnoreCase("lanzar") && ((sender instanceof Player))) {
			if (args.length == 1) {

				Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " ha lanzado " + ChatColor.RED + args[0] + ChatColor.YELLOW + " encima de todo el servidor");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.SPELL, 40);
					pl.playSound(pl.getLocation(), Sound.ENTITY_ARROW_HIT, 4.0F, 4.0F);
				}
			}
			if (args.length == 2) {
				Player pl = Bukkit.getPlayerExact(args[0]);

				if (pl == null) {
					p.sendMessage(Messages.prefix + ChatColor.RED + "Este jugador no existe");
					return true;
				}

				if (pl == p) {
					pl.sendMessage(ChatColor.GREEN + "Que te habras tirado...");
					return true;
				}

				pl.sendMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " te ha lanzado " + ChatColor.RED + args[1] + ChatColor.YELLOW + " :D");
				p.sendMessage(ChatColor.YELLOW + "Has lanzado " + ChatColor.RED + args[1] + ChatColor.YELLOW + " a " + ChatColor.RED + pl.getName());
				pl.getWorld().playEffect(new Location(pl.getWorld(), pl.getLocation().getX(), pl.getLocation().getY() + 2, pl.getLocation().getZ()), Effect.SPELL, 40);
				pl.playSound(pl.getLocation(), Sound.ENTITY_ARROW_HIT, 4.0F, 4.0F);
				p.getWorld().playEffect(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ()), Effect.SPELL, 40);
			}
		}
		return false;
	}
}
