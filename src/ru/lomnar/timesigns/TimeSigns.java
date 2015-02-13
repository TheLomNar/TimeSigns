package ru.lomnar.timesigns;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.plugin.java.JavaPlugin;

public class TimeSigns extends JavaPlugin {

	public static TimeSigns plugin;
	private boolean buffer = false;
	private String signName = null;
	private List<String> signs = new ArrayList<String>();

	public void onEnable() {
		plugin = this;
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getCommand("timesigns").setExecutor(new Commands());
		initConfig();
		updateSigns();
		Log.info("&ePlguin was enabled.");
	}

	public void onDisable() {
		saveConfig();
		Signs.saveSigns();
		Log.info("&ePlguin was disabled.");
	}
	
	public void updateSigns() {
		
			signs = Signs.getSigns().getStringList("placed-signs");
			
			for (int i = 0; i < signs.size(); ++i) {
				String signName = ((String)TimeSigns.this.signs.get(i)).split(Pattern.quote("|"))[0];
				int x = Integer.parseInt(((String)TimeSigns.this.signs.get(i)).split(Pattern.quote("|"))[1]);
				int y = Integer.parseInt(((String)TimeSigns.this.signs.get(i)).split(Pattern.quote("|"))[2]);
				int z = Integer.parseInt(((String)TimeSigns.this.signs.get(i)).split(Pattern.quote("|"))[3]);
				String world = ((String)TimeSigns.this.signs.get(i)).split(Pattern.quote("|"))[4];
				Block b = Bukkit.getWorld(world).getBlockAt(x, y, z);
				if (b.getState() instanceof Sign) {
					Sign sign = (Sign)b.getState();
					SignConfiguration.start(signName, sign);
				}
			}	
		
	}
	
	public void setLine(String signName, int line, String text) {
		List<String> lines = new ArrayList<String>();
		lines = Signs.getSigns().getStringList("Signs." + signName + ".line" + line);
		lines.add(text.replace("_", " "));
		Signs.getSigns().set("Signs." + signName + ".line" + line, lines);
		Signs.saveSigns();
	}
	
	public void createSign(String signName) {
		Signs.getSigns().set("Signs." + signName + ".timer", 1);
		Signs.getSigns().set("Signs." + signName + ".line1", new ArrayList<>());
		Signs.getSigns().set("Signs." + signName + ".line2", new ArrayList<>());
		Signs.getSigns().set("Signs." + signName + ".line3", new ArrayList<>());
		Signs.getSigns().set("Signs." + signName + ".line4", new ArrayList<>());
		Signs.saveSigns();
		
	}
	
	private void initConfig() {
		getConfig().options().copyDefaults(true);
		getConfig().set("version", Double.parseDouble(getDescription().getVersion()));
		saveConfig();
		Signs.getSigns().options().copyDefaults(true);
		Signs.getSigns().addDefault("Signs.sign.timer", 1);
		Signs.getSigns().addDefault("Signs.sign.line1", new ArrayList<>());
		Signs.getSigns().addDefault("Signs.sign.line2", new ArrayList<>());
		Signs.getSigns().addDefault("Signs.sign.line3", new ArrayList<>());
		Signs.getSigns().addDefault("Signs.sign.line4", new ArrayList<>());
		Signs.getSigns().addDefault("placed-signs", new ArrayList<>());
		Signs.saveSigns();
	}
	
	public void setBuffer(boolean value) {
		buffer = value;
	}
	
	public boolean isBuffer() {
		return buffer;
	}

	public void setSign(String signName) {
		this.signName = signName;
	}
	
	public String getSign() {
		return signName;
	}
	
	public List<String> getSigns() {
		return signs;
	}
	
}
