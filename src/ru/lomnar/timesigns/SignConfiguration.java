package ru.lomnar.timesigns;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.block.Sign;

public class SignConfiguration {
	
	private static TimeSigns plugin = TimeSigns.plugin;
	private static List<Integer> schedules = new ArrayList<Integer>();
	
	
	public static void start(String signName, Sign sign) {
		int s =  Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new RunSigns(signName, sign), 0, Signs.getSigns().getInt("Signs." + signName + ".timer"));
		schedules.add(s);
	}
	
	public static void stop() {
		for (int s : schedules) {
			Bukkit.getScheduler().cancelTask(s);
		}
	}

}
