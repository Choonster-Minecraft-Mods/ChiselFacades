package com.choonster.chiselfacades;

import com.cricketcraft.chisel.api.carving.ICarvingGroup;
import com.cricketcraft.chisel.api.carving.ICarvingVariation;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import team.chisel.Features;
import team.chisel.carving.Carving;
import team.chisel.config.Configurations;

import java.io.PrintWriter;


public class FacadeCreator {
	private static int _numFacades = 0;
	private static PrintWriter writer;

	// Send an IMC message to BuildCraft to register the block + metadata as a Facade
	private static void sendFacadeMessage(Block block, int meta) {
		ItemStack stack = new ItemStack(block, 1, meta);

		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", stack);

		if (writer != null) {
			String blockName = Block.blockRegistry.getNameForObject(block);
			boolean fullBoundingBox =
					block.getBlockBoundsMinX() == 0.0 && block.getBlockBoundsMinY() == 0.0 && block.getBlockBoundsMinZ() == 0.0 &&
							block.getBlockBoundsMaxX() == 1.0 && block.getBlockBoundsMaxY() == 1.0 && block.getBlockBoundsMaxZ() == 1.0;

			writer.printf("%s - %s - %b - %d - %s\n", blockName, block.toString(), fullBoundingBox, meta, stack.getHasSubtypes());
		}

		_numFacades++;
	}

	// Register the variations in the group as Facades
	private static void registerGroup(String groupName) {
		ICarvingGroup group = Carving.chisel.getGroup(groupName);

		if (group == null) {
			Logger.warn("CarvingGroup %s is null!", groupName);
			return;
		}

		for (ICarvingVariation variation : group.getVariations()) {
			Block block = variation.getBlock();
			if (Block.blockRegistry.getNameForObject(block).startsWith("chisel:")) { // Ignore non-Chisel blocks
				sendFacadeMessage(block, variation.getItemMeta());
			}
		}
	}

	// Register the ICarvingVariations in the ICarvingGroup as Facades if the Feature is enabled
	private static void registerGroupIfEnabled(Features feature, String groupName) {
		if (feature.enabled()) {
			registerGroup(groupName);
		}
	}

	// Register any metadata values of the block that have an associated ICarvingVariation if the Feature is enabled
	// Used for blocks like Anti Block and Carpet that don't have an ICarvingGroup
	/*private static <T extends Block & ICarvable> void  registerBlockIfEnabled(Features feature, T block){
		if (feature.enabled()) {
			for (int meta = 0; meta < 16; meta++) {
				if (block.getVariation(new ItemStack(block, 1, meta)) != null){
					sendFacadeMessage(block, meta);
				}
			}
		}
	}*/

	public static int init() {
		if (Config.debugOutputEnabled) {
			try {
				writer = new PrintWriter("ChiselFacades" + (FMLCommonHandler.instance().getEffectiveSide().isClient() ? "Client" : "Server") + "Debug.txt", "UTF-8");
				writer.println("Name - Instance - FullBoundingBox - Meta - Subtypes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// We only add blocks with non-1.0 block bounds now that BuildCraft adds all blocks with 1.0 block bounds itself

		// Ignore Floor Carpets because they use the same textures as the full block version

		// Clouds have 1.0 block bounds, so we don't need to register them

		// All panes (Glass, Stained Glass, Iron Bars, Paper Wall) have 1.0 block bounds by default, so we don't need to register them

		// Ignore Limestone Slabs because they use the same textures as the full block version

		registerGroupIfEnabled(Features.MARBLE, "marble_slab");

		if (Features.MARBLE_PILLAR.enabled()) {
			if (!Configurations.oldPillars) {
				// New Pillar blocks are broken, new Pillar slabs work
				registerGroup("marble_pillar_slab");
				Logger.info("Old pillars disabled, adding Pillar slab Facades");
			}
		}

		// Ignore Presents because they only show the vanilla Oak Wood Planks texture as Facades

		// Ignore Road Lines because they use Material.circuits. BuildCraft rejects any Block with a Material that returns false from Material#blocksMovement.

		// Torches have 1.0 block bounds by default, so we don't need to register them

		if (writer != null) {
			writer.close();
		}

		return _numFacades;
	}
}
