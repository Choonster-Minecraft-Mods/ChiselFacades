package com.choonster.chiselfacades;

import net.minecraftforge.common.config.Configuration;

public class Config {
	public static Configuration config;

	public static boolean debugOutputEnabled;

	public static void refreshConfig() {
		debugOutputEnabled = config.getBoolean("debugOutputEnabled", "general", false, "If true, prints debug info to ChiselFacadesDebug.txt");

		if (config.hasChanged()) {
			config.save();
		}
	}
}
