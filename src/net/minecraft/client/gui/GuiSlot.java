package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class GuiSlot {
   private final Minecraft mc;
   private int width;
   private int height;
   protected int top;
   protected int bottom;
   private int right;
   private int left;
   protected final int slotHeight;
   private int scrollUpButtonID;
   private int scrollDownButtonID;
   protected int mouseX;
   protected int mouseY;
   private float initialClickY = -2.0F;
   private float scrollMultiplier;
   private float amountScrolled;
   private int selectedElement = -1;
   private long lastClicked;
   private boolean showSelectionBox = true;
   private boolean field_77243_s;
   private int field_77242_t;

   public GuiSlot(Minecraft par1Minecraft, int par2, int par3, int par4, int par5, int par6) {
      this.mc = par1Minecraft;
      this.width = par2;
      this.height = par3;
      this.top = par4;
      this.bottom = par5;
      this.slotHeight = par6;
      this.left = 0;
      this.right = par2;
   }

   public void func_77207_a(int par1, int par2, int par3, int par4) {
      this.width = par1;
      this.height = par2;
      this.top = par3;
      this.bottom = par4;
      this.left = 0;
      this.right = par1;
   }

   public void setShowSelectionBox(boolean par1) {
      this.showSelectionBox = par1;
   }

   protected void func_77223_a(boolean par1, int par2) {
      this.field_77243_s = par1;
      this.field_77242_t = par2;
      if (!par1) {
         this.field_77242_t = 0;
      }

   }

   protected abstract int getSize();

   protected abstract void elementClicked(int var1, boolean var2);

   protected abstract boolean isSelected(int var1);

   protected int getContentHeight() {
      return this.getSize() * this.slotHeight + this.field_77242_t;
   }

   protected abstract void drawBackground();

   protected abstract void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5);

   protected void func_77222_a(int par1, int par2, Tessellator par3Tessellator) {
   }

   protected void func_77224_a(int par1, int par2) {
   }

   protected void func_77215_b(int par1, int par2) {
   }

   public int func_77210_c(int par1, int par2) {
      int var3 = this.width / 2 - 110;
      int var4 = this.width / 2 + 110;
      int var5 = par2 - this.top - this.field_77242_t + (int)this.amountScrolled - 4;
      int var6 = var5 / this.slotHeight;
      return par1 >= var3 && par1 <= var4 && var6 >= 0 && var5 >= 0 && var6 < this.getSize() ? var6 : -1;
   }

   public void registerScrollButtons(int par1, int par2) {
      this.scrollUpButtonID = par1;
      this.scrollDownButtonID = par2;
   }

   private void bindAmountScrolled() {
      int var1 = this.func_77209_d();
      if (var1 < 0) {
         var1 /= 2;
      }

      if (this.amountScrolled < 0.0F) {
         this.amountScrolled = 0.0F;
      }

      if (this.amountScrolled > (float)var1) {
         this.amountScrolled = (float)var1;
      }

   }

   public int func_77209_d() {
      return this.getContentHeight() - (this.bottom - this.top - 4);
   }

   public void func_77208_b(int par1) {
      this.amountScrolled += (float)par1;
      this.bindAmountScrolled();
      this.initialClickY = -2.0F;
   }

   public void actionPerformed(GuiButton par1GuiButton) {
      if (par1GuiButton.enabled) {
         if (par1GuiButton.id == this.scrollUpButtonID) {
            this.amountScrolled -= (float)(this.slotHeight * 2 / 3);
            this.initialClickY = -2.0F;
            this.bindAmountScrolled();
         } else if (par1GuiButton.id == this.scrollDownButtonID) {
            this.amountScrolled += (float)(this.slotHeight * 2 / 3);
            this.initialClickY = -2.0F;
            this.bindAmountScrolled();
         }
      }

   }

   public void drawDarkenedBackground(int layer) {
      GL11.glDisable(2896);
      GL11.glDisable(2912);
      Tessellator var18 = Tessellator.instance;
      this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      float var17 = 32.0F;
      if (layer == 1) {
         var18.startDrawingQuads();
         var18.setColorOpaque_I(2105376);
         var18.addVertexWithUV((double)this.left, (double)this.bottom, 0.0, (double)((float)this.left / var17), (double)((float)this.bottom / var17));
         var18.addVertexWithUV((double)this.right, (double)this.bottom, 0.0, (double)((float)this.right / var17), (double)((float)this.bottom / var17));
         var18.addVertexWithUV((double)this.right, (double)this.top, 0.0, (double)((float)this.right / var17), (double)((float)this.top / var17));
         var18.addVertexWithUV((double)this.left, (double)this.top, 0.0, (double)((float)this.left / var17), (double)((float)this.top / var17));
         var18.draw();
      } else if (layer == 2) {
         GL11.glDisable(2929);
         byte var20 = 4;
         this.overlayBackground(0, this.top, 255, 255);
         this.overlayBackground(this.bottom, this.height, 255, 255);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         GL11.glDisable(3008);
         GL11.glShadeModel(7425);
         GL11.glDisable(3553);
         var18.startDrawingQuads();
         var18.setColorRGBA_I(0, 0);
         var18.addVertexWithUV((double)this.left, (double)(this.top + var20), 0.0, 0.0, 1.0);
         var18.addVertexWithUV((double)this.right, (double)(this.top + var20), 0.0, 1.0, 1.0);
         var18.setColorRGBA_I(0, 255);
         var18.addVertexWithUV((double)this.right, (double)this.top, 0.0, 1.0, 0.0);
         var18.addVertexWithUV((double)this.left, (double)this.top, 0.0, 0.0, 0.0);
         var18.draw();
         var18.startDrawingQuads();
         var18.setColorRGBA_I(0, 255);
         var18.addVertexWithUV((double)this.left, (double)this.bottom, 0.0, 0.0, 1.0);
         var18.addVertexWithUV((double)this.right, (double)this.bottom, 0.0, 1.0, 1.0);
         var18.setColorRGBA_I(0, 0);
         var18.addVertexWithUV((double)this.right, (double)(this.bottom - var20), 0.0, 1.0, 0.0);
         var18.addVertexWithUV((double)this.left, (double)(this.bottom - var20), 0.0, 0.0, 0.0);
         var18.draw();
      }

   }

   public void drawScreen(int par1, int par2, float par3) {
      this.mouseX = par1;
      this.mouseY = par2;
      this.drawBackground();
      int var4 = this.getSize();
      int var5 = this.getScrollBarX();
      int var6 = var5 + 6;
      int var9;
      int var10;
      int var11;
      int var13;
      int var19;
      int var14;
      if (Mouse.isButtonDown(0)) {
         if (this.initialClickY == -1.0F) {
            boolean var7 = true;
            if (par2 >= this.top && par2 <= this.bottom) {
               var14 = this.width / 2 - 110;
               var9 = this.width / 2 + 110;
               var10 = par2 - this.top - this.field_77242_t + (int)this.amountScrolled - 4;
               var11 = var10 / this.slotHeight;
               if (par1 >= var14 && par1 <= var9 && var11 >= 0 && var10 >= 0 && var11 < var4) {
                  boolean var12 = var11 == this.selectedElement && Minecraft.getSystemTime() - this.lastClicked < 250L;
                  this.elementClicked(var11, var12);
                  this.selectedElement = var11;
                  this.lastClicked = Minecraft.getSystemTime();
               } else if (par1 >= var14 && par1 <= var9 && var10 < 0) {
                  this.func_77224_a(par1 - var14, par2 - this.top + (int)this.amountScrolled - 4);
                  var7 = false;
               }

               if (par1 >= var5 && par1 <= var6) {
                  this.scrollMultiplier = -1.0F;
                  var19 = this.func_77209_d();
                  if (var19 < 1) {
                     var19 = 1;
                  }

                  var13 = (int)((float)((this.bottom - this.top) * (this.bottom - this.top)) / (float)this.getContentHeight());
                  if (var13 < 32) {
                     var13 = 32;
                  }

                  if (var13 > this.bottom - this.top - 8) {
                     var13 = this.bottom - this.top - 8;
                  }

                  this.scrollMultiplier /= (float)(this.bottom - this.top - var13) / (float)var19;
               } else {
                  this.scrollMultiplier = 1.0F;
               }

               if (var7) {
                  this.initialClickY = (float)par2;
               } else {
                  this.initialClickY = -2.0F;
               }
            } else {
               this.initialClickY = -2.0F;
            }
         } else if (this.initialClickY >= 0.0F) {
            this.amountScrolled -= ((float)par2 - this.initialClickY) * this.scrollMultiplier;
            this.initialClickY = (float)par2;
         }
      } else {
         while(true) {
            if (this.mc.gameSettings.touchscreen || !Mouse.next()) {
               this.initialClickY = -1.0F;
               break;
            }

            int var16 = Mouse.getEventDWheel();
            if (var16 != 0) {
               if (var16 > 0) {
                  var16 = -1;
               } else if (var16 < 0) {
                  var16 = 1;
               }

               this.amountScrolled += (float)(var16 * this.slotHeight / 2);
            }
         }
      }

      this.bindAmountScrolled();
      this.drawDarkenedBackground(1);
      Tessellator var18 = Tessellator.instance;
      var9 = this.width / 2 - 92 - 16;
      var10 = this.top + 4 - (int)this.amountScrolled;
      if (this.field_77243_s) {
         this.func_77222_a(var9, var10, var18);
      }

      for(var11 = 0; var11 < var4; ++var11) {
         var19 = var10 + var11 * this.slotHeight + this.field_77242_t;
         var13 = this.slotHeight - 4;
         if (var19 <= this.bottom && var19 + var13 >= this.top) {
            if (this.showSelectionBox && this.isSelected(var11)) {
               var14 = this.width / 2 - 110;
               int var15 = this.width / 2 + 110;
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glDisable(3553);
               var18.startDrawingQuads();
               var18.setColorOpaque_I(8421504);
               var18.addVertexWithUV((double)var14, (double)(var19 + var13 + 2), 0.0, 0.0, 1.0);
               var18.addVertexWithUV((double)var15, (double)(var19 + var13 + 2), 0.0, 1.0, 1.0);
               var18.addVertexWithUV((double)var15, (double)(var19 - 2), 0.0, 1.0, 0.0);
               var18.addVertexWithUV((double)var14, (double)(var19 - 2), 0.0, 0.0, 0.0);
               var18.setColorOpaque_I(0);
               var18.addVertexWithUV((double)(var14 + 1), (double)(var19 + var13 + 1), 0.0, 0.0, 1.0);
               var18.addVertexWithUV((double)(var15 - 1), (double)(var19 + var13 + 1), 0.0, 1.0, 1.0);
               var18.addVertexWithUV((double)(var15 - 1), (double)(var19 - 1), 0.0, 1.0, 0.0);
               var18.addVertexWithUV((double)(var14 + 1), (double)(var19 - 1), 0.0, 0.0, 0.0);
               var18.draw();
               GL11.glEnable(3553);
            }

            this.drawSlot(var11, var9, var19, var13, var18);
         }
      }

      this.drawDarkenedBackground(2);
      var19 = this.func_77209_d();
      if (var19 > 0) {
         var13 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
         if (var13 < 32) {
            var13 = 32;
         }

         if (var13 > this.bottom - this.top - 8) {
            var13 = this.bottom - this.top - 8;
         }

         var14 = (int)this.amountScrolled * (this.bottom - this.top - var13) / var19 + this.top;
         if (var14 < this.top) {
            var14 = this.top;
         }

         var18.startDrawingQuads();
         var18.setColorRGBA_I(0, 255);
         var18.addVertexWithUV((double)var5, (double)this.bottom, 0.0, 0.0, 1.0);
         var18.addVertexWithUV((double)var6, (double)this.bottom, 0.0, 1.0, 1.0);
         var18.addVertexWithUV((double)var6, (double)this.top, 0.0, 1.0, 0.0);
         var18.addVertexWithUV((double)var5, (double)this.top, 0.0, 0.0, 0.0);
         var18.draw();
         var18.startDrawingQuads();
         var18.setColorRGBA_I(8421504, 255);
         var18.addVertexWithUV((double)var5, (double)(var14 + var13), 0.0, 0.0, 1.0);
         var18.addVertexWithUV((double)var6, (double)(var14 + var13), 0.0, 1.0, 1.0);
         var18.addVertexWithUV((double)var6, (double)var14, 0.0, 1.0, 0.0);
         var18.addVertexWithUV((double)var5, (double)var14, 0.0, 0.0, 0.0);
         var18.draw();
         var18.startDrawingQuads();
         var18.setColorRGBA_I(12632256, 255);
         var18.addVertexWithUV((double)var5, (double)(var14 + var13 - 1), 0.0, 0.0, 1.0);
         var18.addVertexWithUV((double)(var6 - 1), (double)(var14 + var13 - 1), 0.0, 1.0, 1.0);
         var18.addVertexWithUV((double)(var6 - 1), (double)var14, 0.0, 1.0, 0.0);
         var18.addVertexWithUV((double)var5, (double)var14, 0.0, 0.0, 0.0);
         var18.draw();
      }

      this.func_77215_b(par1, par2);
      GL11.glEnable(3553);
      GL11.glShadeModel(7424);
      GL11.glEnable(3008);
      GL11.glDisable(3042);
   }

   protected int getScrollBarX() {
      return this.width / 2 + 124;
   }

   private void overlayBackground(int par1, int par2, int par3, int par4) {
      Tessellator var5 = Tessellator.instance;
      this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      float var6 = 32.0F;
      var5.startDrawingQuads();
      var5.setColorRGBA_I(4210752, par4);
      var5.addVertexWithUV(0.0, (double)par2, 0.0, 0.0, (double)((float)par2 / var6));
      var5.addVertexWithUV((double)this.width, (double)par2, 0.0, (double)((float)this.width / var6), (double)((float)par2 / var6));
      var5.setColorRGBA_I(4210752, par3);
      var5.addVertexWithUV((double)this.width, (double)par1, 0.0, (double)((float)this.width / var6), (double)((float)par1 / var6));
      var5.addVertexWithUV(0.0, (double)par1, 0.0, 0.0, (double)((float)par1 / var6));
      var5.draw();
   }
}