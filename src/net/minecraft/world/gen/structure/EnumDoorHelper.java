package net.minecraft.world.gen.structure;

// $FF: synthetic class
class EnumDoorHelper {
   // $FF: synthetic field
   static final int[] doorEnum = new int[EnumDoor.values().length];

   static {
      try {
         doorEnum[EnumDoor.OPENING.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         doorEnum[EnumDoor.WOOD_DOOR.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         doorEnum[EnumDoor.GRATES.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         doorEnum[EnumDoor.IRON_DOOR.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }

   }
}