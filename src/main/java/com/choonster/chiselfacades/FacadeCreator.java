package com.choonster.chiselfacades;

import cpw.mods.fml.common.event.FMLInterModComms;
import info.jbcs.minecraft.chisel.Configurations;
import info.jbcs.minecraft.chisel.api.ICarvable;
import info.jbcs.minecraft.chisel.block.BlockSnakestone;
import info.jbcs.minecraft.chisel.carving.CarvableVariation;
import info.jbcs.minecraft.chisel.init.ModBlocks;
import net.minecraft.block.Block;

import java.io.PrintWriter;

public class FacadeCreator {
	private static int _numFacades = 0;
	private static PrintWriter writer;

	private static void sendFacadeMessage(Block block, int meta) {
		// I use this instead of block.getUnlocalizedName() because the latter
		// returns "tile.marble"; but Chisel registers blocks with
		// "chisel.marble", so the actual block name BuildCraft expects is
		// "chisel:chisel.marble" (which this returns)
		String blockName = Block.blockRegistry.getNameForObject(block);

		FMLInterModComms.sendMessage("BuildCraft|Transport",
				"add-facade", blockName + "@" + meta);

		if (writer != null) {
			writer.printf("%s - %d - %d\n", blockName, meta, block.getRenderType());
		}

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
		if (Config.debugOutputEnabled) {
			try {
				writer = new PrintWriter("ChiselFacadesDebug.txt", "UTF-8");
				writer.println("Name - Meta - RenderType");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Uses the same structure as the ModBlocks.load() method so I don't miss any blocks

		if (Configurations.featureEnabled("marble")) {
			registerFacade(ModBlocks.marble);

			registerFacade(ModBlocks.marbleSlab);

			if (Configurations.featureEnabled("marblePillar")) {
				if (Configurations.oldPillars) {
					// Old Pillar blocks work, old Pillar slabs are broken
					registerFacade(ModBlocks.marblePillar);
					Logger.info("Old pillars enabled, adding Pillar block Facades");
				} else {
					// New Pillar blocks are broken, new Pillar slabs work
					registerFacade(ModBlocks.marblePillarSlab);
					Logger.info("Old pillars disabled, adding Pillar slab Facades");
				}
			}
		}

		if (Configurations.featureEnabled("limestone")) {
			registerFacade(ModBlocks.limestone);

			// Limestone slabs use the same textures as Limestone blocks, don't register them
		}

		if (Configurations.featureEnabled("cobblestone")) {
			registerFacade(ModBlocks.cobblestone);
		}

		if (Configurations.featureEnabled("glass")) {
			registerFacade(ModBlocks.glass);
		}

		if (Configurations.featureEnabled("sandstone")) {
			registerFacade(ModBlocks.sandstone);

			if (Configurations.featureEnabled("snakeSandstone")) {
				registerFacade(ModBlocks.sandSnakestone);
			}
		}

		if (Configurations.featureEnabled("sandstoneScribbles")) {
			registerFacade(ModBlocks.sandstoneScribbles);
		}

		if (Configurations.featureEnabled("concrete")) {
			registerFacade(ModBlocks.concrete);
		}

		if (Configurations.featureEnabled("ironBlock")) {
			registerFacade(ModBlocks.iron);
		}

		if (Configurations.featureEnabled("goldBlock")) {
			registerFacade(ModBlocks.gold);
		}

		if (Configurations.featureEnabled("diamondBlock")) {
			registerFacade(ModBlocks.diamond);
		}

		if (Configurations.featureEnabled("glowstone")) {
			registerFacade(ModBlocks.lightstone);
		}

		if (Configurations.featureEnabled("lapisBlock")) {
			registerFacade(ModBlocks.lapis);
		}

		if (Configurations.featureEnabled("emeraldBlock")) {
			registerFacade(ModBlocks.emerald);
		}

		if (Configurations.featureEnabled("netherBrick")) {
			registerFacade(ModBlocks.netherBrick);
		}

		if (Configurations.featureEnabled("netherRack")) {
			registerFacade(ModBlocks.netherrack);
		}

		if (Configurations.featureEnabled("cobblestoneMossy")) {
			registerFacade(ModBlocks.cobblestoneMossy);
		}

		if (Configurations.featureEnabled("stoneBrick")) {
			registerFacade(ModBlocks.stoneBrick);
		}

		if (Configurations.featureEnabled("snakestone")) {
			registerFacade(ModBlocks.snakestone);
		}

		if (Configurations.featureEnabled("dirt")) {
			registerFacade(ModBlocks.dirt);
		}

		// Can't register Ice or Ice Pillars because they have
		// getRenderType() == 0. BuildCraft tries to add Facades for blocks with
		// this renderType on its own, but it doesn't work properly with
		// Chisel's Ice blocks.

		if (Configurations.featureEnabled("wood")) {
			for (int i = 0; i < ModBlocks.planks.length; i++) {
				registerFacade(ModBlocks.planks[i]);
			}
		}

		if (Configurations.featureEnabled("obsidian")) {
			registerFacade(ModBlocks.obsidian);
		}

		if (Configurations.featureEnabled("snakestoneObsidian")) {
			registerFacade(ModBlocks.obsidianSnakestone);
		}

		if (Configurations.featureEnabled("ironBars")) {
			registerFacade(ModBlocks.paneIron);
		}

		if (Configurations.featureEnabled("redstoneBlock")) {
			registerFacade(ModBlocks.redstone);
		}

		if (Configurations.featureEnabled("holystone")) {
			registerFacade(ModBlocks.holystone);
		}

		if (Configurations.featureEnabled("lavastone")) {
			registerFacade(ModBlocks.lavastone);
		}

		if (Configurations.featureEnabled("fantasy")) {
			registerFacade(ModBlocks.fantasy);
		}

		if (Configurations.featureEnabled("carpet")) {
			registerFacade(ModBlocks.carpet);
		}

		if (Configurations.featureEnabled("bookshelf")) {
			registerFacade(ModBlocks.bookshelf);
		}

		if (Configurations.featureEnabled("futuristicArmorPlating")) {
			registerFacade(ModBlocks.tyrian);
		}

		if (Configurations.featureEnabled("templeBlock")) {
			registerFacade(ModBlocks.temple);

			if (Configurations.featureEnabled("templeBlockMossy")) {
				registerFacade(ModBlocks.templeMossy);
			}
		}

		if (Configurations.featureEnabled("cloud")) {
			registerFacade(ModBlocks.cloud);
		}

		if (Configurations.featureEnabled("factory")) {
			registerFacade(ModBlocks.factory);
		}

		if (Configurations.featureEnabled("glassStained"))
			for (int i = 0; i < ModBlocks.stainedGlass.length; i++) {
				registerFacade(ModBlocks.stainedGlass[i]);
			}

		if (Configurations.featureEnabled("paperWall")) {
			registerFacade(ModBlocks.paperWall);
		}

		if (Configurations.featureEnabled("woolenClay")) {
			registerFacade(ModBlocks.woolenClay);
		}

		if (Configurations.featureEnabled("laboratory")) {
			registerFacade(ModBlocks.laboratory);
		}

		if (Configurations.featureEnabled("pumpkin")) {
			// This doesn't do anything yet, but carving variations for Pumpkins will probably come in a future version
			registerFacade(ModBlocks.pumpkin);
		}

		if (Configurations.featureEnabled("voidstone")) {
			registerFacade(ModBlocks.voidstone);
		}

		// BuildCraft automatically adds Void Stone Pillar Facades

		if (writer != null) {
			writer.close();
		}

		return _numFacades;
	}
}
