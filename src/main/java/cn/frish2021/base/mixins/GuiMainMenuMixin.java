package cn.frish2021.base.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiMainMenu;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuMixin extends GuiScreen {
    @Shadow
    private void func_73971_c(int var1, int var2, float var3) {}
    @Shadow
    private float field_73974_b = 0.0F;
    @Shadow
    private String field_73975_c = "missingno";

    /**
     * @author Frish2021
     * @reason
     */
    @Overwrite
    public void func_73863_a(int var1, int var2, float var3) {
        this.func_73971_c(var1, var2, var3);
        Tessellator var4 = Tessellator.field_78398_a;
        short var5 = 274;
        int var6 = this.field_73880_f / 2 - var5 / 2;
        byte var7 = 30;
        this.func_73733_a(0, 0, this.field_73880_f, this.field_73881_g, -2130706433, 16777215);
        this.func_73733_a(0, 0, this.field_73880_f, this.field_73881_g, 0, Integer.MIN_VALUE);
        GL11.glBindTexture(3553, this.field_73882_e.field_71446_o.func_78341_b("/title/mclogo.png"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if ((double)this.field_73974_b < 1.0E-4) {
            this.func_73729_b(var6 + 0, var7 + 0, 0, 0, 99, 44);
            this.func_73729_b(var6 + 99, var7 + 0, 129, 0, 27, 44);
            this.func_73729_b(var6 + 99 + 26, var7 + 0, 126, 0, 3, 44);
            this.func_73729_b(var6 + 99 + 26 + 3, var7 + 0, 99, 0, 26, 44);
            this.func_73729_b(var6 + 155, var7 + 0, 0, 45, 155, 44);
        } else {
            this.func_73729_b(var6 + 0, var7 + 0, 0, 0, 155, 44);
            this.func_73729_b(var6 + 155, var7 + 0, 0, 45, 155, 44);
        }

        var4.func_78378_d(16777215);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.field_73880_f / 2 + 90), 70.0F, 0.0F);
        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
        float var8 = 1.8F - MathHelper.func_76135_e(MathHelper.func_76126_a((float)(Minecraft.func_71386_F() % 1000L) / 1000.0F * 3.1415927F * 2.0F) * 0.1F);
        var8 = var8 * 100.0F / (float)(this.field_73886_k.func_78256_a(this.field_73975_c) + 32);
        GL11.glScalef(var8, var8, var8);
        this.func_73732_a(this.field_73886_k, this.field_73975_c, 0, -8, 16776960);
        GL11.glPopMatrix();
        String var9 = "Minecraft 1.3.1/MineOld-Mod-Loader";
        if (this.field_73882_e.func_71355_q()) {
            var9 = var9 + " Demo";
        }

        this.func_73731_b(this.field_73886_k, var9, 2, this.field_73881_g - 10, 16777215);
        String var10 = "Copyright Mojang AB. Do not distribute!";
        this.func_73731_b(this.field_73886_k, var10, this.field_73880_f - this.field_73886_k.func_78256_a(var10) - 2, this.field_73881_g - 10, 16777215);
        super.func_73863_a(var1, var2, var3);
    }
}
