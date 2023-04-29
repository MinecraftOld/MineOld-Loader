package cn.frish2021.loader.api;

public class ModInfo {
    private final String name;
    private final String modid;
    private final String[] author;
    private final String version;
    private final String description;
    private final String mixin;
    private final String mainClass;
    private final String license;

    public ModInfo(String name, String modid, String[] author, String version, String description, String mixin, String mainClass, String license) {
        this.name = name;
        this.modid = modid;
        this.author = author;
        this.version = version;
        this.description = description;
        this.mixin = mixin;
        this.mainClass = mainClass;
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public String[] getAuthor() {
        return author;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String getMixin() {
        return mixin;
    }

    public String getMainClass() {
        return mainClass;
    }

    public String getLicense() {
        return license;
    }

    public String getModid() {
        return modid;
    }
}
