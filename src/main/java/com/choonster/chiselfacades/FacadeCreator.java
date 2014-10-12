package com.choonster.chiselfacades;

import cpw.mods.fml.common.event.FMLInterModComms;
import info.jbcs.minecraft.chisel.ChiselBlocks;
import info.jbcs.minecraft.chisel.Configurations;
import info.jbcs.minecraft.chisel.api.ICarvable;
import info.jbcs.minecraft.chisel.block.BlockSnakestone;
import info.jbcs.minecraft.chisel.carving.CarvableVariation;
import net.minecraft.block.Block;

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

		if (Configurations.featureEnabled("marble")) {
			registerFacade(ChiselBlocks.blockMarble);

			registerFacade(ChiselBlocks.blockMarbleSlab);

			if (Configurations.featureEnabled("marblePillar")) {
				if (Configurations.oldPillars) {
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

		if (Configurations.featureEnabled("limestone")) {
			registerFacade(ChiselBlocks.blockLimestone);

			// Limestone slabs use the same textures as Limestone blocks, don't
			// register them
		}

		if (Configurations.featureEnabled("cobblestone")) {
			registerFacade(ChiselBlocks.blockCobblestone);
		}

		if (Configurations.featureEnabled("glass")) {
			registerFacade(ChiselBlocks.blockGlass);
		}

		if (Configurations.featureEnabled("sandstone")) {
			registerFacade(ChiselBlocks.blockSandstone);

			if (Configurations.featureEnabled("snakeSandstone")) {
				registerFacade(ChiselBlocks.blockSandSnakestone);
			}
		}

		if (Configurations.featureEnabled("sandstoneScribbles")) {
			registerFacade(ChiselBlocks.blockSandstoneScribbles);
		}

		if (Configurations.featureEnabled("concrete")) {
			registerFacade(ChiselBlocks.blockConcrete);
		}

		if (Configurations.featureEnabled("ironBlock")) {
			registerFacade(ChiselBlocks.blockIron);
		}

		if (Configurations.featureEnabled("goldBlock")) {
			registerFacade(ChiselBlocks.blockGold);
		}

		if (Configurations.featureEnabled("diamondBlock")) {
			registerFacade(ChiselBlocks.blockDiamond);
		}

		if (Configurations.featureEnabled("glowstone")) {
			registerFacade(ChiselBlocks.blockLightstone);
		}

		if (Configurations.featureEnabled("lapisBlock")) {
			registerFacade(ChiselBlocks.blockLapis);
		}

		if (Configurations.featureEnabled("emeraldBlock")) {
			registerFacade(ChiselBlocks.blockEmerald);
		}

		if (Configurations.featureEnabled("netherBrick")) {
			registerFacade(ChiselBlocks.blockNetherBrick);
		}

		if (Configurations.featureEnabled("netherRack")) {
			registerFacade(ChiselBlocks.blockNetherrack);
		}

		if (Configurations.featureEnabled("cobblestoneMossy")) {
			registerFacade(ChiselBlocks.blockCobblestoneMossy);
		}

		if (Configurations.featureEnabled("stoneBrick")) {
			registerFacade(ChiselBlocks.stoneBrick);
		}

		if (Configurations.featureEnabled("snakestone")) {
			registerFacade(ChiselBlocks.blockSnakestone);
		}

		if (Configurations.featureEnabled("dirt")) {
			registerFacade(ChiselBlocks.blockDirt);
		}

		// Can't register Ice or Ice Pillars because they have
		// getRenderType() == 0. BuildCraft tries to add Facades for blocks with
		// this renderType on its own, but it doesn't work properly with
		// Chisel's Ice blocks.

		if (Configurations.featureEnabled("wood")) {
			for (int i = 0; i < ChiselBlocks.blockPlanks.length; i++) {
				registerFacade(ChiselBlocks.blockPlanks[i]);
			}
		}

		if (Configurations.featureEnabled("obsidian")) {
			registerFacade(ChiselBlocks.blockObsidian);
		}

		if (Configurations.featureEnabled("snakestoneObsidian")) {
			registerFacade(ChiselBlocks.blockObsidianSnakestone);
		}

		if (Configurations.featureEnabled("ironBars")) {
			registerFacade(ChiselBlocks.blockPaneIron);
		}

		if (Configurations.featureEnabled("redstoneBlock")) {
			registerFacade(ChiselBlocks.blockRedstone);
		}

		if (Configurations.featureEnabled("holystone")) {
			registerFacade(ChiselBlocks.blockHolystone);
		}

		if (Configurations.featureEnabled("lavastone")) {
			registerFacade(ChiselBlocks.blockLavastone);
		}

		if (Configurations.featureEnabled("fantasy")) {
			registerFacade(ChiselBlocks.blockFft);
		}

		if (Configurations.featureEnabled("carpet")) {
			registerFacade(ChiselBlocks.blockCarpet);
		}

		if (Configurations.featureEnabled("bookshelf")) {
			registerFacade(ChiselBlocks.blockBookshelf);
		}

		if (Configurations.featureEnabled("futuristicArmorPlating")) {
			registerFacade(ChiselBlocks.blockTyrian);
		}

		if (Configurations.featureEnabled("templeBlock")) {
			registerFacade(ChiselBlocks.blockTemple);

			if (Configurations.featureEnabled("templeBlockMossy")) {
				registerFacade(ChiselBlocks.blockTempleMossy);
			}
		}

		if (Configurations.featureEnabled("cloud")) {
			registerFacade(ChiselBlocks.blockCloud);
		}

		if (Configurations.featureEnabled("factory")) {
			registerFacade(ChiselBlocks.blockFactory);
		}

		if (Configurations.featureEnabled("glassStained"))
			for (int i = 0; i < ChiselBlocks.blockStainedGlass.length; i++) {
				registerFacade(ChiselBlocks.blockStainedGlass[i]);
			}

		if (Configurations.featureEnabled("paperWall")) {
			registerFacade(ChiselBlocks.blockPaperwall);
		}

		if (Configurations.featureEnabled("woolenClay")) {
			registerFacade(ChiselBlocks.blockWoolenClay);
		}

		if(Configurations.featureEnabled("laboratory")) {
			registerFacade(ChiselBlocks.blockLaboratory);
		}

		return _numFacades;
	}
}
