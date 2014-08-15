# Chisel Facades
This is a mod for Minecraft 1.6.4 that adds blocks from [Chisel](http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/1288400-chisel) as [BuildCraft](http://www.mod-buildcraft.com/) Facades.

This branch is designed to work with [delta 534's fork of Chisel](https://github.com/delta534/Chisel) ([download](http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/1288400-chisel?comment=749)).

You can download it from [Curse](http://curse.com/project/222784).

## Setting up Forge Gradle
To set up Forge Gradle, run `gradlew setupDecompWorkspace`.

To generate an IDE project, run `gradlew eclipse` (for Eclipse) or `gradlew idea` (for IntelliJ IDEA).

## Building
Put Chisel-1.6.4-1.6.0.5.zip and Autoutils-1.6.4-1.0.1 in the libs folder and run `gradlew build`.

## Running
To run in your development environment after building, you'll need BuildCraft and CodeChickenCore (for automatic de/reobfuscation) in your mods folder.

To run in a release environment, you'll need the mod itself, Chisel, AutoUtils, CodeChickenCore and BuildCraft in your mods folder.