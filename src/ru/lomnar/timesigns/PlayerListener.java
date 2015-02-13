package ru.lomnar.timesigns;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {
	
	private TimeSigns plugin = TimeSigns.plugin;
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		
		if (!(e.getClickedBlock().getState() instanceof Sign)) {
			return;
		}
		
		Sign sign = (Sign) e.getClickedBlock().getState();
		
		if (plugin.isBuffer()) {
			plugin.getSigns().add(plugin.getSign() + "|" + sign.getX() + "|" + sign.getY() + "|" + sign.getZ() + "|" + sign.getWorld().getName());
			Signs.getSigns().set("placed-signs", plugin.getSigns());
			Signs.saveSigns();
			plugin.updateSigns();
			plugin.setBuffer(false);
			Log.playerMessage(e.getPlayer(), "§2Вы успешно установили текст на табличку!");
		}
	}

}
