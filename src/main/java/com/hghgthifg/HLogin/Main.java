package com.hghgthifg.HLogin;

import org.bukkit.Bukkit;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
    // 私有实例
    private FileConfiguration config;

    /*
     * 插件关闭时的行为
     */
    @Override
    public void onDisable(){
        super.onDisable();
    }

    /*
     * 插件开启时的行为
     */
    @Override
    public void onEnable(){
        super.onEnable();
        Bukkit.getConsoleSender().sendMessage("§4HLogin loaded");

        //获取config信息
        if (!getDataFolder().mkdir()) {
            saveDefaultConfig();
            reloadConfig();
        }
        config = getConfig();

    }

}
