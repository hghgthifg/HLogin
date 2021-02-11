package com.hghgthifg.HLogin;

import com.hghgthifg.HLogin.command.login.LoginCommand;
import com.hghgthifg.HLogin.command.register.RegisterCommand;
import com.hghgthifg.HLogin.listener.CommandEvent;
import com.hghgthifg.HLogin.listener.LoginEvent;
import com.hghgthifg.HLogin.listener.MoveEvent;
import com.hghgthifg.HLogin.listener.QuitEvent;
import org.bukkit.Bukkit;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.HashMap;

public class Main extends JavaPlugin{
    // 私有实例
    private FileConfiguration config;
    private final File userDataFolder=new File(getDataFolder(),"userdata");
    public HashMap<String,Boolean>  userLoginState=new HashMap<>();

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
        Bukkit.getConsoleSender().sendMessage("§4HLogin已启用");
        //获取config信息
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
            saveDefaultConfig();
            getLogger().info("创建了插件数据目录");
            reloadConfig();
        }
        if (!userDataFolder.exists()) {
            userDataFolder.mkdir();
            getLogger().info("创建了用户数据目录");
        }
        config = getConfig();


        //注册命令
        Bukkit.getPluginCommand("login").setExecutor(new LoginCommand(userDataFolder,getLogger(),userLoginState));
        Bukkit.getPluginCommand("register").setExecutor(new RegisterCommand(userDataFolder,getLogger(),userLoginState));
        getLogger().info("指令加载完成");

        //注册监听器
        Bukkit.getPluginManager().registerEvents(new LoginEvent(userDataFolder,getLogger(),userLoginState),this);
        Bukkit.getPluginManager().registerEvents(new CommandEvent(userDataFolder,getLogger(),userLoginState),this);
        //Bukkit.getPluginManager().registerEvents(new QuitEvent(userDataFolder,getLogger(),userLoginState),this);
        Bukkit.getPluginManager().registerEvents(new MoveEvent(userDataFolder,getLogger(),userLoginState),this);
        getLogger().info("监听器加载完成");
    }

}
