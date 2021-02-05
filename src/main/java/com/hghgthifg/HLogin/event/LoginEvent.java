package com.hghgthifg.HLogin.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public final class LoginEvent implements Listener {
    private final File userDataFolder;
    private final Logger logger;
    private File userData;

    public LoginEvent(File Folder,Logger logger){
        this.userDataFolder=Folder;
        this.logger=logger;
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        userData=new File(userDataFolder,event.getPlayer().getUniqueId().toString());
        if (!userData.exists()){
            try {
                if (userData.createNewFile()){
                    logger.info("为"+event.getPlayer().getName()+"创建了数据文件");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        event.getPlayer().sendMessage("Hello "+event.getPlayer().getName()+"!");
    }
}
