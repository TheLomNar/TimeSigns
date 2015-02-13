package ru.lomnar.timesigns;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Commands implements CommandExecutor {
	
	private TimeSigns plugin = TimeSigns.plugin;
	
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return false;
		}
		Player p = (Player) sender;
		if (args.length == 0 ) {
			if (p.isOp() || p.hasPermission("timesigns.admin")) {
				p.sendMessage("§5--------------- "  + Log.getPrefix() + "§5---------------");
				p.sendMessage(" §2/ts add §4<sign> §e- использовать параметры заданные в конфиге");
				p.sendMessage(" §2/ts create §4<sign> §e- создать новую табличку");
				p.sendMessage(" §2/ts sign §4<signName> §2line1 §3<text> §e- добавить текст на строку 1");
				p.sendMessage(" §2/ts sign §4<signName> §2line2 §3<text> §e- добавить текст на строку 2");
				p.sendMessage(" §2/ts sign §4<signName> §2line3 §3<text> §e- добавить текст на строку 3");
				p.sendMessage(" §2/ts sign §4<signName> §2line4 §3<text> §e- добавить текст на строку 4");
				p.sendMessage(" §2/ts timer §4<signName> §5<timer> §e- установить таймер");
				p.sendMessage(" §2/ts reload §e- перезагрузить плагин.");
				p.sendMessage("§5--------------------------------------");
			} else Log.playerMessage(p, "§4У вас нет прав на использование этой команды.");
		} else if (args[0].equals("add") && (p.isOp() || p.hasPermission("timesigns.admin"))) {
			if (!(args.length < 2)) {
				if (Signs.getSigns().contains("Signs." + args[1])) {
					plugin.setSign(args[1]);
					plugin.setBuffer(true);
					Log.playerMessage(p, "§2Теперь жми на табличку!");
				} else Log.playerMessage(p, "§4Нет такой таблички!");
			} else Log.playerMessage(p, "§2/ts add <sign>");
		}
		else if (args[0].equals("reload") && (p.isOp() || p.hasPermission("timesigns.admin"))) {
			
			Log.info("§3The plugin is reloaded...");
			Log.playerMessage(p, "§3Перезагрузка плагина...");
			Signs.reloadSigns();
			SignConfiguration.stop();
			plugin.updateSigns();
			Log.info("§2This plugin has been restarted!");
			Log.playerMessage(p, "§2Плагин перезагружен.");
			}
		else if (args[0].equals("create") && (p.isOp() || p.hasPermission("timesigns.admin"))) {
			if (!(args.length < 2)) {
				if (!(Signs.getSigns().contains(args[1]))) {
					plugin.createSign(args[1]);
					 Log.playerMessage(p, "§eВы создали табличку с именем: §c" + args[1]);
				} else Log.playerMessage(p, "§4Вы не можете создать табличку с таким именем т.к. она уже существует.");
			} else Log.playerMessage(p, "§2/ts create <sign>");
		}
		else if (args[0].equals("sign") && (p.isOp() || p.hasPermission("timesigns.admin"))) {
			if (!(args.length < 4)) {
				if (args[2].equals("line1")) {
					plugin.setLine(args[1], 1, args[3]);
					 Log.playerMessage(p, "§eВы добавили текст на строку 1: §f" + args[3].replace("&", "§").replace("_", " "));
				}
				else if (args[2].equals("line2")) {
					plugin.setLine(args[1], 2, args[3]);
					 Log.playerMessage(p, "§eВы добавили текст на строку 2: §f" + args[3].replace("&", "§").replace("_", " "));
				}
				else if (args[2].equals("line3")) {
					plugin.setLine(args[1], 3, args[3]);
					 Log.playerMessage(p, "§eВы добавили текст на строку 3: §f" + args[3].replace("&", "§").replace("_", " "));
				}
				else if (args[2].equals("line4")) {
					plugin.setLine(args[1], 4, args[3]);
					 Log.playerMessage(p, "§eВы добавили текст на строку 4: §f" + args[3].replace("&", "§").replace("_", " "));
				} else Log.playerMessage(p, "§2/ts sign <sign> line<1- 4> <text>");
			} else Log.playerMessage(p, "§2/ts sign <sign> line<1- 4> <text>");
			
		}
		else if (args[0].equals("timer") && (p.isOp() || p.hasPermission("timesigns.admin"))) {
			if (!(args.length < 3)) {
				Signs.getSigns().set("Signs." + args[1] + ".timer", Double.parseDouble(args[2]));
				Signs.saveSigns();
				Log.playerMessage(p, "§2Вы установили таймер на §e" + (Double.parseDouble(args[2])/20) + " §2сек.");
			} else Log.playerMessage(p, "§2/ts timer <signName> <timer>");
			
		} else Log.playerMessage(p, "§4У вас нет прав на использование этой команды.");
		
		return false;
	}

}
