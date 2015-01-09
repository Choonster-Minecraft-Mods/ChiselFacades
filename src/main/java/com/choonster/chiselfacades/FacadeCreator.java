package com.choonster.chiselfacades;

import com.cricketcraft.chisel.api.ICarvable;
import com.cricketcraft.chisel.block.BlockSnakestone;
import com.cricketcraft.chisel.carving.CarvableVariation;
import com.cricketcraft.chisel.config.Configurations;
import com.cricketcraft.chisel.init.ChiselBlocks;
import com.cricketcraft.chisel.init.Features;
import cpw.mods.fml.common.event.FMLInterModComms;
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
		boolean hasVariations = false;

		for (int meta = 0; meta <= 15; meta++) {
			CarvableVariation variation = block.getVariation(meta);
			if (variation != null) {
				hasVariations = true;
				sendFacadeMessage(block, meta);
			}
		}

		if (!hasVariations) {
			Logger.warn("Block has no variations. %s - %s", block.getUnlocalizedName(), block);
		}
	}

	private static void registerFacadeIfEnabled(Features feature, BlockSnakestone block) {
		if (feature.enabled()) {
			sendFacadeMessage(block, Constants.SNAKESTONE_HEAD_META);
			sendFacadeMessage(block, Constants.SNAKESTONE_BODY_META);
		}
	}

	private static <T extends Block & ICarvable> void registerFacadeIfEnabled(Features feature, T block) {
		if (feature.enabled()) {
			registerFacade(block);
		}
	}

	private static <T extends Block & ICarvable> void registerFacadeIfEnabled(Features feature, T[] blocks) {
		if (feature.enabled()) {
			for (int i = 0; i < blocks.length; i++) {
				registerFacade(blocks[i]);
			}
		}
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

		// Ignore AE Certus Quartz and Sky Stone because Chisel doesn't add new variations,
		// it only cycles through existing ones

		registerFacadeIfEnabled(Features.ANDESITE, ChiselBlocks.andesite);

		registerFacadeIfEnabled(Features.ARCANE, ChiselBlocks.arcane);

		// Ignore Auto Chisel, Auto Chisel Upgrades and Ball of Moss because
		// BC doesn't allow Facades of TileEntity blocks (i.e. Auto Chisel) and the other two are only items

		registerFacadeIfEnabled(Features.BLOOD_RUNE, ChiselBlocks.bloodRune);

		registerFacadeIfEnabled(Features.BOOKSHELF, ChiselBlocks.bookshelf);

		registerFacadeIfEnabled(Features.BRICK_CUSTOM, ChiselBlocks.brickCustom);

		registerFacadeIfEnabled(Features.CARPET, ChiselBlocks.carpet_block);

		// Ignore Carpet Floor because it uses the same textures as the full block version

		registerFacadeIfEnabled(Features.CLOUD, ChiselBlocks.cloud);

		registerFacadeIfEnabled(Features.COBBLESTONE, ChiselBlocks.cobblestone);

		registerFacadeIfEnabled(Features.COBBLESTONE_MOSSY, ChiselBlocks.mossy_cobblestone);

		registerFacadeIfEnabled(Features.COLORED_SAND, ChiselBlocks.colored_sand);

		registerFacadeIfEnabled(Features.CONCRETE, ChiselBlocks.concrete);

		registerFacadeIfEnabled(Features.DIAMOND_BLOCK, ChiselBlocks.diamond_block);

		registerFacadeIfEnabled(Features.DIORITE, ChiselBlocks.diorite);

		registerFacadeIfEnabled(Features.DIRT, ChiselBlocks.dirt);

		registerFacadeIfEnabled(Features.EMERALD_BLOCK, ChiselBlocks.emerald_block);

		registerFacadeIfEnabled(Features.FACTORY, ChiselBlocks.factoryblock);

		registerFacadeIfEnabled(Features.FANTASY, ChiselBlocks.fantasyblock);

		registerFacadeIfEnabled(Features.FANTASY2, ChiselBlocks.fantasyblock2);

		registerFacadeIfEnabled(Features.FUTURA, ChiselBlocks.futura);

		registerFacadeIfEnabled(Features.GLASS, ChiselBlocks.glass);

		registerFacadeIfEnabled(Features.GLASS_PANE, ChiselBlocks.glass_pane);

		registerFacadeIfEnabled(Features.GLASS_STAINED, ChiselBlocks.stainedGlass);

		registerFacadeIfEnabled(Features.GLASS_STAINED_PANE, ChiselBlocks.stainedGlassPane);

		registerFacadeIfEnabled(Features.GLOWSTONE, ChiselBlocks.glowstone);

		registerFacadeIfEnabled(Features.GOLD_BLOCK, ChiselBlocks.gold_block);

		registerFacadeIfEnabled(Features.GRANITE, ChiselBlocks.granite);

		registerFacadeIfEnabled(Features.GRIMSTONE, ChiselBlocks.grimstone);

		registerFacadeIfEnabled(Features.HEX_PLATING, ChiselBlocks.hexPlating);

		registerFacadeIfEnabled(Features.HOLYSTONE, ChiselBlocks.holystone);

		// Can't register Ice or Ice Pillars because they have getRenderType() == 0.
		// BuildCraft tries to add Facades for blocks with this renderType on its own, but it doesn't work properly with
		// Chisel's Ice blocks.

		// Ignore Ice Stairs because they use the same textures as the full block version

		registerFacadeIfEnabled(Features.IRON_BARS, ChiselBlocks.iron_bars);

		registerFacadeIfEnabled(Features.IRON_BLOCK, ChiselBlocks.iron_block);

		// Ignore Jack o'Lanterns because BuildCraft registers them itself

		registerFacadeIfEnabled(Features.LABORATORY, ChiselBlocks.laboratoryblock);

		registerFacadeIfEnabled(Features.LAPIS_BLOCK, ChiselBlocks.lapis_block);

		registerFacadeIfEnabled(Features.LAVASTONE, ChiselBlocks.lavastone);

		registerFacadeIfEnabled(Features.LEAVES, ChiselBlocks.leaves);

		registerFacadeIfEnabled(Features.LIMESTONE, ChiselBlocks.limestone);

		// Ignore Limestone Slabs and Stairs because they use the same textures as the full block version

		registerFacadeIfEnabled(Features.MARBLE, ChiselBlocks.marble);

		registerFacadeIfEnabled(Features.MARBLE, ChiselBlocks.marble_slab);

		// Ignore Marble Stairs because they use the same textures as the slab version

		if (Features.MARBLE_PILLAR.enabled()) {
			if (Configurations.oldPillars) {
				// Old Pillar blocks work, old Pillar slabs are broken
				registerFacade(ChiselBlocks.marble_pillar);
				Logger.info("Old pillars enabled, adding Pillar block Facades");
			} else {
				// New Pillar blocks are broken, new Pillar slabs work
				registerFacade(ChiselBlocks.marble_pillar_slab);
				Logger.info("Old pillars disabled, adding Pillar slab Facades");
			}
		}

		registerFacadeIfEnabled(Features.NETHER_BRICK, ChiselBlocks.nether_brick);

		registerFacadeIfEnabled(Features.NETHER_RACK, ChiselBlocks.netherrack);

		registerFacadeIfEnabled(Features.OBSIDIAN, ChiselBlocks.obsidian);

		// Can't register Packed Ice blocks or pillars due to their renderType (see reasoning for regular Ice).

		registerFacadeIfEnabled(Features.PAPER_WALL, ChiselBlocks.paperwall);

		// Ignore Presents because they only show the vanilla Oak Wood Planks texture as Facades

		// Ignore Pumpkins because BuildCraft registers them itself

		// Ignore Quartz because Chisel doesn't add new variations, it only cycles through existing ones

		// Ignore Railcraft blocks because Chisel doesn't add new variations, it only cycles through existing ones

		registerFacadeIfEnabled(Features.REDSTONE_BLOCK, ChiselBlocks.redstone_block);

		if (Features.ROAD_LINE.enabled()) {
			// Looks weird because it's not a full block, but it doesn't look broken
			sendFacadeMessage(ChiselBlocks.road_line, 0);
		}

		registerFacadeIfEnabled(Features.SANDSTONE, ChiselBlocks.sandstone);

		registerFacadeIfEnabled(Features.SANDSTONE_SCRIBBLES, ChiselBlocks.sandstone_scribbles);

		// Ignore Smashing Rock because it's not a block

		registerFacadeIfEnabled(Features.SNAKE_SANDSTONE, ChiselBlocks.sand_snakestone);

		registerFacadeIfEnabled(Features.SNAKESTONE, ChiselBlocks.stone_snakestone);

		registerFacadeIfEnabled(Features.SNAKESTONE_OBSIDIAN, ChiselBlocks.obsidian_snakestone);

		registerFacadeIfEnabled(Features.STONE_BRICK, ChiselBlocks.stonebricksmooth);

		registerFacadeIfEnabled(Features.TECHNICAL, ChiselBlocks.technical);
		registerFacadeIfEnabled(Features.TECHNICAL, ChiselBlocks.technical2);

		registerFacadeIfEnabled(Features.TEMPLE_BLOCK, ChiselBlocks.templeblock);

		registerFacadeIfEnabled(Features.TEMPLE_BLOCK_MOSSY, ChiselBlocks.mossy_templeblock);

		// Ignore Twilight Forest blocks because Chisel doesn't add new variations, it only cycles through existing ones

		registerFacadeIfEnabled(Features.TYRIAN, ChiselBlocks.tyrian);

		registerFacadeIfEnabled(Features.VOIDSTONE, ChiselBlocks.voidstone);
		registerFacadeIfEnabled(Features.VOIDSTONE, ChiselBlocks.voidstone2);

		// Can't register Voidstone Pillars due to their renderType (see reasoning for Ice).

		registerFacadeIfEnabled(Features.WATERSTONE, ChiselBlocks.waterstone);

		registerFacadeIfEnabled(Features.WOOD, ChiselBlocks.planks);

		registerFacadeIfEnabled(Features.WOOLEN_CLAY, ChiselBlocks.woolen_clay);

		if (writer != null) {
			writer.close();
		}

		return _numFacades;
	}
}
