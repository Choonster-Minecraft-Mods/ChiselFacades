package com.choonster.chiselfacades;

import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;

public class Logger {
	public static void log(Level level, String format, Object... data) {
		FMLLog.log(Constants.MODID, level, format, data);
	}

	public static void severe(String format, Object... data) {
		log(Level.SEVERE, format, data);
	}

	public static void severe(Throwable throwable, String format,
			Object... data) {
		FMLLog.log(Constants.MODID, Level.SEVERE, throwable, format, data);
	}

	public static void warning(String format, Object... data) {
		log(Level.WARNING, format, data);
	}

	public static void info(String format, Object... data) {
		log(Level.INFO, format, data);
	}
}
