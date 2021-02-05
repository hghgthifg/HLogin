package com.hghgthifg.HLogin;

import com.hghgthifg.HLogin.command.login.LoginCommand;
import com.hghgthifg.HLogin.command.register.RegisterCommand;
import com.hghgthifg.HLogin.event.LoginEvent;
import org.bukkit.Bukkit;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin{
    // 私有实例
    private FileConfiguration config;
    private File userDataFolder=new File(getDataFolder(),"userdata");

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
            if (!userDataFolder.mkdir()){
                getLogger().info("Create userdata folder");
            }
            reloadConfig();
        }
        config = getConfig();
        //注册命令
        Bukkit.getPluginCommand("login").setExecutor(new LoginCommand());
        getLogger().info("Command login loaded");
        Bukkit.getPluginCommand("register").setExecutor(new RegisterCommand());
        getLogger().info("Command register loaded");

        //注册监听器
        Bukkit.getPluginManager().registerEvents(new LoginEvent(userDataFolder,getLogger()),this);
        getLogger().info("Listener login loaded");
    }

}
