package com.choonster.chiselfacades;

import java.util.logging.Level;

import info.jbcs.minecraft.chisel.BlockMarble;
import info.jbcs.minecraft.chisel.Carvable;
import info.jbcs.minecraft.chisel.CarvableVariation;
import info.jbcs.minecraft.chisel.Chisel;
import net.minecraft.block.Block;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.FMLLog;

@Mod(modid = Constants.MODID, name = Constants.MODNAME, version = Constants.VERSION, dependencies = "required-after:BuildCraft|Transport;required-after:Chisel")
public class ChiselFacades {

	@Instance("ChiselFacades")
	public static ChiselFacades instance;

	@EventHandler
	public void init(FMLInitializationEvent event) {
		FMLLog.log(Constants.MODID, Level.INFO, "Init: Block marble = %s, Chisel Loaded = %s", Chisel.blockMarble, Loader.isModLoaded("Chisel"));
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		FMLLog.log(Constants.MODID, Level.INFO, "Post init: Block marble = %s", Chisel.blockMarble);
		FacadeCreator.init();
	}
}
