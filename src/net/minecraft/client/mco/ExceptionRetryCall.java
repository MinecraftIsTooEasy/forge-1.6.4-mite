package net.minecraft.client.mco;

public class ExceptionRetryCall extends ExceptionMcoService {
   public final int field_96393_c;

   public ExceptionRetryCall(int var1) {
      super(503, "Retry operation", -1);
      this.field_96393_c = var1;
   }
}