package com.choonster.chiselfacades;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = Constants.MODID, name = Constants.MODNAME, dependencies = "required-after:BuildCraft|Transport;required-after:chisel")
public class ChiselFacades {

	@Instance(Constants.MODID)
	public static ChiselFacades instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.config = new Configuration(event.getSuggestedConfigurationFile());
		Config.config.load();
		Config.refreshConfig();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		try {
			Logger.info("Creating Facades...");
			int numFacades = FacadeCreator.init();
			Logger.info("Successfully created Facades for %d Chisel block variations", numFacades);
		} catch (Exception e) {
			Logger.fatal(e, "Facade creation failed");
		}
	}
}
