package net.minecraft.world.gen.layer;

public class GenLayerZoom extends GenLayer {
   public GenLayerZoom(long par1, GenLayer par3GenLayer) {
      super(par1);
      super.parent = par3GenLayer;
   }

   public int[] getInts(int par1, int par2, int par3, int par4, int z) {
      int var5 = par1 >> 1;
      int var6 = par2 >> 1;
      int var7 = (par3 >> 1) + 3;
      int var8 = (par4 >> 1) + 3;
      int[] var9 = this.parent.getInts(var5, var6, var7, var8, z);
      int[] var10 = IntCache.getIntCache(var7 * 2 * var8 * 2);
      int var11 = var7 << 1;

      int var13;
      for(int var12 = 0; var12 < var8 - 1; ++var12) {
         var13 = var12 << 1;
         int var14 = var13 * var11;
         int var15 = var9[0 + (var12 + 0) * var7];
         int var16 = var9[0 + (var12 + 1) * var7];

         for(int var17 = 0; var17 < var7 - 1; ++var17) {
            long par1_1 = (long)(var17 + var5 << 1);
            long par3_1 = (long)(var12 + var6 << 1);
            this.chunkSeed = this.worldGenSeed;
            this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
            this.chunkSeed += par1_1;
            this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
            this.chunkSeed += par3_1;
            this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
            this.chunkSeed += par1_1;
            this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
            this.chunkSeed += par3_1;
            int var18 = var9[var17 + 1 + (var12 + 0) * var7];
            int var19 = var9[var17 + 1 + (var12 + 1) * var7];
            var10[var14] = var15;
            var10[var14++ + var11] = (this.chunkSeed >> 24 & 1L) == 0L ? var15 : var16;
            this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
            this.chunkSeed += this.worldGenSeed;
            var10[var14] = (this.chunkSeed >> 24 & 1L) == 0L ? var15 : var18;
            this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
            this.chunkSeed += this.worldGenSeed;
            var10[var14++ + var11] = this.modeOrRandom(var15, var18, var16, var19);
            var15 = var18;
            var16 = var19;
         }
      }

      int[] var20 = IntCache.getIntCache(par3 * par4);

      for(var13 = 0; var13 < par4; ++var13) {
         System.arraycopy(var10, (var13 + (par2 & 1)) * (var7 << 1) + (par1 & 1), var20, var13 * par3, par3);
      }

      return var20;
   }

   protected int modeOrRandom(int par1, int par2, int par3, int par4) {
      if (par2 == par3 && par3 == par4) {
         return par2;
      } else if (par1 == par2 && par1 == par3) {
         return par1;
      } else if (par1 == par2 && par1 == par4) {
         return par1;
      } else if (par1 == par3 && par1 == par4) {
         return par1;
      } else if (par1 == par2 && par3 != par4) {
         return par1;
      } else if (par1 == par3 && par2 != par4) {
         return par1;
      } else if (par1 == par4 && par2 != par3) {
         return par1;
      } else if (par2 == par1 && par3 != par4) {
         return par2;
      } else if (par2 == par3 && par1 != par4) {
         return par2;
      } else if (par2 == par4 && par1 != par3) {
         return par2;
      } else if (par3 == par1 && par2 != par4) {
         return par3;
      } else if (par3 == par2 && par1 != par4) {
         return par3;
      } else if (par3 == par4 && par1 != par2) {
         return par3;
      } else if (par4 == par1 && par2 != par3) {
         return par3;
      } else if (par4 == par2 && par1 != par3) {
         return par3;
      } else if (par4 == par3 && par1 != par2) {
         return par3;
      } else {
         int var5 = (int)(this.chunkSeed >> 24 & 3L);
         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
         this.chunkSeed += this.worldGenSeed;
         return var5 == 0 ? par1 : (var5 == 1 ? par2 : (var5 == 2 ? par3 : par4));
      }
   }

   public static GenLayer magnify(long par0, GenLayer par2GenLayer, int par3) {
      Object var4 = par2GenLayer;

      for(int var5 = 0; var5 < par3; ++var5) {
         var4 = new GenLayerZoom(par0 + (long)var5, (GenLayer)var4);
      }

      return (GenLayer)var4;
   }
}