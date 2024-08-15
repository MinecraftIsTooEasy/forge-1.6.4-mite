package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ComponentNetherBridgeEnd extends ComponentNetherBridgePiece {
   private int fillSeed;

   public ComponentNetherBridgeEnd() {
   }

   public ComponentNetherBridgeEnd(int var1, Random var2, StructureBoundingBox var3, int var4) {
      super(var1);
      this.coordBaseMode = var4;
      this.boundingBox = var3;
      this.fillSeed = var2.nextInt();
   }

   public static ComponentNetherBridgeEnd func_74971_a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -3, 0, 5, 10, 8, var5);
      return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null ? new ComponentNetherBridgeEnd(var6, var1, var7, var5) : null;
   }

   protected void func_143011_b(NBTTagCompound var1) {
      super.func_143011_b(var1);
      this.fillSeed = var1.getInteger("Seed");
   }

   protected void func_143012_a(NBTTagCompound var1) {
      super.func_143012_a(var1);
      var1.setInteger("Seed", this.fillSeed);
   }

   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      Random var4 = new Random((long)this.fillSeed);

      int var5;
      int var6;
      int var7;
      for(var5 = 0; var5 <= 4; ++var5) {
         for(var6 = 3; var6 <= 4; ++var6) {
            var7 = var4.nextInt(8);
            this.fillWithBlocks(var1, var3, var5, var6, 0, var5, var6, var7, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
         }
      }

      var5 = var4.nextInt(8);
      this.fillWithBlocks(var1, var3, 0, 5, 0, 0, 5, var5, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
      var5 = var4.nextInt(8);
      this.fillWithBlocks(var1, var3, 4, 5, 0, 4, 5, var5, Block.netherBrick.blockID, Block.netherBrick.blockID, false);

      for(var5 = 0; var5 <= 4; ++var5) {
         var6 = var4.nextInt(5);
         this.fillWithBlocks(var1, var3, var5, 2, 0, var5, 2, var6, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
      }

      for(var5 = 0; var5 <= 4; ++var5) {
         for(var6 = 0; var6 <= 1; ++var6) {
            var7 = var4.nextInt(3);
            this.fillWithBlocks(var1, var3, var5, var6, 0, var5, var6, var7, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
         }
      }

      return true;
   }
}