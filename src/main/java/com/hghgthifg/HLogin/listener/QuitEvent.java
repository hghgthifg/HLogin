package com.hghgthifg.HLogin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

public final class QuitEvent implements Listener {
    private final File userDataFolder;
    private final Logger logger;
    private final HashMap<String,Boolean> userLoginState;
    private File userData;

    public QuitEvent(File folder, Logger logger,HashMap<String,Boolean> state){
        this.userDataFolder=folder;
        this.logger=logger;
        this.userLoginState=state;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {

    }
}
