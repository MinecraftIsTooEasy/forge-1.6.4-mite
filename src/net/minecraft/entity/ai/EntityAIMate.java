package net.minecraft.entity.ai;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivestock;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.EnumParticle;
import net.minecraft.world.World;

public class EntityAIMate extends EntityAIBase {
   private EntityAnimal theAnimal;
   World theWorld;
   private EntityAnimal targetMate;
   int spawnBabyDelay;
   double moveSpeed;

   public EntityAIMate(EntityAnimal par1EntityAnimal, double par2) {
      this.theAnimal = par1EntityAnimal;
      this.theWorld = par1EntityAnimal.worldObj;
      this.moveSpeed = par2;
      this.setMutexBits(3);
   }

   public boolean shouldExecute() {
      if (!this.theAnimal.isInLove()) {
         return false;
      } else {
         this.targetMate = this.getNearbyMate();
         return this.targetMate != null;
      }
   }

   public boolean continueExecuting() {
      return this.targetMate.isEntityAlive() && this.targetMate.isInLove() && this.spawnBabyDelay < 60;
   }

   public void resetTask() {
      this.targetMate = null;
      this.spawnBabyDelay = 0;
   }

   public void updateTask() {
      this.theAnimal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, (float)this.theAnimal.getVerticalFaceSpeed());
      this.theAnimal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);
      ++this.spawnBabyDelay;
      if (this.spawnBabyDelay >= 60 && this.theAnimal.getDistanceSqToEntity(this.targetMate) < 9.0) {
         this.spawnBaby();
      }

   }

   private EntityAnimal getNearbyMate() {
      float var1 = 8.0F;
      List var2 = this.theWorld.getEntitiesWithinAABB(this.theAnimal.getClass(), this.theAnimal.boundingBox.expand((double)var1, (double)var1, (double)var1));
      double var3 = Double.MAX_VALUE;
      EntityAnimal var5 = null;
      Iterator var6 = var2.iterator();

      while(var6.hasNext()) {
         EntityAnimal var7 = (EntityAnimal)var6.next();
         if (this.theAnimal.canMateWith(var7) && this.theAnimal.getDistanceSqToEntity(var7) < var3) {
            var5 = var7;
            var3 = this.theAnimal.getDistanceSqToEntity(var7);
         }
      }

      return var5;
   }

   private void spawnBaby() {
      EntityAgeable var1 = this.theAnimal.createChild(this.targetMate);
      if (var1 != null) {
         this.theAnimal.setGrowingAgeAfterBreeding();
         this.targetMate.setGrowingAgeAfterBreeding();
         this.theAnimal.resetInLove();
         this.targetMate.resetInLove();
         var1.setGrowingAgeToNewborn();
         var1.setLocationAndAngles(this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, 0.0F, 0.0F);
         if (!this.theWorld.isRemote && var1 instanceof EntityLivestock) {
            ((EntityLivestock)var1).adoptWellnessFromParents(this.theAnimal, this.targetMate);
         }

         this.theWorld.spawnEntityInWorld(var1);
         Random var2 = this.theAnimal.getRNG();

         for(int var3 = 0; var3 < 7; ++var3) {
            double var4 = var2.nextGaussian() * 0.02;
            double var6 = var2.nextGaussian() * 0.02;
            double var8 = var2.nextGaussian() * 0.02;
            this.theWorld.spawnParticle(EnumParticle.heart, this.theAnimal.posX + (double)(var2.nextFloat() * this.theAnimal.width * 2.0F) - (double)this.theAnimal.width, this.theAnimal.posY + 0.5 + (double)(var2.nextFloat() * this.theAnimal.height), this.theAnimal.posZ + (double)(var2.nextFloat() * this.theAnimal.width * 2.0F) - (double)this.theAnimal.width, var4, var6, var8);
         }

         this.theWorld.spawnEntityInWorld(new EntityXPOrb(this.theWorld, this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, var2.nextInt(7) + 1));
      }

   }
}