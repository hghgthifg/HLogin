package com.hghgthifg.HLogin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

public final class MoveEvent implements Listener {
    private final File userDataFolder;
    private final Logger logger;
    private final HashMap<String,Boolean> userLoginState;

    public MoveEvent(File folder, Logger logger,HashMap<String,Boolean> state){
        this.userDataFolder=folder;
        this.logger=logger;
        this.userLoginState=state;
    }

    @EventHandler
    public void onMovesCommand(PlayerMoveEvent event) {
        Player player= event.getPlayer();

        if (userLoginState.get(player.getUniqueId().toString()))
        {
            return;
        }
        event.setCancelled(true);
        player.sendMessage("请先登录或注册");
    }
}