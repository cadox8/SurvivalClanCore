package es.projectalpha.scc.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.projectalpha.scc.SurvivalClanCore;
import es.projectalpha.scc.api.PingAPI;
import es.projectalpha.scc.utils.Messages;

public class Ayuda implements CommandExecutor {

	private SurvivalClanCore plugin;

	public Ayuda(SurvivalClanCore Main){
		this.plugin = Main;
	}

	private String f = ChatColor.GRAY + " => ";

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("ayuda") && ((sender instanceof Player))) {
			if (args.length == 0) {
				p.sendMessage(Messages.prefix + ChatColor.AQUA + "La ayuda del servidor");
				p.sendMessage(ChatColor.DARK_GREEN + "/ayuda info" + f + ChatColor.YELLOW + "Muestra información del plugin");
				p.sendMessage(ChatColor.DARK_GREEN + "/emociones" + f + ChatColor.YELLOW + "Te dice que comandos para mostrar tus emociones puedes usar");
				p.sendMessage(ChatColor.DARK_GREEN + "/ayuda social" + f + ChatColor.YELLOW + "Muestra comandos de nuestra network");
				p.sendMessage(ChatColor.DARK_GREEN + "/ayuda ping" + f + ChatColor.YELLOW + "Muestra tu ping");
				p.sendMessage(ChatColor.DARK_GREEN + "/ayuda ayuda" + f + ChatColor.YELLOW + "Pide ayuda al staff inmediatamente (si hay conectados)");
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("info")) {
					p.sendMessage(Messages.prefix + ChatColor.AQUA + "Información del plugin");
					p.sendMessage(ChatColor.DARK_GREEN + "Versión: " + ChatColor.GOLD + this.plugin.getDescription().getVersion());
					p.sendMessage(ChatColor.DARK_GREEN + "Autores: " + ChatColor.GOLD + this.plugin.getDescription().getAuthors());
					p.sendMessage(ChatColor.DARK_GREEN + "Posibles Bugs: " + ChatColor.GOLD + "Trabajando en una forma fácil de poner bugs...");
				}
				if (args[0].equalsIgnoreCase("social")) {
					p.sendMessage(Messages.prefix + ChatColor.AQUA + "Comandos de la Network");
					p.sendMessage(ChatColor.DARK_GREEN + "/twitter: " + ChatColor.GOLD + "Accede a nuestro twitter");
					p.sendMessage(ChatColor.DARK_GREEN + "/youtube: " + ChatColor.GOLD + "Accede a nuestro canal de youtube");
					p.sendMessage(ChatColor.DARK_GREEN + "/web: " + ChatColor.GOLD + "Accede a nuestra web");
					p.sendMessage(ChatColor.DARK_GREEN + "/foro: " + ChatColor.GOLD + "Accede a nuestro foro");
					p.sendMessage(ChatColor.DARK_GREEN + "/ts: " + ChatColor.GOLD + "Muestra nuestro ts");
				}
				if (args[0].equalsIgnoreCase("ping")) {
					p.sendMessage(Messages.prefix + ChatColor.GREEN + "Tu ping es " + ChatColor.YELLOW + PingAPI.getPing(p));
				}
				if (args[0].equalsIgnoreCase("ayuda")) {
					p.sendMessage(Messages.prefix + ChatColor.RED + "Los staffs han sido avisados. Se pondrán en contacto contigo en pocos minutos (si hay alguno conectado)");

					for (Player pl : Bukkit.getOnlinePlayers()) {
						if (pl.hasPermission("rolcore.staff")) {
							pl.sendMessage(Messages.prefix + ChatColor.GREEN + "El jugador " + ChatColor.YELLOW + p.getName() + ChatColor.GREEN + " esta pidiendo ayuda. Ve a ayudarle!");
						}
					}
				}
			}
			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("ping")) {
					Player pl = Bukkit.getPlayerExact(args[1]);
					p.sendMessage(Messages.prefix + ChatColor.GREEN + "El ping de " + ChatColor.RED + pl.getName() + ChatColor.GREEN + " es " + ChatColor.YELLOW + PingAPI.getPing(pl));
				}
			}
			if (args.length > 3) {
				p.sendMessage(Messages.prefix + ChatColor.RED + "Creo que te has pasado un poco con los argumentos...");
			}
		}
		return false;
	}
}
