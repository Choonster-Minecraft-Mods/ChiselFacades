package com.choonster.chiselfacades;

import cpw.mods.fml.common.event.FMLInterModComms;
import info.jbcs.minecraft.chisel.Configurations;
import info.jbcs.minecraft.chisel.api.ICarvable;
import info.jbcs.minecraft.chisel.block.BlockSnakestone;
import info.jbcs.minecraft.chisel.carving.CarvableVariation;
import info.jbcs.minecraft.chisel.init.ChiselBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.io.PrintWriter;

public class FacadeCreator {
	private static int _numFacades = 0;
	private static PrintWriter writer;

	private static void sendFacadeMessage(Block block, int meta) {
		String blockName = Block.blockRegistry.getNameForObject(block);

		FMLInterModComms.sendMessage("BuildCraft|Transport",
				"add-facade", blockName + "@" + meta);

		if (writer != null) {
			writer.printf("%s - %d - %s\n", blockName, meta, new ItemStack(block, 1, meta).getHasSubtypes());
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
				writer.println("Name - Meta - Subtypes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Uses the same structure as the ModBlocks.load() method so I don't miss any blocks

		if (Configurations.featureEnabled("marble")) {
			registerFacade(ChiselBlocks.marble);

			registerFacade(ChiselBlocks.marbleSlab);

			if (Configurations.featureEnabled("marblePillar")) {
				if (Configurations.oldPillars) {
					// Old Pillar blocks work, old Pillar slabs are broken
					registerFacade(ChiselBlocks.marblePillar);
					Logger.info("Old pillars enabled, adding Pillar block Facades");
				} else {
					// New Pillar blocks are broken, new Pillar slabs work
					registerFacade(ChiselBlocks.marblePillarSlab);
					Logger.info("Old pillars disabled, adding Pillar slab Facades");
				}
			}
		}

		if (Configurations.featureEnabled("limestone")) {
			registerFacade(ChiselBlocks.limestone);

			// Limestone slabs use the same textures as Limestone blocks, don't register them
		}

		if (Configurations.featureEnabled("cobblestone")) {
			registerFacade(ChiselBlocks.cobblestone);
		}

		if (Configurations.featureEnabled("glass")) {
			registerFacade(ChiselBlocks.glass);
		}

		if (Configurations.featureEnabled("sandstone")) {
			registerFacade(ChiselBlocks.sandstone);

			if (Configurations.featureEnabled("snakeSandstone")) {
				registerFacade(ChiselBlocks.sandSnakestone);
			}
		}

		if (Configurations.featureEnabled("sandstoneScribbles")) {
			registerFacade(ChiselBlocks.sandstoneScribbles);
		}

		if (Configurations.featureEnabled("concrete")) {
			registerFacade(ChiselBlocks.concrete);
		}

		if (Configurations.featureEnabled("ironBlock")) {
			registerFacade(ChiselBlocks.iron);
		}

		if (Configurations.featureEnabled("goldBlock")) {
			registerFacade(ChiselBlocks.gold);
		}

		if (Configurations.featureEnabled("diamondBlock")) {
			registerFacade(ChiselBlocks.diamond);
		}

		if (Configurations.featureEnabled("glowstone")) {
			registerFacade(ChiselBlocks.lightstone);
		}

		if (Configurations.featureEnabled("lapisBlock")) {
			registerFacade(ChiselBlocks.lapis);
		}

		if (Configurations.featureEnabled("emeraldBlock")) {
			registerFacade(ChiselBlocks.emerald);
		}

		if (Configurations.featureEnabled("netherBrick")) {
			registerFacade(ChiselBlocks.netherBrick);
		}

		if (Configurations.featureEnabled("netherRack")) {
			registerFacade(ChiselBlocks.netherrack);
		}

		if (Configurations.featureEnabled("cobblestoneMossy")) {
			registerFacade(ChiselBlocks.cobblestoneMossy);
		}

		if (Configurations.featureEnabled("stoneBrick")) {
			registerFacade(ChiselBlocks.stoneBrick);
		}

		if (Configurations.featureEnabled("snakestone")) {
			registerFacade(ChiselBlocks.snakestone);
		}

		if (Configurations.featureEnabled("dirt")) {
			registerFacade(ChiselBlocks.dirt);
		}

		// Can't register Ice or Ice Pillars because they have
		// getRenderType() == 0. BuildCraft tries to add Facades for blocks with
		// this renderType on its own, but it doesn't work properly with
		// Chisel's Ice blocks.

		if (Configurations.featureEnabled("wood")) {
			for (int i = 0; i < ChiselBlocks.planks.length; i++) {
				registerFacade(ChiselBlocks.planks[i]);
			}
		}

		if (Configurations.featureEnabled("obsidian")) {
			registerFacade(ChiselBlocks.obsidian);
		}

		if (Configurations.featureEnabled("snakestoneObsidian")) {
			registerFacade(ChiselBlocks.obsidianSnakestone);
		}

		if (Configurations.featureEnabled("ironBars")) {
			registerFacade(ChiselBlocks.paneIron);
		}

		if (Configurations.featureEnabled("redstoneBlock")) {
			registerFacade(ChiselBlocks.redstone);
		}

		if (Configurations.featureEnabled("holystone")) {
			registerFacade(ChiselBlocks.holystone);
		}

		if (Configurations.featureEnabled("lavastone")) {
			registerFacade(ChiselBlocks.lavastone);
		}

		if (Configurations.featureEnabled("fantasy")) {
			registerFacade(ChiselBlocks.fantasy);
		}

		if (Configurations.featureEnabled("carpet")) {
			registerFacade(ChiselBlocks.carpet);
		}

		if (Configurations.featureEnabled("bookshelf")) {
			registerFacade(ChiselBlocks.bookshelf);
		}

		if (Configurations.featureEnabled("futuristicArmorPlating")) {
			registerFacade(ChiselBlocks.tyrian);
		}

		if (Configurations.featureEnabled("templeBlock")) {
			registerFacade(ChiselBlocks.temple);

			if (Configurations.featureEnabled("templeBlockMossy")) {
				registerFacade(ChiselBlocks.templeMossy);
			}
		}

		if (Configurations.featureEnabled("cloud")) {
			registerFacade(ChiselBlocks.cloud);
		}

		if (Configurations.featureEnabled("factory")) {
			registerFacade(ChiselBlocks.factory);
		}

		if (Configurations.featureEnabled("glassStained"))
			for (int i = 0; i < ChiselBlocks.stainedGlass.length; i++) {
				registerFacade(ChiselBlocks.stainedGlass[i]);
			}

		if (Configurations.featureEnabled("paperWall")) {
			registerFacade(ChiselBlocks.paperWall);
		}

		if (Configurations.featureEnabled("woolenClay")) {
			registerFacade(ChiselBlocks.woolenClay);
		}

		if (Configurations.featureEnabled("laboratory")) {
			registerFacade(ChiselBlocks.laboratory);
		}

		if (Configurations.featureEnabled("pumpkin")) {
			// This doesn't do anything yet, but carving variations for Pumpkins will probably come in a future version
			registerFacade(ChiselBlocks.pumpkin);
		}

		if(Configurations.featureEnabled("leaves")){
			registerFacade(ChiselBlocks.leaf);
		}

		if (Configurations.featureEnabled("voidstone")) {
			registerFacade(ChiselBlocks.voidstone);
		}

		// BuildCraft automatically adds Void Stone Pillar Facades

		if (Configurations.featureEnabled("waterstone")) {
			registerFacade(ChiselBlocks.waterstone);
		}

		if (writer != null) {
			writer.close();
		}

		return _numFacades;
	}
}
