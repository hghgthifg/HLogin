package com.hghgthifg.HLogin.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public final class LoginEvent implements Listener {
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        event.getPlayer().sendMessage("Hello,"+event.getPlayer().getName()+".");
    }
}
