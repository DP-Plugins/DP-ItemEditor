package com.darksoldier1404.die;

import com.darksoldier1404.die.commands.DPIECommand;
import com.darksoldier1404.dppc.lang.DLang;
import com.darksoldier1404.dppc.utils.ColorUtils;
import com.darksoldier1404.dppc.utils.ConfigUtils;
import com.darksoldier1404.dppc.utils.PluginUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemEditor extends JavaPlugin {
    public static ItemEditor plugin;
    public static YamlConfiguration config;
    public static String prefix;
    public static DLang lang;
    public static ItemEditor getInstance() {
        return plugin;
    }

    @Override
    public void onLoad() {
        plugin = this;
        PluginUtil.addPlugin(plugin, 26325);
    }

    @Override
    public void onEnable() {
        config = ConfigUtils.loadDefaultPluginConfig(plugin);
        prefix = ColorUtils.applyColor(config.getString("Settings.prefix"));
        lang = new DLang(config.getString("Settings.Lang") == null ? "English" : config.getString("Settings.Lang"), plugin);
        getCommand("dpie").setExecutor(new DPIECommand().getExecutor());
    }
}
