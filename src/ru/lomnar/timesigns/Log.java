package ru.lomnar.timesigns;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Arrow;

public class Log {

	private static String prefix =  "&7[&e" + TimeSigns.plugin.getDescription().getName() + "&7] ";
	
	public static void broadcast(String broadcast) {
		Bukkit.broadcastMessage(getPrefix() + changeText(broadcast));
	}
	
	public static void info(String info) {
		Bukkit.getConsoleSender().sendMessage(getPrefix() + changeText(info));
	}
	
	public static void playerMessage(Player p, String message) {
		p.sendMessage(getPrefix() + changeText(message)); 
	}
	
	public static String changeText(String text) {
		text = text.replace("&", "§");
		text = text.replace("{online}", Bukkit.getOnlinePlayers().toString());
		text = text.replace("{max}", String.valueOf(Bukkit.getMaxPlayers()));
		return text;
	}
	
	public static String getPrefix() {
		return changeText(prefix);
	}
}
