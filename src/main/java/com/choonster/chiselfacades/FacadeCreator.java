package com.choonster.chiselfacades;

import com.cricketcraft.chisel.Features;
import com.cricketcraft.chisel.api.carving.ICarvingGroup;
import com.cricketcraft.chisel.api.carving.ICarvingVariation;
import com.cricketcraft.chisel.carving.Carving;
import com.cricketcraft.chisel.config.Configurations;
import com.cricketcraft.chisel.init.ChiselBlocks;
import com.cricketcraft.chisel.utils.General;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.io.PrintWriter;


public class FacadeCreator {
	private static int _numFacades = 0;
	private static PrintWriter writer;

	private static void sendFacadeMessage(Block block, int meta) {
		ItemStack stack = new ItemStack(block, 1, meta);

		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", stack);

		if (writer != null) {
			String blockName = Block.blockRegistry.getNameForObject(block);
			writer.printf("%s - %s - %d - %d - %s\n", blockName, block.toString(), block.getRenderType(), meta, stack.getHasSubtypes());
		}

		_numFacades++;
	}

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

	private static void registerGroupIfEnabled(Features feature, String groupName) {
		if (feature.enabled()) {
			registerGroup(groupName);
		}
	}

	public static int init() {
		if (Config.debugOutputEnabled) {
			try {
				writer = new PrintWriter("ChiselFacades" + (FMLCommonHandler.instance().getEffectiveSide().isClient() ? "Client" : "Server") + "Debug.txt", "UTF-8");
				writer.println("Name - Instance - RenderType - Meta - Subtypes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String[] stainedGlassNames = new String[16];
		for (int i = 0; i < 16; i++) {
			stainedGlassNames[i] = General.sGNames[i].replaceAll(" ", "").toLowerCase();
		}

		// Ignore AE Certus Quartz and Sky Stone because Chisel doesn't add new variations,
		// it only cycles through existing ones

		// Ignore Thaumcraft Amber because Chisel doesn't add new variations,
		// it only cycles through existing ones

		registerGroupIfEnabled(Features.ANDESITE, "andesite");

		registerGroupIfEnabled(Features.ANTIBLOCK, "antiBlock");

		registerGroupIfEnabled(Features.ARCANE, "arcane");

		// Ignore Auto Chisel, Auto Chisel Upgrades and Ball of Moss because
		// BC doesn't allow Facades of TileEntity blocks (i.e. Auto Chisel) and the other two are only items

		registerGroupIfEnabled(Features.BLOOD_RUNE, "bloodRune");

		// Ignore Blood Magic Blood Block because Chisel doesn't add new variations,
		// it only cycles through existing ones

		registerGroupIfEnabled(Features.BOOKSHELF, "bookshelf");

		registerGroupIfEnabled(Features.BRICK_CUSTOM, "brickCustom");

		// Carpets can't be chiseled, so they don't have a ICarvingGroup to register
		if (Features.CARPET.enabled()) {
			for (int meta = 0; meta < 16; meta++) {
				sendFacadeMessage(ChiselBlocks.carpet_block, meta);
			}
		}

		// Ignore Floor Carpets because they use the same textures as the full block version

		// Ignore Chisel becuase it only adds items

		registerGroupIfEnabled(Features.CLOUD, "cloud");

		registerGroupIfEnabled(Features.COBBLESTONE, "cobblestone");

		registerGroupIfEnabled(Features.COBBLESTONE_MOSSY, "mossy_cobblestone");

		registerGroupIfEnabled(Features.CONCRETE, "concrete");

		registerGroupIfEnabled(Features.DIAMOND_BLOCK, "diamond_block");

		registerGroupIfEnabled(Features.DIORITE, "diorite");

		registerGroupIfEnabled(Features.DIRT, "dirt");

		registerGroupIfEnabled(Features.EMERALD_BLOCK, "emerald_block");

		registerGroupIfEnabled(Features.FACTORY, "factoryblock");

		registerGroupIfEnabled(Features.FANTASY, "fantasyblock");

		registerGroupIfEnabled(Features.FANTASY, "fantasyblock2");

		registerGroupIfEnabled(Features.FUTURA, "futura");

		registerGroupIfEnabled(Features.GLASS, "glass");

		registerGroupIfEnabled(Features.GLASS_PANE, "glass_pane");

		if (Features.GLASS_STAINED.enabled()) {
			for (int i = 0; i < 16; i++) {
				registerGroup("stained_glass_" + stainedGlassNames[i]);
			}
		}

		if (Features.GLASS_STAINED_PANE.enabled()) {
			for (int i = 0; i < 16; i++) {
				registerGroup("stained_glass_pane_" + stainedGlassNames[i]);
			}
		}

		registerGroupIfEnabled(Features.GLOWSTONE, "glowstone");

		registerGroupIfEnabled(Features.GOLD_BLOCK, "gold_block");

		registerGroupIfEnabled(Features.GRANITE, "granite");

		registerGroupIfEnabled(Features.GRIMSTONE, "grimstone");

		registerGroupIfEnabled(Features.HEX_PLATING, "hexPlating");

		registerGroupIfEnabled(Features.HOLYSTONE, "holystone");

		// Can't register Ice or Ice Pillars because they have getRenderType() == 0.
		// BuildCraft tries to add Facades for blocks with this renderType on its own, but it doesn't work properly with
		// Chisel's Ice blocks.

		// Ignore Ice Stairs because they use the same textures as the full block version

		registerGroupIfEnabled(Features.IRON_BARS, "iron_bars");

		registerGroupIfEnabled(Features.IRON_BLOCK, "iron_block");

		// Ignore Jack o'Lanterns because BuildCraft registers them itself

		registerGroupIfEnabled(Features.LABORATORY, "laboratoryblock");

		registerGroupIfEnabled(Features.LAPIS_BLOCK, "lapis_block");

		registerGroupIfEnabled(Features.LAVASTONE, "lavastone");

		registerGroupIfEnabled(Features.LEAVES, "leaves");

		registerGroupIfEnabled(Features.LIMESTONE, "limestone");

		// Ignore Limestone Slabs and Stairs because they use the same textures as the full block version

		registerGroupIfEnabled(Features.MARBLE, "marble");

		registerGroupIfEnabled(Features.MARBLE, "marble_slab");

		// Ignore Marble Stairs because they use the same textures as the slab version

		if (Features.MARBLE_PILLAR.enabled()) {
			if (Configurations.oldPillars) {
				// Old Pillar blocks work, old Pillar slabs are broken
				registerGroup("marble_pillar");
				Logger.info("Old pillars enabled, adding Pillar block Facades");
			} else {
				// New Pillar blocks are broken, new Pillar slabs work
				registerGroup("marble_pillar_slab");
				Logger.info("Old pillars disabled, adding Pillar slab Facades");
			}
		}

		registerGroupIfEnabled(Features.NETHER_BRICK, "nether_brick");

		registerGroupIfEnabled(Features.NETHER_RACK, "netherrack");

		registerGroupIfEnabled(Features.OBSIDIAN, "obsidian");

		// Can't register Packed Ice blocks or pillars due to their renderType (see reasoning for regular Ice).

		registerGroupIfEnabled(Features.PAPER_WALL, "paperwall");

		// Ignore Presents because they only show the vanilla Oak Wood Planks texture as Facades

		// Ignore Pumpkins because BuildCraft registers them itself

		// Ignore Quartz because Chisel doesn't add new variations, it only cycles through existing ones

		// Ignore Railcraft blocks because Chisel doesn't add new variations, it only cycles through existing ones

		registerGroupIfEnabled(Features.REDSTONE_BLOCK, "redstone_block");

		// Looks weird because it's not a full block, but it doesn't look broken
		registerGroupIfEnabled(Features.ROAD_LINE, "road_line");

		registerGroupIfEnabled(Features.SANDSTONE, "sandstone");

		registerGroupIfEnabled(Features.SANDSTONE_SCRIBBLES, "sandstone_scribbles");

		// Ignore Smashing Rock because it's not a block

		// Sand Snakestone is registered with the "sandstone" group

		registerGroupIfEnabled(Features.SNAKESTONE, "stonebrick");

		// Obsidian Snakestone is registered with the "obsidian" group

		registerGroupIfEnabled(Features.STONE_BRICK, "stonebricksmooth");

		registerGroupIfEnabled(Features.TALLOW, "tallow");

		registerGroupIfEnabled(Features.TECHNICAL, "technical");
		registerGroupIfEnabled(Features.TECHNICAL, "technical2");

		registerGroupIfEnabled(Features.TEMPLE_BLOCK, "templeblock");

		registerGroupIfEnabled(Features.TEMPLE_BLOCK_MOSSY, "mossy_templeblock");

		// Ignore Twilight Forest blocks because Chisel doesn't add new variations, it only cycles through existing ones

		registerGroupIfEnabled(Features.THAUMIUM, "thaumium");

		// Looks weird because it's not a full block, but it doesn't look broken
		registerGroupIfEnabled(Features.TORCH, "torch");

		registerGroupIfEnabled(Features.TYRIAN, "tyrian");

		registerGroupIfEnabled(Features.VOIDSTONE, "voidstone");
		registerGroupIfEnabled(Features.VOIDSTONE, "voidstone2");

		// Ignore Voidstone Pillars because they display the purple and black missing texture icon as Facades

		registerGroupIfEnabled(Features.WARNING_SIGN, "warningSign");

		registerGroupIfEnabled(Features.WATERSTONE, "waterstone");

		if (Features.WOOD.enabled()) {
			String[] plank_names = {"oak", "spruce", "birch", "jungle", "acacia", "dark_oak"};
			for (int i = 0; i < plank_names.length; i++) {
				registerGroup(plank_names[i] + "_planks");
			}
		}

		registerGroupIfEnabled(Features.WOOLEN_CLAY, "woolen_clay");

		if (writer != null) {
			writer.close();
		}

		return _numFacades;
	}
}
