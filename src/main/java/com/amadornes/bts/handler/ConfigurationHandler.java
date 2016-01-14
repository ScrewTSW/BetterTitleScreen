package com.amadornes.bts.handler;

import com.amadornes.bts.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

public class ConfigurationHandler {
    public static Configuration config;
    public static String CATEGORY_CUSTOM_TEXT = "custom text";
    public static String DefaultText = "\u00A74B\u00A76E\u00A7eT\u00A72T\u00A73E\u00A71R \u00A75T\u00A74I\u00A76T\u00A7eL\u00A72E \u00A73S\u00A71C\u00A75R\u00A74E\u00A76E\u00A7eN";
    public static String[] TitleScreenText;
    public static boolean TitleScreenMCVersion;
    public static boolean TitleScreenMCP;
    public static boolean TitleScreenFML;
    public static boolean TitleScreenForge;
    public static boolean TitleScreenMods;

    public static void init(File configFile) {
        if (config == null) {
            config = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {
        TitleScreenText = config.getStringList("Text", CATEGORY_CUSTOM_TEXT, new String[]{DefaultText}, "Insert the text you want here (supports multiple lines).");
        TitleScreenMCVersion = config.getBoolean("Show MC version", CATEGORY_GENERAL, true, "Show which version of Minecraft the client is currently running.");
        TitleScreenMCP = config.getBoolean("Show MCP version", CATEGORY_GENERAL, false, "Show which version of Minecraft Coder Pack (MCP) the client is running.");
        TitleScreenFML = config.getBoolean("Show FML version", CATEGORY_GENERAL, false, "Show which version of Forge Mod Loader (FML) the client is running.");
        TitleScreenForge = config.getBoolean("Show Forge version", CATEGORY_GENERAL, false, "Show which version of Minecraft Forge the client is running.");
        TitleScreenMods = config.getBoolean("Show mods loaded", CATEGORY_GENERAL, true, "Show how many mods are loaded.");

        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public void OnConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfiguration();
        }
    }
}