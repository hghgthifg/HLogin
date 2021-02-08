package com.hghgthifg.HLogin.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.logging.Logger;

import static org.bukkit.GameMode.SPECTATOR;

public final class LoginEvent implements Listener {
    private final File userDataFolder;
    private final Logger logger;
    private File userData;

    public LoginEvent(File folder,Logger logger){
        this.userDataFolder=folder;
        this.logger=logger;
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        Player player=event.getPlayer();
        userData=new File(userDataFolder,player.getUniqueId().toString());
        if (!userData.exists()) {
            player.sendMessage("请使用/reg <密码> <确认密码> 注册");
        }
        else {
            player.sendMessage("请使用/log <密码> 登录");
        }
    }

    private void setStatus(Player player)
    {
        player.setAllowFlight(false);
        player.setCanPickupItems(false);
        player.setCollidable(false);
        player.setGameMode(SPECTATOR);
        //player.setWalkSpeed(0);
    }
}
