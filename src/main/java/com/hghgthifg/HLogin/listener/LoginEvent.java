package com.hghgthifg.HLogin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

import static org.bukkit.GameMode.SPECTATOR;

public final class LoginEvent implements Listener {
    private final File userDataFolder;
    private final Logger logger;
    private final HashMap<String,Boolean> userLoginState;
    private File userData;

    public LoginEvent(File folder, Logger logger,HashMap<String,Boolean> state){
        this.userDataFolder=folder;
        this.logger=logger;
        this.userLoginState=state;
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        Player player=event.getPlayer();
        userData=new File(userDataFolder,player.getUniqueId().toString());
        userLoginState.put(player.getUniqueId().toString(),false);
        if (!userData.exists()) {
            player.sendMessage("请使用/reg <密码> <确认密码> 注册");
        }
        else {
            player.sendMessage("请使用/log <密码> 登录");
        }

    }
}
