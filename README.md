# Chisel Facades
This is a mod for Minecraft 1.6.4 that adds blocks from [Chisel](http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/1288400-chisel) as [BuildCraft](http://www.mod-buildcraft.com/) Facades.

You can download it from [Curse](http://curse.com/project/222784).

## Setting up Forge Gradle
To set up Forge Gradle, run `gradlew setupDecompWorkspace`.

To generate an IDE project, run `gradlew eclipse` (for Eclipse) or `gradlew idea` (for IntelliJ IDEA).

## Building
Put CHISEL-1.6.4-1.5.2.jar and AUTOUTILS-1.6.4-1.0.1.jar in the libs folder and run `gradlew build`.

## Running
To run in your development environment after building, you'll need BuildCraft and CodeChickenCore (for automatic de/reobfuscation) in your mods folder.

To run in a release environment, you'll need the mod itself, Chisel, AutoUtils and BuildCraft in your mods folder. CodeChickenCore isn't required.