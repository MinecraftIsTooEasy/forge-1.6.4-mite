package net.minecraft.util;

public enum EnumParticle {
   hugeexplosion,
   largeexplode,
   fireworkSpark,
   bubble,
   suspended,
   depthsuspend,
   townaura,
   crit,
   magicCrit,
   smoke,
   mobSpell,
   mobSpellAmbient,
   spell,
   instantSpell,
   witchMagic,
   note,
   portal_underworld,
   portal_nether,
   runegate,
   enchantmenttable,
   explode,
   flame,
   lava,
   footstep,
   splash,
   largesmoke,
   cloud,
   reddust,
   snowballpoof,
   brickpoof,
   brickpoof_netherrack,
   dripWater,
   dripLava,
   snowshovel,
   slime,
   ochre_jelly,
   crimson_blob,
   gray_ooze,
   black_pudding,
   heart,
   vampiric_gain,
   angryVillager,
   happyVillager,
   manure,
   repair,
   iconcrack,
   tilecrack,
   crafting,
   sacred;

   public static EnumParticle get(int ordinal) {
      return values()[ordinal];
   }
}