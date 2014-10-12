# Chisel Facades
This is a mod for Minecraft 1.7.10 that adds blocks from [Chisel](http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/1288400-chisel) as [BuildCraft](http://www.mod-buildcraft.com/) Facades.

You can download it from [Curse](http://curse.com/project/222784).

## Setting up Forge Gradle
To set up Forge Gradle, run `gradlew setupDecompWorkspace`.

To generate an IDE project, run `gradlew eclipse` (for Eclipse) or `gradlew idea` (for IntelliJ IDEA).

## Building
Put Chisel in the libs folder and run `gradlew build`.

## Running
To run in your development environment after building, you'll need a dev version of BuildCraft 6.0.x in your mods folder.

To run in a release environment, you'll need the mod itself, Chisel and BuildCraft in your mods folder.