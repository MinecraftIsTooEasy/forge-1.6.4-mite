package net.minecraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecartTNT;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumDirection;
import net.minecraft.util.EnumFace;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockRailBase extends Block {
   protected final boolean isPowered;

   public static final boolean isRailBlockAt(World par0World, int par1, int par2, int par3) {
      return isRailBlock(par0World.getBlockId(par1, par2, par3));
   }

   public static final boolean isRailBlock(int par0) {
      return par0 == Block.rail.blockID || par0 == Block.railPowered.blockID || par0 == Block.railDetector.blockID || par0 == Block.railActivator.blockID;
   }

   protected BlockRailBase(int par1, boolean par2) {
      super(par1, Material.circuits, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
      this.isPowered = par2;
      this.setBlockBoundsForAllThreads(0.0, 0.0, 0.0, 1.0, 0.125, 1.0);
      this.setMaxStackSize(8);
      this.setCreativeTab(CreativeTabs.tabTransport);
   }

   public boolean isPowered() {
      return this.isPowered;
   }

   public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
      if (var5 >= 2 && var5 <= 5) {
         this.setBlockBoundsForCurrentThread(0.0, 0.0, 0.0, 1.0, 0.625, 1.0);
      } else {
         this.setBlockBoundsForCurrentThread(0.0, 0.0, 0.0, 1.0, 0.125, 1.0);
      }

   }

   public int getRenderType() {
      return 9;
   }

   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
      return block_below != null && block_below.isTopFlatAndSolid(block_below_metadata);
   }

   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
      if (!par1World.isRemote) {
         this.refreshTrackShape(par1World, par2, par3, par4, true);
         if (this.isPowered) {
            this.onNeighborBlockChange(par1World, par2, par3, par4, this.blockID);
         }
      }

   }

   public boolean onNeighborBlockChange(World world, int x, int y, int z, int neighbor_block_id) {
      if (super.onNeighborBlockChange(world, x, y, z, neighbor_block_id)) {
         return true;
      } else {
         int metadata = world.getBlockMetadata(x, y, z);
         this.func_94358_a(world, x, y, z, metadata, this.isPowered ? metadata & 7 : metadata, neighbor_block_id);
         return world.getBlock(x, y, z) != this || world.getBlockMetadata(x, y, z) != metadata;
      }
   }

   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
      int var7;
      if (this.isPowered) {
         var7 = metadata & 7;
      } else {
         var7 = metadata;
      }

      boolean var8 = false;
      if (var7 == 2 && !world.isBlockTopFlatAndSolid(x + 1, y, z)) {
         return false;
      } else if (var7 == 3 && !world.isBlockTopFlatAndSolid(x - 1, y, z)) {
         return false;
      } else if (var7 == 4 && !world.isBlockTopFlatAndSolid(x, y, z - 1)) {
         return false;
      } else {
         return var7 == 5 && !world.isBlockTopFlatAndSolid(x, y, z + 1) ? false : super.isLegalAt(world, x, y, z, metadata);
      }
   }

   protected void func_94358_a(World par1World, int par2, int par3, int par4, int par5, int par6, int par7) {
   }

   protected void refreshTrackShape(World par1World, int par2, int par3, int par4, boolean par5) {
      if (!par1World.isRemote) {
         (new BlockBaseRailLogic(this, par1World, par2, par3, par4)).func_94511_a(par1World.isBlockIndirectlyGettingPowered(par2, par3, par4), par5);
      }

   }

   public int getMobilityFlag() {
      return 0;
   }

   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
      int var7 = par6;
      if (this.isPowered) {
         var7 = par6 & 7;
      }

      super.breakBlock(par1World, par2, par3, par4, par5, par6);
      if (var7 == 2 || var7 == 3 || var7 == 4 || var7 == 5) {
         par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, par5);
      }

      if (this.isPowered) {
         par1World.notifyBlocksOfNeighborChange(par2, par3, par4, par5);
         par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, par5);
      }

   }

   public float getExplosionResistance(Explosion explosion) {
      return explosion.exploder instanceof EntityMinecartTNT ? Float.MAX_VALUE : super.getExplosionResistance(explosion);
   }

   public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
      EnumDirection direction = entity.getDirectionFromYaw();
      return direction.isNorthOrSouth() ? 0 : 1;
   }

   public boolean isDislodgedOrCrushedByFallingBlock(int metadata, Block falling_block, int falling_block_metadata) {
      return false;
   }

   public boolean isSolid(boolean[] is_solid, int metadata) {
      return false;
   }

   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
      return false;
   }

   public boolean blocksFluids(boolean[] blocks_fluids, int metadata) {
      return true;
   }
}