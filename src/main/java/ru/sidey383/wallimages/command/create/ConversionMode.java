package ru.sidey383.wallimages.command.create;

import java.util.List;

public enum ConversionMode {
	
	Vertical(List.of("vert", "vertical")), Horizontal(List.of("horizontal", "horiz")), SaveFull(List.of("savefull")), UseFullSpace(List.of("fullspace"));
	
	List<String> alias;
	
	ConversionMode(List<String> alias) {
		this.alias = alias;
	}
	
	public static ConversionMode getMode(String str) {
		str = str.toLowerCase();
		for(ConversionMode mode : values()) {
			if(mode.isMode(str)) {
				return mode;
			}
		}
		return null;
	}
	
	
	public boolean isMode(String str) {
		return alias.contains(str);
	}
	
}
