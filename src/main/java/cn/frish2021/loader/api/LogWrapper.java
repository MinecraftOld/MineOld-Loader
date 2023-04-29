package cn.frish2021.loader.api;

import org.apache.logging.log4j.Level;

public class LogWrapper {
    public static void info(String modId, String message, String level) {
        if (level.equals("INFO")) {
            net.minecraft.launchwrapper.LogWrapper.log(modId, Level.INFO, message);
        } else if (level.equals("WARN")) {
            net.minecraft.launchwrapper.LogWrapper.log(modId, Level.WARN, message);
        } else if (level.equals("ERROR")) {
            net.minecraft.launchwrapper.LogWrapper.log(modId, Level.ERROR, message);
        } else if (level.equals("ALL")) {
            net.minecraft.launchwrapper.LogWrapper.log(modId, Level.ALL, message);
        }
    }
}
