package cn.frish2021.base.launch;

import net.minecraft.launchwrapper.IClassTransformer;
import org.spongepowered.asm.service.ITransformer;

public class MMLTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String s, String s1, byte[] bytes) {
        return bytes;
    }
}
