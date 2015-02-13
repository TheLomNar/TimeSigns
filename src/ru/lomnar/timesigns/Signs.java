package ru.lomnar.timesigns;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Signs {
	private static FileConfiguration config = null;
	private static File configFile = null;
	private static TimeSigns plugin = TimeSigns.plugin;


	public static FileConfiguration getSigns() {
		if (configFile == null) {
			reloadSigns();
		}
		return config;
	}

	@SuppressWarnings("deprecation")
	public static void reloadSigns() {
		if (configFile == null) {
			configFile = new File(plugin.getDataFolder(), "signs.yml");
		}

		config = YamlConfiguration.loadConfiguration(configFile);

		InputStream configStream = plugin.getResource("signs.yml");
		if (configStream != null) {
			YamlConfiguration defaultConf = YamlConfiguration.loadConfiguration(configStream);
			config.setDefaults(defaultConf);
		}
	}
	
	public static void saveSigns() {
		if (config == null || configFile == null) {
			return;
		}
		try {
			getSigns().save(configFile);
		} catch (IOException e) {
			System.out.println(configFile + "[" + e + "]");
		}
	}
	public static void saveDef() {
		if (configFile == null) {
			configFile = new File(plugin.getDataFolder(), "signs.yml");
		}
		
		if(!configFile.exists()) {
			plugin.saveResource("signs.yml", false);
		}
	}
}
