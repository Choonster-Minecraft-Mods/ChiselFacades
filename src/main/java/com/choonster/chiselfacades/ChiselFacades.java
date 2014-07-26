package com.choonster.chiselfacades;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = Constants.MODID, name = Constants.MODNAME, version = Constants.VERSION, dependencies = "required-after:BuildCraft|Transport;required-after:Chisel")
public class ChiselFacades {

	@Instance("ChiselFacades")
	public static ChiselFacades instance;

	@EventHandler
	public void init(FMLInitializationEvent event) {
		try {
			FacadeCreator.init();
		} catch (Exception e) {
			Logger.severe("Facade creation failed - %s", e);					
		}
	}
}
