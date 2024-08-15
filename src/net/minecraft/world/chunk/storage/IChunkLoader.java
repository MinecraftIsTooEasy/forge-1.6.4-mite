package net.minecraft.world.chunk.storage;

import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.io.IOException;

public interface IChunkLoader {
   Chunk loadChunk(World var1, int var2, int var3) throws IOException;

   void saveChunk(World var1, Chunk var2) throws MinecraftException, IOException;

   void saveExtraChunkData(World var1, Chunk var2);

   void chunkTick();

   void saveExtraData();
}