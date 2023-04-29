package cn.frish2021.base.mixins;

import cn.frish2021.api.Event.EventManager;
import cn.frish2021.api.events.MMLInitializeEvents;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(at = @At("HEAD"), method = "main")
    private static void main(CallbackInfo info) {
        EventManager.call(new MMLInitializeEvents());
        System.out.println("Minecraft 1.3.1");
    }
}
