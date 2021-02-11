package com.hghgthifg.HLogin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

public final class CommandEvent implements Listener {
    private final File userDataFolder;
    private final Logger logger;
    private final HashMap<String,Boolean> userLoginState;

    public CommandEvent(File folder, Logger logger,HashMap<String,Boolean> state){
        this.userDataFolder=folder;
        this.logger=logger;
        this.userLoginState=state;
    }

    @EventHandler
    public void onSendCommand(PlayerCommandPreprocessEvent event) {
        Player player= event.getPlayer();
        if (userLoginState.get(player.getUniqueId().toString()))
        {
            return;
        }
        String command=event.getMessage().replaceAll("/","");
        logger.info(player.getName()+"使用了"+command);
        if (!command.startsWith("login") &&
            !command.startsWith("log") &&
            !command.startsWith("l") &&
            !command.startsWith("register") &&
            !command.startsWith("reg") &&
            !command.startsWith("r"))
        {
            event.setCancelled(true);
            player.sendMessage("请先登录或注册");
        }
    }
}