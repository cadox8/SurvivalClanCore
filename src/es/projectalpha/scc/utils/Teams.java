package es.projectalpha.scc.utils;

import org.bukkit.ChatColor;

import es.projectalpha.scc.SurvivalClanCore;

public class Teams {

	private SurvivalClanCore pl;

	private String p1 = ChatColor.GRAY + "[";
	private String p2 = ChatColor.GRAY + "]";
	private String a = ChatColor.RED + "A";
	private String m = ChatColor.AQUA + "M";
	private String ay = ChatColor.DARK_PURPLE + "AY";
	private String c = ChatColor.DARK_BLUE + "C";
	private String d = ChatColor.DARK_GREEN + "D";
	private String v = ChatColor.GREEN + "V";
	private String o = ChatColor.GOLD + "O";
	private String y = ChatColor.DARK_RED + "YT";
	private String n = ChatColor.GRAY + "";

	public Teams(SurvivalClanCore pl){
		this.pl = pl;
	}

	public void registerTeams(){
		this.pl.admin = (this.pl.sb.getTeam("ADMIN") == null ? this.pl.sb.registerNewTeam("ADMIN") : this.pl.sb.getTeam("ADMIN"));
		this.pl.mod = (this.pl.sb.getTeam("MOD") == null ? this.pl.sb.registerNewTeam("MOD") : this.pl.sb.getTeam("MOD"));
		this.pl.helper = (this.pl.sb.getTeam("AYUDANTE") == null ? this.pl.sb.registerNewTeam("AYUDANTE") : this.pl.sb.getTeam("AYUDANTE"));
		this.pl.builder = (this.pl.sb.getTeam("CONSTRUCTOR") == null ? this.pl.sb.registerNewTeam("CONSTRUCTOR") : this.pl.sb.getTeam("CONSTRUCTOR"));
		this.pl.developer = (this.pl.sb.getTeam("DEV") == null ? this.pl.sb.registerNewTeam("DEV") : this.pl.sb.getTeam("DEV"));
		this.pl.def = (this.pl.sb.getTeam("MIEMBRO") == null ? this.pl.sb.registerNewTeam("MIEMBRO") : this.pl.sb.getTeam("MIEMBRO"));
		this.pl.vip = (this.pl.sb.getTeam("VIP") == null ? this.pl.sb.registerNewTeam("VIP") : this.pl.sb.getTeam("VIP"));
		this.pl.origin = (this.pl.sb.getTeam("ORIGIN") == null ? this.pl.sb.registerNewTeam("ORIGIN") : this.pl.sb.getTeam("ORIGIN"));
		this.pl.yt = (this.pl.sb.getTeam("YT") == null ? this.pl.sb.registerNewTeam("YT") : this.pl.sb.getTeam("YT"));
		this.pl.nomada = (this.pl.sb.getTeam("Nomanda") == null ? this.pl.sb.registerNewTeam("Nomada") : this.pl.sb.getTeam("Nomada"));
	}

	public void setPrefixesAndSuffixes(){
		this.pl.admin.setPrefix(p1 + a + p2 + ChatColor.RESET + " ");
		this.pl.mod.setPrefix(p1 + m + p2 + ChatColor.RESET + " ");
		this.pl.helper.setPrefix(p1 + ay + p2 + ChatColor.RESET + " ");
		this.pl.builder.setPrefix(p1 + c + p2 + ChatColor.RESET + " ");
		this.pl.developer.setPrefix(p1 + d + p2 + ChatColor.RESET + " ");
		this.pl.def.setPrefix("");
		this.pl.vip.setPrefix(p1 + v + p2 + ChatColor.RESET + " ");
		this.pl.origin.setPrefix(p1 + o + p2 + ChatColor.RESET + " ");
		this.pl.yt.setPrefix(p1 + y + p2 + ChatColor.RESET + " ");
		this.pl.nomada.setPrefix(n);
	}
}
