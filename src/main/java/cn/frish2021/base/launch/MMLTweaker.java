package cn.frish2021.base.launch;

import cn.frish2021.loader.api.ModInfo;
import cn.frish2021.loader.api.ModInitialize;
import cn.frish2021.util.ClassLoaderUtil;
import com.google.gson.Gson;
import net.minecraft.client.Minecraft;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraft.launchwrapper.LogWrapper;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.io.*;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

public class MMLTweaker implements ITweaker {
    private String gameDir = System.getProperty("user.dir");

    @Override
    public void acceptOptions(List<String> list, File file, File file1, String s) {
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader launchClassLoader) {
        launchClassLoader.registerTransformer(MMLTransformer.class.getName());
        LogWrapper.info("MMLTweaker inject......");
        List<String> mixins = new ArrayList<>();
        final ArrayList<ModInitialize> modInitialized = new ArrayList<>();
        File mods = new File(gameDir, "mods");
        if (!mods.exists()) {
            mods.mkdir();
        }
        try {
            Files.walkFileTree(mods.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toFile().getName().endsWith(".jar")) {
                        try {
                            JarFile jarFile = new JarFile(file.toFile());
                            if (jarFile.getEntry("mineold.mod.json") != null) {
                                launchClassLoader.addURL(file.toUri().toURL());
                            } else {
                                LogWrapper.log("MineOldLoader", Level.WARN, "Ignore %s it is not a mineold mod or mineold.mod.json not found", file.toFile().getName());
                            }
                        } catch (Throwable throwable) {
                            LogWrapper.log("MineOldLoader", Level.ERROR, throwable, "File %s fail to load", file.toFile().getName());
                        }
                    }
                    return super.visitFile(file, attrs);
                }
            });

            ModInfo modInfo = null;

            Enumeration<URL> resources = launchClassLoader.getResources("mineold.mod.json");
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();

                modInfo = new Gson().fromJson(IOUtils.toString(url.openStream(), StandardCharsets.UTF_8), ModInfo.class);
                if (modInfo.getMixin() != null) {
                    mixins.add(modInfo.getMixin());
                }
                LogWrapper.log("MineOldLoader", Level.INFO, "Loaded: %s", modInfo.getModid());
            }

            File[] files = mods.listFiles();
            for (File file : files) {
                if (file.isDirectory() && !file.getName().endsWith(".jar")) {
                    continue;
                }

                ClassLoader classLoader = ClassLoaderUtil.getClassLoader("file:/" + file.getPath().replace("\\", "/"));
                Class<?> clazz = classLoader.loadClass(modInfo.getMainClass());
                modInitialized.add((ModInitialize) clazz.newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (ModInitialize modInitialize : modInitialized) {
            modInitialize.initialize();
        }

        MixinBootstrap.init();
        Mixins.addConfiguration("mixin.mineold.json");
        mixins.forEach(Mixins::addConfiguration);
    }

    @Override
    public String getLaunchTarget() {
        return Minecraft.class.getName();
    }

    @Override
    public String[] getLaunchArguments() {
        return new String[0];
    }
}
