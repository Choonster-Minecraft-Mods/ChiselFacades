package com.choonster.chiselfacades;

import info.jbcs.minecraft.chisel.Chisel;
import info.jbcs.minecraft.chisel.ChiselBlocks;
import info.jbcs.minecraft.chisel.api.ICarvable;
import info.jbcs.minecraft.chisel.block.BlockCarvableGlass;
import info.jbcs.minecraft.chisel.block.BlockSnakestone;
import info.jbcs.minecraft.chisel.carving.CarvableVariation;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.event.FMLInterModComms;

public class FacadeCreator {
	private static int _numFacades = 0;

	private static void sendFacadeMessage(Block block, int meta) {
		// I use this instead of block.getUnlocalizedName() because the latter
		// returns "tile.marble"; but Chisel registers blocks with
		// "chisel.marble", so the actual block name BuildCraft expects is
		// "chisel:chisel.marble" (which this returns)
		String blockName = Block.blockRegistry.getNameForObject(block);

		FMLInterModComms.sendMessage("BuildCraft|Transport",
				"add-facade", blockName + "@" + meta);
		
		_numFacades++;
	}

	private static <T extends Block & ICarvable> void registerFacade(T block) {
		for (int meta = 0; meta <= 15; meta++) {
			CarvableVariation variation = block.getVariation(meta);
			if (variation != null) {
				sendFacadeMessage(block, meta);
			}
		}
	}

	private static void registerFacade(BlockSnakestone block) {
		sendFacadeMessage(block, Constants.SNAKESTONE_HEAD_META);
		sendFacadeMessage(block, Constants.SNAKESTONE_BODY_META);
	}

	public static int init() {
		// Uses the same structure as the ChiselBlocks.load() method so I don't
		// miss any blocks

		if (Chisel.featureEnabled("marble")) {
			registerFacade(ChiselBlocks.blockMarble);

			registerFacade(ChiselBlocks.blockMarbleSlab);

			if (Chisel.featureEnabled("marblePillar")) {
				if (Chisel.oldPillars) {
					// Old Pillar blocks work, old Pillar slabs are broken
					registerFacade(ChiselBlocks.blockMarblePillar);
					Logger.info("Old pillars enabled, adding Pillar block Facades");
				} else {
					// New Pillar blocks are broken, new Pillar slabs work
					registerFacade(ChiselBlocks.blockMarblePillarSlab);
					Logger.info("Old Pillars disabled, adding Pillar slab Facades");
				}
			}
		}

		if (Chisel.featureEnabled("limestone")) {
			registerFacade(ChiselBlocks.blockLimestone);

			// Limestone slabs use the same textures as Limestone blocks, don't
			// register them
		}

		if (Chisel.featureEnabled("cobblestone")) {
			registerFacade(ChiselBlocks.blockCobblestone);
		}

		if (Chisel.featureEnabled("glass")) {
			registerFacade(ChiselBlocks.blockGlass);
		}

		if (Chisel.featureEnabled("sandstone")) {
			registerFacade(ChiselBlocks.blockSandstone);

			if (Chisel.featureEnabled("snakeSandstone")) {
				registerFacade(ChiselBlocks.blockSandSnakestone);
			}
		}

		if (Chisel.featureEnabled("sandstoneScribbles")) {
			registerFacade(ChiselBlocks.blockSandstoneScribbles);
		}

		if (Chisel.featureEnabled("concrete")) {
			registerFacade(ChiselBlocks.blockConcrete);
		}

		if (Chisel.featureEnabled("ironBlock")) {
			registerFacade(ChiselBlocks.blockIron);
		}

		if (Chisel.featureEnabled("goldBlock")) {
			registerFacade(ChiselBlocks.blockGold);
		}

		if (Chisel.featureEnabled("diamondBlock")) {
			registerFacade(ChiselBlocks.blockDiamond);
		}

		if (Chisel.featureEnabled("glowstone")) {
			registerFacade(ChiselBlocks.blockLightstone);
		}

		if (Chisel.featureEnabled("lapisBlock")) {
			registerFacade(ChiselBlocks.blockLapis);
		}

		if (Chisel.featureEnabled("emeraldBlock")) {
			registerFacade(ChiselBlocks.blockEmerald);
		}

		if (Chisel.featureEnabled("netherBrick")) {
			registerFacade(ChiselBlocks.blockNetherBrick);
		}

		if (Chisel.featureEnabled("netherRack")) {
			registerFacade(ChiselBlocks.blockNetherrack);
		}

		if (Chisel.featureEnabled("cobblestoneMossy")) {
			registerFacade(ChiselBlocks.blockCobblestoneMossy);
		}

		if (Chisel.featureEnabled("stoneBrick")) {
			registerFacade(ChiselBlocks.stoneBrick);
		}

		if (Chisel.featureEnabled("snakestone")) {
			registerFacade(ChiselBlocks.blockSnakestone);
		}

		if (Chisel.featureEnabled("dirt")) {
			registerFacade(ChiselBlocks.blockDirt);
		}

		// Can't register Ice or Ice Pillars because they have
		// getRenderType() == 0. BuildCraft tries to add Facades for blocks with
		// this renderType on its own, but it doesn't work properly with
		// Chisel's Ice blocks.

		if (Chisel.featureEnabled("wood")) {
			for (int i = 0; i < ChiselBlocks.blockPlanks.length; i++) {
				registerFacade(ChiselBlocks.blockPlanks[i]);
			}
		}

		if (Chisel.featureEnabled("obsidian")) {
			registerFacade(ChiselBlocks.blockObsidian);
		}

		if (Chisel.featureEnabled("snakestoneObsidian")) {
			registerFacade(ChiselBlocks.blockObsidianSnakestone);
		}

		if (Chisel.featureEnabled("ironBars")) {
			registerFacade(ChiselBlocks.blockPaneIron);
		}

		if (Chisel.featureEnabled("redstoneBlock")) {
			registerFacade(ChiselBlocks.blockRedstone);
		}

		if (Chisel.featureEnabled("holystone")) {
			registerFacade(ChiselBlocks.blockHolystone);
		}

		if (Chisel.featureEnabled("lavastone")) {
			registerFacade(ChiselBlocks.blockLavastone);
		}

		if (Chisel.featureEnabled("fantasy")) {
			registerFacade(ChiselBlocks.blockFft);
		}

		if (Chisel.featureEnabled("carpet")) {
			registerFacade(ChiselBlocks.blockCarpet);
		}

		if (Chisel.featureEnabled("bookshelf")) {
			registerFacade(ChiselBlocks.blockBookshelf);
		}

		if (Chisel.featureEnabled("futuristicArmorPlating")) {
			registerFacade(ChiselBlocks.blockTyrian);
		}

		if (Chisel.featureEnabled("templeBlock")) {
			registerFacade(ChiselBlocks.blockTemple);

			if (Chisel.featureEnabled("templeBlockMossy")) {
				registerFacade(ChiselBlocks.blockTempleMossy);
			}
		}

		if (Chisel.featureEnabled("cloud")) {
			registerFacade(ChiselBlocks.blockCloud);
		}

		if (Chisel.featureEnabled("factory")) {
			registerFacade(ChiselBlocks.blockFactory);
		}

		if (Chisel.featureEnabled("glassStained"))
			for (int i = 0; i < ChiselBlocks.blockStainedGlass.length; i++) {
				registerFacade(ChiselBlocks.blockStainedGlass[i]);
			}

		if (Chisel.featureEnabled("paperWall")) {
			registerFacade(ChiselBlocks.blockPaperwall);
		}

		if (Chisel.featureEnabled("woolenClay")) {
			registerFacade(ChiselBlocks.blockWoolenClay);
		}

		return _numFacades;
	}
}
