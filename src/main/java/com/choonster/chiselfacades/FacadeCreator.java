package com.choonster.chiselfacades;

import info.jbcs.minecraft.chisel.BlockGlassCarvable;
import info.jbcs.minecraft.chisel.BlockMarble;
import info.jbcs.minecraft.chisel.BlockMarbleCarpet;
import info.jbcs.minecraft.chisel.BlockMarbleIce;
import info.jbcs.minecraft.chisel.BlockMarbleTexturedOre;
import info.jbcs.minecraft.chisel.BlockSnakestone;
import info.jbcs.minecraft.chisel.Carvable;
import info.jbcs.minecraft.chisel.CarvableVariation;
import info.jbcs.minecraft.chisel.Chisel;
import cpw.mods.fml.common.event.FMLInterModComms;

public class FacadeCreator {
	private static void sendFacadeMessage(int blockID, int meta) {
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade",
				blockID + "@" + meta);
	}

	private static void registerFacade(Carvable block, int blockID) {
		for (int meta = 0; meta <= 15; meta++) {
			CarvableVariation variation = block.getVariation(meta);
			if (variation != null) {
				sendFacadeMessage(blockID, meta);
			}
		}
	}

	private static void registerFacade(BlockMarble block) {
		registerFacade(block, block.blockID);
	}

	private static void registerFacade(BlockMarbleIce block) {
		registerFacade(block, block.blockID);
	}

	private static void registerFacade(BlockMarbleTexturedOre block) {
		registerFacade(block, block.blockID);
	}

	private static void registerFacade(BlockMarbleCarpet block) {
		registerFacade(block, block.blockID);
	}

	private static void registerFacade(BlockGlassCarvable block) {
		registerFacade(block, block.blockID);
	}

	private static void registerFacade(BlockSnakestone block) {
		int blockID = block.blockID;

		sendFacadeMessage(blockID, Constants.SNAKESTONE_HEAD_META);
		sendFacadeMessage(blockID, Constants.SNAKESTONE_BODY_META);
	}

	public static void init() {
		registerFacade(Chisel.blockMarble);
		registerFacade(Chisel.blockLimestone);
		registerFacade(Chisel.blockMarbleSlab);
		registerFacade(Chisel.blockLimestoneSlab);
		registerFacade(Chisel.blockCobblestone);
		registerFacade(Chisel.blockGlass);
		registerFacade(Chisel.blockSandstone);
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
		registerFacade(Chisel.blockIce);
		registerFacade(Chisel.blockObsidian);
		registerFacade(Chisel.blockRedstone);
		registerFacade(Chisel.blockHolystone);
		registerFacade(Chisel.blockLavastone);
		registerFacade(Chisel.blockFft);
		registerFacade(Chisel.blockCarpet);
		registerFacade(Chisel.blockCarpetFloor);
		registerFacade(Chisel.blockBookshelf);
		registerFacade(Chisel.blockTyrian);
		registerFacade(Chisel.blockDirt);
		registerFacade(Chisel.blockCloud);
		registerFacade(Chisel.blockTemple);
		registerFacade(Chisel.blockTempleMossy);
		registerFacade(Chisel.blockFactory);

		registerFacade(Chisel.blockMarblePillar);
		registerFacade(Chisel.blockIcePillar);

		if (!Chisel.oldPillars) { // New-style pillars are enabled, register
									// facades for pillar slabs
			registerFacade(Chisel.blockMarblePillarSlab);
		}

		registerFacade(Chisel.blockSnakestone);
		registerFacade(Chisel.blockSandSnakestone);
		registerFacade(Chisel.blockObsidianSnakestone);

		for (int i = 0; i < Chisel.blockPlanks.length; i++) {
			registerFacade(Chisel.blockPlanks[i]);
		}
	}
}
