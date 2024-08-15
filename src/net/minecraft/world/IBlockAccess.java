package net.minecraft.world;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3Pool;
import net.minecraft.world.biome.BiomeGenBase;

public interface IBlockAccess {
   int getBlockId(int var1, int var2, int var3);

   Block getBlock(int var1, int var2, int var3);

   TileEntity getBlockTileEntity(int var1, int var2, int var3);

   int getLightBrightnessForSkyBlocks(int var1, int var2, int var3, int var4);

   float getBrightness(int var1, int var2, int var3, int var4);

   float getLightBrightness(int var1, int var2, int var3);

   int getBlockMetadata(int var1, int var2, int var3);

   Material getBlockMaterial(int var1, int var2, int var3);

   Material getBlockMaterial(int var1);

   boolean isBlockStandardFormOpaqueCube(int var1, int var2, int var3);

   boolean isBlockNormalCube(int var1, int var2, int var3);

   boolean isAirBlock(int var1, int var2, int var3);

   BiomeGenBase getBiomeGenForCoords(int var1, int var2);

   int getHeight();

   boolean extendedLevelsInChunkCache();

   boolean isBlockTopFlatAndSolid(int var1, int var2, int var3);

   Vec3Pool getWorldVec3Pool();

   int isBlockProvidingPowerTo(int var1, int var2, int var3, int var4);

   World getWorld();

   boolean isBlockSolid(int var1, int var2, int var3);
}