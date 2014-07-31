package com.choonster.chiselfacades;

import info.jbcs.minecraft.chisel.BlockSnakestone;
import info.jbcs.minecraft.chisel.Carvable;
import info.jbcs.minecraft.chisel.CarvableVariation;
import info.jbcs.minecraft.chisel.Chisel;
import net.minecraft.block.Block;
import cpw.mods.fml.common.event.FMLInterModComms;

public class FacadeCreator {
	private static int _numFacades = 0;

	private static void sendFacadeMessage(int blockID, int meta) {
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade",
				blockID + "@" + meta);
		_numFacades++;
	}

	private static <T extends Block & Carvable> void registerFacade(T block) {
		int blockID = block.blockID;

		for (int meta = 0; meta <= 15; meta++) {
			CarvableVariation variation = block.getVariation(meta);
			if (variation != null) {
				sendFacadeMessage(blockID, meta);
			}
		}
	}

	private static void registerFacade(BlockSnakestone block) {
		int blockID = block.blockID;

		sendFacadeMessage(blockID, Constants.SNAKESTONE_HEAD_META);
		sendFacadeMessage(blockID, Constants.SNAKESTONE_BODY_META);
	}

	public static int init() {
		registerFacade(Chisel.blockMarble);
		registerFacade(Chisel.blockMarbleSlab);
		
		if (Chisel.oldPillars) {
			// Old Pillar blocks work, old Pillar slabs are broken
			registerFacade(Chisel.blockMarblePillar);
			Logger.info("Old pillars enabled, adding Pillar block Facades");
		} else {
			// New Pillar blocks are broken, new Pillar slabs work
			registerFacade(Chisel.blockMarblePillarSlab);
			Logger.info("Old Pillars disabled, adding Pillar slab Facades");
		}
		
		registerFacade(Chisel.blockLimestone);
		registerFacade(Chisel.blockCobblestone);
		registerFacade(Chisel.blockGlass);
		
		registerFacade(Chisel.blockSandstone);
		registerFacade(Chisel.blockSandSnakestone);
		registerFacade(Chisel.blockSandstoneScribbles);
		
		registerFacade(Chisel.blockConcrete);
		registerFacade(Chisel.blockIron);
		registerFacade(Chisel.blockGold);
		registerFacade(Chisel.blockDiamond);
		registerFacade(Chisel.blockLightstone);
		registerFacade(Chisel.blockLapis);
		registerFacade(Chisel.blockEmerald);
		registerFacade(Chisel.blockNetherBrick);
		registerFacade(Chisel.blockNetherrack);
		registerFacade(Chisel.blockCobblestoneMossy);
		
		registerFacade(Chisel.stoneBrick);
		registerFacade(Chisel.blockSnakestone);
		
		registerFacade(Chisel.blockIce);
		registerFacade(Chisel.blockIcePillar);
		
		for (int i = 0; i < Chisel.blockPlanks.length; i++) {
			registerFacade(Chisel.blockPlanks[i]);
		}
		
		registerFacade(Chisel.blockObsidian);
		registerFacade(Chisel.blockObsidianSnakestone);
		
		if (Chisel.blockPaneIron !=null){
			registerFacade(Chisel.blockPaneIron);
		}
		
		registerFacade(Chisel.blockRedstone);
		registerFacade(Chisel.blockHolystone);
		registerFacade(Chisel.blockLavastone);
		registerFacade(Chisel.blockFft);
		registerFacade(Chisel.blockCarpet);
		registerFacade(Chisel.blockBookshelf);
		registerFacade(Chisel.blockTyrian);
		registerFacade(Chisel.blockDirt);
		registerFacade(Chisel.blockTemple);
		registerFacade(Chisel.blockTempleMossy);
		registerFacade(Chisel.blockCloud);
		registerFacade(Chisel.blockFactory);
		
		return _numFacades;
	}
}
