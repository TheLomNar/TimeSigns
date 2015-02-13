package ru.lomnar.timesigns;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.block.Sign;

public class RunSigns implements Runnable {
	
	private Iterator<String> iteraror1 = new ArrayList<String>().iterator();;
	private Iterator<String> iteraror2 = new ArrayList<String>().iterator();
	private Iterator<String> iteraror3 = new ArrayList<String>().iterator();
	private Iterator<String> iteraror4 = new ArrayList<String>().iterator();
	private String signName = null;
	private Sign sign;
	
	public RunSigns(String signName, Sign sign) {
		this.signName = signName;
		this.sign = sign;
	}

	@Override
	public void run() {
		
		if (!RunSigns.this.iteraror1.hasNext()) {	
			RunSigns.this.iteraror1 = Signs.getSigns().getStringList("Signs." + signName + ".line1").iterator();
		}
		if (!RunSigns.this.iteraror2.hasNext()) {
			RunSigns.this.iteraror2 = Signs.getSigns().getStringList("Signs." + signName + ".line2").iterator();
		}
		if (!RunSigns.this.iteraror3.hasNext()) {
			RunSigns.this.iteraror3 = Signs.getSigns().getStringList("Signs." + signName + ".line3").iterator();
		}
		if (!RunSigns.this.iteraror4.hasNext()) {
			RunSigns.this.iteraror4 = Signs.getSigns().getStringList("Signs." + signName + ".line4").iterator();
		}
		
		if (Signs.getSigns().getStringList("Signs." + signName + ".line1").size() != 0) { sign.setLine(0, (String)RunSigns.this.iteraror1.next().toString().replace("&", "§")); } else { sign.setLine(0, ""); sign.update(); }
		if (Signs.getSigns().getStringList("Signs." + signName + ".line2").size() != 0) { sign.setLine(1, (String)RunSigns.this.iteraror2.next().toString().replace("&", "§")); } else { sign.setLine(1, ""); sign.update(); }
		if (Signs.getSigns().getStringList("Signs." + signName + ".line3").size() != 0) { sign.setLine(2, (String)RunSigns.this.iteraror3.next().toString().replace("&", "§")); } else { sign.setLine(2, ""); sign.update(); }
		if (Signs.getSigns().getStringList("Signs." + signName + ".line4").size() != 0) { sign.setLine(3, (String)RunSigns.this.iteraror4.next().toString().replace("&", "§")); } else { sign.setLine(3, ""); sign.update(); }
		sign.update();
		
	}

}
