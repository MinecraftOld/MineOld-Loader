package cn.frish2021.base.mixins.services;

import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.service.mojang.MixinServiceLaunchWrapper;

public class LaunchWrapperMixinService extends MixinServiceLaunchWrapper {
    @Override
    public MixinEnvironment.CompatibilityLevel getMaxCompatibilityLevel() {
        return MixinEnvironment.CompatibilityLevel.JAVA_8;
    }
}
