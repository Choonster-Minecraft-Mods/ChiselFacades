package com.choonster.chiselfacades;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import com.cricketcraft.chisel.Configurations;
import com.cricketcraft.chisel.api.ICarvable;
import com.cricketcraft.chisel.block.BlockSnakestone;
import com.cricketcraft.chisel.carving.CarvableVariation;
import com.cricketcraft.chisel.init.ModBlocks;
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
			for (int i = 0; i < ModBlocks.pumpkin.length; i++){
				registerFacade(ModBlocks.pumpkin[i]);
			}
		}

		if(Configurations.featureEnabled("jackolantern"))
		{
			for (int i = 0; i < ModBlocks.jackolantern.length; i++){
				registerFacade(ModBlocks.jackolantern[i]);
			}
		}

		if(Configurations.featureEnabled("leaves")){
			registerFacade(ModBlocks.leaf);
		}

		// Can't create Facades for Presents/Chests because they have a non-1.0 bounding box

		if (Configurations.featureEnabled("voidstone")) {
			registerFacade(ModBlocks.voidstone);
			registerFacade(ModBlocks.voidstone2);
		}

		// BuildCraft automatically adds Void Stone Pillar Facades

		if (Configurations.featureEnabled("waterstone")) {
			registerFacade(ModBlocks.waterstone);
		}

		if(Configurations.featureEnabled("hexPlating")){
			registerFacade(ModBlocks.hexPlating);
		}

		if(Configurations.featureEnabled("fantasy2")){
			registerFacade(ModBlocks.fantasy2);
		}

		if(Configurations.featureEnabled("grimstone")){
			registerFacade(ModBlocks.grimstone);
		}

		if(Configurations.featureEnabled("technical")){
			registerFacade(ModBlocks.technical);
			registerFacade(ModBlocks.technical2);
		}

		if(Configurations.featureEnabled("bone")){
			registerFacade(ModBlocks.bone);
		}

		if(Configurations.featureEnabled("scorching"))
		{
			registerFacade(ModBlocks.scorching);
		}

		if(Configurations.featureEnabled("brickCustom"))
		{
			registerFacade(ModBlocks.brickCustom);
		}

		// Can't create Facades for Torches because they have a non-1.0 bounding box

		if(Configurations.featureEnabled("warningSign"))
		{
			registerFacade(ModBlocks.sign);
		}

		if(Configurations.featureEnabled("arcane") && Loader.isModLoaded("Thaumcraft"))
		{
			registerFacade(ModBlocks.arcane);
		}

		if (writer != null) {
			writer.close();
		}

		return _numFacades;
	}
}
